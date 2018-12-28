package br.com.fellipeoliveira.expensemanagement.domains;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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

  private String category;

  private double value;

  private long userCode;

  private LocalDate date;

  private LocalTime time;

}
