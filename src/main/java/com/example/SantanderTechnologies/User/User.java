package com.example.SantanderTechnologies.User;

import com.example.SantanderTechnologies.CreditCard.CreditCard;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<CreditCard> creditCards;
}
