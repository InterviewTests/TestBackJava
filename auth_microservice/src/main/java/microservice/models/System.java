package microservice.models;


import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "system")
public class System {

    @Id 
    private ObjectId _id;

    @NotNull
    @Indexed(unique = true)
    private String username;

    @NotNull
    private String password;

    public System() { }

    public System(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String get_id() { return _id.toHexString(); }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public void set_id(ObjectId _id) { this._id = _id; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

}
