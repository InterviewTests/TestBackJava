package br.com.fellipeoliveira.expensemanagement.domains;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Spending implements Serializable {

  private static final long serialVersionUID = -3262273344255968755L;

  @Id
  private String id;

  private String description;

  private double value;

  private int userCode;

  private LocalDateTime date; //formato UTC

}
