package org.springframework.boot.cliente.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing_id_seq")
    @SequenceGenerator(name = "billing_id_seq", sequenceName = "billing_id_seq", allocationSize = 1)
    private Long id;

    @NotNull(message = "Usuario não pode ser nulo")
    private Long codigousuario;

    @NotNull(message = "Descriçao não pode ser nula")
    private String descricao;

    @NotNull(message = "Valor não pode ser nulo")
    private Integer valor;

    @NotNull(message = "Data não pode ser nula")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date data;


}
