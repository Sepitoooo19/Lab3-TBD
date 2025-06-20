package bdavanzadas.Lab3.controllers;

import bdavanzadas.Lab3.entities.ClientEntity;
import bdavanzadas.Lab3.entities.DealerEntity;
import bdavanzadas.Lab3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bdavanzadas.Lab3.entities.UserEntity;
import bdavanzadas.Lab3.Security.JwtUtil;



import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 *
 * La clase AuthController maneja las solicitudes de autenticación y registro de usuarios.
 * Esta clase contiene métodos para registrar nuevos usuarios y validar credenciales de inicio de sesión.
 *
 *
 * */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {


    /**
     * Servicio de usuarios.
     * Este servicio se utiliza para interactuar con la base de datos de usuarios.
     */
    @Autowired
    private UserService userService;



    /**
     *
     * JwtUtil es una clase de utilidad para manejar la generación y validación de tokens JWT.
     * Esta clase se utiliza para generar tokens JWT para los usuarios autenticados y validar tokens en solicitudes posteriores.
     *
     *
     * */
    @Autowired
    private JwtUtil jwtUtil;







    /**
     * Endpoint para registrar un nuevo usuario.
     * Este endpoint recibe un objeto JSON con los datos del nuevo usuario y lo registra en la base de datos.
     * Si el usuario es un cliente, se guardan los datos adicionales del cliente.
     * Si el usuario es un dealer, se guardan los datos adicionales del dealer.
     * Si el usuario es un administrador, se guardan los datos del administrador.
     * @param "body" El objeto JSON con los datos del nuevo usuario.
     * @return Una respuesta HTTP con el resultado del registro.
     *
     * */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> body) {
        try {
            String username = (String) body.get("username");
            String password = (String) body.get("password");
            String role = (String) body.get("role");

            if ("ADMIN".equals(role)) {
                userService.registerAdmin(username, password);
                return ResponseEntity.ok(Map.of(
                        "success", true,
                        "message", "Administrador registrado exitosamente"
                ));
            }
            else if ("CLIENT".equals(role)) {
                String name = (String) body.get("name");
                String rut = (String) body.get("rut");
                String email = (String) body.get("email");
                String phone = (String) body.get("phone");
                String address = (String) body.get("address");

                // Convertir WKT a coordenadas para MongoDB
                String wkt = (String) body.get("ubication");
                double[] coords = parseWktToCoordinates(wkt);

                userService.registerClient(
                        username, password, name, rut,
                        email, phone, address, coords[0], coords[1]
                );

                return ResponseEntity.ok(Map.of(
                        "success", true,
                        "message", "Cliente registrado exitosamente"
                ));
            }
            else if ("DEALER".equals(role)) {
                String name = (String) body.get("name");
                String rut = (String) body.get("rut");
                String email = (String) body.get("email");
                String phone = (String) body.get("phone");
                String vehicle = (String) body.get("vehicle");
                String plate = (String) body.get("plate");

                // Convertir WKT a coordenadas para MongoDB
                String wkt = (String) body.get("ubication");
                double[] coords = parseWktToCoordinates(wkt);

                userService.registerDealer(
                        username, password, name, rut,
                        email, phone, vehicle, plate, coords[0], coords[1]
                );

                return ResponseEntity.ok(Map.of(
                        "success", true,
                        "message", "Repartidor registrado exitosamente"
                ));
            }
            else {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "Rol no válido"
                ));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "success", false,
                            "message", "Error al registrar usuario: " + e.getMessage()
                    ));
        }
    }

    // Método auxiliar para convertir WKT a coordenadas
    private double[] parseWktToCoordinates(String wkt) {
        if (wkt == null || wkt.isEmpty()) {
            return new double[]{0, 0}; // Coordenadas por defecto
        }

        try {
            String cleaned = wkt.replace("POINT(", "").replace(")", "");
            String[] parts = cleaned.split(" ");
            double longitude = Double.parseDouble(parts[0]);
            double latitude = Double.parseDouble(parts[1]);
            return new double[]{longitude, latitude};
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de ubicación inválido. Debe ser 'POINT(longitud latitud)'");
        }
    }

    /**
     * Endpoint para loggearse en la aplicación.
     * Este endpoint recibe un objeto JSON con el nombre de usuario y la contraseña del usuario.
     * Si las credenciales son válidas, se genera un token JWT y se devuelve en la respuesta.
     * @param "body" El objeto JSON con el nombre de usuario y la contraseña del usuario.
     * @return Una respuesta HTTP con el token JWT y el rol del usuario.
     *
     *
     * */
    // Endpoint para el inicio de sesión
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        Optional<UserEntity> userOpt = userService.validateCredentials(username, password);

        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole(), user.getId());
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("role", user.getRole());
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}