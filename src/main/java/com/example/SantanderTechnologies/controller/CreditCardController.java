package com.example.SantanderTechnologies.controller;

import com.example.SantanderTechnologies.model.CreditCard;
import com.example.SantanderTechnologies.payload.ApiResponse;
import com.example.SantanderTechnologies.payload.CreditCardResponse;
import com.example.SantanderTechnologies.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/creditcard")
public class CreditCardController {

    @Autowired
    CreditCardRepository creditCardRepository;

    @GetMapping("/details/{number}")
    public ApiResponse getCreditCard(@PathVariable String number){
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findByNumber(number);
        if(!optionalCreditCard.isPresent()){
            return new ApiResponse(false, "Could not find credit card");
        }

        CreditCard creditCard = optionalCreditCard.get();

        CreditCardResponse creditCardResponse = new CreditCardResponse(true, "Success");
        creditCardResponse.setAmount(creditCard.getTotalAmount());
        creditCardResponse.setDate(creditCard.getDuoDate());
        creditCardResponse.setDescription(creditCard.getClientName());
        return creditCardResponse;
    }
}
