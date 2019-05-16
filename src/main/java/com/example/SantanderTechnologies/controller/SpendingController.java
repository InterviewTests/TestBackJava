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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/spending")
public class SpendingController {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    SpendingRepository spendingRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping
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

    @GetMapping("/filterbydate/{date}")
    public List<Spending> filterSpendingByDate(@CurrentUser UserPrincipal currentUser, @PathVariable @DateTimeFormat(pattern = "ddMMyyyy") Date date){

        List<CreditCard> creditCards = creditCardRepository.findAllByUserId(currentUser.getId());
        List<Spending> spendings = new ArrayList<>();
        for(CreditCard c : creditCards){
            List<Spending> s = spendingRepository.findAllByCreditCardId(c.getId());

            s = s.stream().filter(
                    p -> {
                        Calendar cal1 = Calendar.getInstance();
                        Calendar cal2 = Calendar.getInstance();
                        cal1.setTime(p.getDate());
                        cal2.setTime(date);
                        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);}
                        ).collect(Collectors.toList());
            spendings.addAll(s);
        }
        return spendings;
    }
}
