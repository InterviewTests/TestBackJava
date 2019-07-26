package santander.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@RedisHash("Expense")
public class Expense implements Serializable {

    private @Id         String id;
    private             String description;
    private             Double value;
    private @Indexed    Long userCode;
    private @Indexed    Date date;
    private             Category category;
}
