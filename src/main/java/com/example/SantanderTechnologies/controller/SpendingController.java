package com.example.SantanderTechnologies.controller;

import com.example.SantanderTechnologies.model.CreditCard;
import com.example.SantanderTechnologies.model.Spending;
import com.example.SantanderTechnologies.model.SpendingCategory;
import com.example.SantanderTechnologies.payload.ApiResponse;
import com.example.SantanderTechnologies.payload.EditSpendingRequest;
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
        String spendingCategoryName = spendingRequest.getCategory();
        if(spendingCategoryName == null || spendingRequest.getDescription() != null){
            Optional<Spending> optionalSpending = spendingRepository.findFirstByDescription(spendingRequest.getDescription());
            if(optionalSpending.isPresent() && optionalSpending.get().getCategory() != null){
                spendingCategoryName = optionalSpending.get().getCategory().getCategoryName();
            }
        }

        Optional<SpendingCategory> optionalSpendingCategory = categoryRepository.findByCategoryName(spendingCategoryName);
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

    @GetMapping("/nocategory/{cardnumber}")
    public List<Spending> getSpendingWithoutCategory(@CurrentUser UserPrincipal currentUser, @PathVariable String cardnumber){
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findByNumber(cardnumber);
        if (!optionalCreditCard.isPresent()){
            return null; //TODO make a default Api Response
        }

        List<Spending> spendings = spendingRepository.findAllByCreditCardId(optionalCreditCard.get().getId());

        return spendings
                .stream()
                .filter(spending ->
                        spending.getCategory() == null || spending.getCategory().getCategoryName() == null)
                .collect(Collectors.toList());


    }

    @PostMapping("/edit")
    public ApiResponse editSpending(@CurrentUser UserPrincipal currentUser, @RequestBody EditSpendingRequest editSpendingRequest){

        Optional<Spending> optionalSpending = spendingRepository.findById(editSpendingRequest.getSpendingId());

        if(!optionalSpending.isPresent()){
            return new ApiResponse(false, "Could not find spending");
        }

        Spending spending = optionalSpending.get();
        SpendingCategory newSpendingCategory = new SpendingCategory(editSpendingRequest.getCategory());

        spending.setCategory(newSpendingCategory);
        categoryRepository.save(newSpendingCategory);
        spendingRepository.save(spending);

        return new ApiResponse(true, "Spending category edited.");
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
