package br.com.santander.spendingmanager.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class Spending {

    private UUID id;
    private String description;
    private Double value;
    private int userCode;
    private LocalDate date;
}
