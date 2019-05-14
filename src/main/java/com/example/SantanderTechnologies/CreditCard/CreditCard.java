package com.example.SantanderTechnologies.CreditCard;

import com.example.SantanderTechnologies.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private User user;
}
