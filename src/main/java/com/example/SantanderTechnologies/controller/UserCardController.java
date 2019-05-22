package com.example.SantanderTechnologies.controller;

import com.example.SantanderTechnologies.model.CreditCard;
import com.example.SantanderTechnologies.model.User;
import com.example.SantanderTechnologies.payload.ApiResponse;
import com.example.SantanderTechnologies.payload.CreditCardRequest;
import com.example.SantanderTechnologies.payload.UserSummary;
import com.example.SantanderTechnologies.repository.CreditCardRepository;
import com.example.SantanderTechnologies.repository.UserRepository;
import com.example.SantanderTechnologies.security.CurrentUser;
import com.example.SantanderTechnologies.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserCardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @PostMapping("/user/newCreditCard")
    public ApiResponse createNewCreditCard(@CurrentUser UserPrincipal currentUser, @RequestBody CreditCardRequest creditCardRequest) {

        CreditCard creditCard = new CreditCard(creditCardRequest.getClientName(),
                                                creditCardRequest.getNumber(),
                                                creditCardRequest.getValidationCode(),
                                                creditCardRequest.getDuoDate());
        Optional<User> optionalUser = userRepository.findById(currentUser.getId());
        if(!optionalUser.isPresent()){
            return new ApiResponse(true, "Could not find current user");
        }
        creditCard.setUser(optionalUser.get());
        creditCardRepository.save(creditCard);
        return new ApiResponse(true, "Credit Card created");
    }

    @GetMapping("/user/creditCards")
    public List<CreditCard> getCreditCards(@CurrentUser UserPrincipal currentUser){
        List<CreditCard> creditCards = creditCardRepository.findAllByUserId(currentUser.getId());

        return creditCards;
    }
}
