package io.santander.gastos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "gasto")
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long codigoUsuario;
    private String descricao;
    private Double valor;
    private Date data;
    @CreatedDate
    @Column(name = "dat_creation", updatable = false, nullable = false)
    private Date creationTime;

    @LastModifiedDate
    @Column(name = "dat_update", nullable = false)
    private Date updateTime;
}
