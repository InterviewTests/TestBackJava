package com.example.SantanderTechnologies.controller;

import com.example.SantanderTechnologies.model.CreditCard;
import com.example.SantanderTechnologies.model.Spending;
import com.example.SantanderTechnologies.model.SpendingCategory;
import com.example.SantanderTechnologies.payload.ApiResponse;
import com.example.SantanderTechnologies.payload.SpendingRequest;
import com.example.SantanderTechnologies.repository.CategoryRepository;
import com.example.SantanderTechnologies.repository.CreditCardRepository;
import com.example.SantanderTechnologies.repository.SpendingRepository;
import com.example.SantanderTechnologies.security.CurrentUser;
import com.example.SantanderTechnologies.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SpendingController {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    SpendingRepository spendingRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/spending")
    public ApiResponse addNewSpending(@CurrentUser UserPrincipal currentUser, @RequestBody SpendingRequest spendingRequest){
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findByNumber(spendingRequest.getCreditCardNumber());
        if(!optionalCreditCard.isPresent()){
            return new ApiResponse(false, "Could not find credit card");
        }

        CreditCard creditCard = optionalCreditCard.get();
        SpendingCategory spendingCategory;
        Optional<SpendingCategory> optionalSpendingCategory = categoryRepository.findByCategoryName(spendingRequest.getCategory());
        if(!optionalSpendingCategory.isPresent()){
            SpendingCategory sc = new SpendingCategory();
            sc.setCategoryName(spendingRequest.getCategory());
            categoryRepository.save(sc);
            spendingCategory = sc;
        }
        else {
            spendingCategory = optionalSpendingCategory.get();
        }
        Spending spending = new Spending(spendingRequest.getDescription(),
                spendingRequest.getAmount(),
                spendingCategory,
                spendingRequest.getDate(),
                creditCard);
        spendingRepository.save(spending);

        return new ApiResponse(true, "Spending Created");

    }
}
