package bdavanzadas.lab1.mappers;


/**
 *
 * Mapeador para convertir entidades de usuario a documentos de usuario.
 * Este mapeador transforma los datos de la entidad de usuario
 * a un documento que puede ser almacenado en MongoDB.
 * */
public class UserMapper {


    /**
     * * Este método toma una entidad de usuario y crea un documento
     * @param entity
     * @return
     */
    public static bdavanzadas.lab1.documents.UserDocument fromUserEntity(bdavanzadas.lab1.entities.UserEntity entity) {
        bdavanzadas.lab1.documents.UserDocument document = new bdavanzadas.lab1.documents.UserDocument();

        document.setUserId(entity.getId());
        document.setUsername(entity.getUsername());
        document.setPassword(entity.getPassword());
        document.setRole(entity.getRole());

        return document;
    }
}
