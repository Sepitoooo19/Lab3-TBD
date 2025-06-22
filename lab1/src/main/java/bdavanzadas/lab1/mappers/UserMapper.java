package bdavanzadas.lab1.mappers;

public class UserMapper {


    public static bdavanzadas.lab1.documents.UserDocument fromUserEntity(bdavanzadas.lab1.entities.UserEntity entity) {
        bdavanzadas.lab1.documents.UserDocument document = new bdavanzadas.lab1.documents.UserDocument();

        document.setUserId(entity.getId());
        document.setUsername(entity.getUsername());
        document.setPassword(entity.getPassword());
        document.setRole(entity.getRole());

        return document;
    }
}
