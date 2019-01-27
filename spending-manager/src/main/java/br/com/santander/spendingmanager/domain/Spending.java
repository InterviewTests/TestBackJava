package br.com.santander.spendingmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("spending")
public class Spending implements Serializable {

    @PrimaryKey
    @Column("id")
    private UUID id;

    @Column("description")
    private String description;

    @Column("value")
    private Double value;

    @Column("userCode")
    private int userCode;

    @Column("date")
    private LocalDateTime date;

    @Column("category")
    private String category;
}
