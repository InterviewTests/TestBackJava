package microservice.models;


import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "categories")
public class Category {
    
    @Id 
    private String _id;

    @NotNull
    @Indexed(unique = true)
    private String category;

    public Category() { }

    public Category(String category) { this.category = category; }

    public Category(String _id, String category) {
        this._id = _id; 
        this.category = category; 
    }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String get_id() { return _id; }

    public void set_id(String _id) { this._id = _id; }



}
