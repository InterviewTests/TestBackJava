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
@Table(name = "spent")
@EntityListeners(AuditingEntityListener.class)
public class Spent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    private Double spentValue;
    private Date spentDate;
    @JoinColumn(name = "classification")
    @ManyToOne
    private Classification classification;
    @CreatedDate
    @Column(name = "dat_creation", updatable = false, nullable = false)
    private Date creationTime;

    @LastModifiedDate
    @Column(name = "dat_update", nullable = false)
    private Date updateTime;
}