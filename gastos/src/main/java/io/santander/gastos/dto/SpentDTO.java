package io.santander.gastos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpentDTO {
    private Long id;
    private Long userCode;
    private String cardNumber;
    private String description;
    private Double spentValue;
    private Date spentDate;
}
