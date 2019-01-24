package br.com.santander.spendingmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("spending")
public class Spending implements Serializable {

    @PrimaryKey
    private UUID id;

    private String description;
    private Double value;
    private int userCode;
    private LocalDate date;
}
