package com.example.SantanderTechnologies.payload;

import com.example.SantanderTechnologies.model.Spending;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SpendingListResponse {

    @JsonProperty("listagemDeGastos")
    private List<Spending> spendingList;
}
