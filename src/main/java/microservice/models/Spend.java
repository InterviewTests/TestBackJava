package microservice.models;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


public class Spend {
    @Id
    private ObjectId _id;

    private String description;
    
    @NotNull
    private BigDecimal value;

    @NotNull
    private Integer userCode;

    private String category;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, 
                pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", 
                timezone="UTC")
    private Date date;

    public Spend() { }

    public Spend(ObjectId _id, 
                 String description, 
                 BigDecimal value, 
                 int userCode, 
                 String category, 
                 Date date) {
        this._id = _id;
        this.description = description;
        this.value = value;
        this.userCode = userCode;
        this.category = category;
        this.date = date;
    }

    public String get_id() { return _id.toHexString(); }

    public String getDescription() { return description; }

    public BigDecimal getValue() { return value; }

    public Integer getUserCode() { return userCode; }

    public String getCategory() { return category; }

    public Date getDate() { return date; }

    public void set_id(ObjectId _id) { this._id = _id; }

    public void setDescription(String description) { this.description = description; }

    public void setValue(BigDecimal value) { this.value = value.setScale(2, BigDecimal.ROUND_HALF_UP); }

    public void setUserCode(int userCode) { this.userCode = userCode; }

    public void setCategory(String category) { this.category = category; }

    public void setDate(Date date) { this.date = date; }
}