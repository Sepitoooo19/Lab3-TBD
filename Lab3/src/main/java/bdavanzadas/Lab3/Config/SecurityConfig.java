package bdavanzadas.Lab3.Config;

import bdavanzadas.Lab3.Security.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;


/**
 * Configuración de seguridad para la aplicación.
 * Esta clase define las reglas de autorización y autenticación para las rutas de la API.
 * Utiliza JWT para la autenticación y BCryptPasswordEncoder para el hash seguro de contraseñas.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     - Define reglas de autorización:
     - Permite acceso público a rutas `/auth/**`, `/clients/**`, etc.
     - Restringe acceso a `/companies/**` a usuarios con roles ADMIN, CLIENT o DEALER
     - Requiere autenticación para otras rutas
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/clients/**", "/orders/**","/companies/**","/payment-methods/**","/products/**", "/dealers/**","/order-details/**","/ratings/**","/coverage-areas/**","/emergency-reports/**").permitAll()
                        .requestMatchers("/companies/**").hasAnyRole("ADMIN", "CLIENT", "DEALER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



    /**
     * Configura el codificador de contraseñas.
     * Utiliza BCryptPasswordEncoder para el hash seguro de contraseñas.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Configura el filtro JWT.
     * Este filtro se encarga de validar el token JWT en cada solicitud.
     *
     * @return OncePerRequestFilter
     */
    private OncePerRequestFilter jwtFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                String authHeader = request.getHeader("Authorization");

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);

                    if (jwtUtil.validateToken(token)) {
                        String role = jwtUtil.extractRole(token);
                        String userId = jwtUtil.extractUserId(token);  // Ahora es String

                        var auth = new UsernamePasswordAuthenticationToken(
                                userId, null, Collections.singletonList(() -> role));
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
                filterChain.doFilter(request, response);
            }
        };
    }
}