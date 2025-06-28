package bdavanzadas.lab1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteCountDTO {
    @Id
    private Document _id;  // This will hold the route document
    
    @Field("count")
    private int count;
    
    public Document getRoute() {
        return _id;
    }
}
