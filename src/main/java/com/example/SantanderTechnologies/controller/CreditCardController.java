package com.example.SantanderTechnologies.controller;

import com.example.SantanderTechnologies.model.CreditCard;
import com.example.SantanderTechnologies.model.Spending;
import com.example.SantanderTechnologies.payload.ApiResponse;
import com.example.SantanderTechnologies.payload.CreditCardResponse;
import com.example.SantanderTechnologies.repository.CreditCardRepository;
import com.example.SantanderTechnologies.repository.SpendingRepository;
import com.example.SantanderTechnologies.security.CurrentUser;
import com.example.SantanderTechnologies.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/creditcard")
public class CreditCardController {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    SpendingRepository spendingRepository;

    @GetMapping("/details/{number}")
    public ApiResponse getCreditCard(@CurrentUser UserPrincipal currentUser, @PathVariable String number){
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findByNumber(number);
        if(!optionalCreditCard.isPresent()){
            return new ApiResponse(false, "Could not find credit card");
        }

        CreditCard creditCard = optionalCreditCard.get();

        List<Spending> spendings = spendingRepository.findAllByCreditCardId(creditCard.getId());
        BigDecimal totalSpending = new BigDecimal(0);
        for(Spending s : spendings){
            totalSpending = totalSpending.add(s.getValue());
        }

        CreditCardResponse creditCardResponse = new CreditCardResponse(true, "Success");
        creditCardResponse.setAmount(totalSpending);
        creditCardResponse.setDate(creditCard.getDuoDate());
        creditCardResponse.setUserCode(currentUser.getId());
        creditCardResponse.setDescription(creditCard.getClientName() + " " + creditCard.getUser());
        return creditCardResponse;
    }
}
