package io.santander.gastos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card_spent")
@EntityListeners(AuditingEntityListener.class)
public class CardSpent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card")
    private CreditCard creditCard;

    @OneToOne
    @JoinColumn(name = "spent")
    private Spent spent;

    @CreatedDate
    @Column(name = "dat_creation", updatable = false, nullable = false)
    private Date creationTime;

    @LastModifiedDate
    @Column(name = "dat_update", nullable = false)
    private Date updateTime;
}
