package io.santander.gastos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSpentDTO {
    private Long id;

    private CreditCardDTO creditCard;

    private SpentDTO spent;
}