package org.springframework.boot.cliente.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_id_seq")
    @SequenceGenerator(name = "categoria_id_seq", sequenceName = "categoria_id_seq", allocationSize = 1)
    @Column(name = "categotia_id")
    private Long id;

    @NotNull(message = "categoria não pode ser nula")
    private String categoria;

    @NotNull(message = "categoria não pode ser nula")
    private String descricao;


}
