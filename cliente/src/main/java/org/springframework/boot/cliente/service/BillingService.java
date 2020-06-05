package org.springframework.boot.cliente.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.cliente.dto.BillingDTO;

import org.springframework.boot.cliente.repository.BillingRepository;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Service
@Slf4j
public class BillingService {

    @Autowired
    BillingRepository billingRepository;

    public void saveOrder(Object consumerRecord) {

        try{
            JSONObject obj = new JSONObject(consumerRecord.toString());

            BillingDTO billingDTO = new BillingDTO();
            billingDTO.setCodigousuario(obj.getLong("codigousuario"));
            billingDTO.setDescricao(obj.getString("descricao"));
            billingDTO.setValor(Integer.parseInt(obj.getString("valor").replace(",",".")));
            String jsonDate = obj.getString("data");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            Date date = formatter.parse(jsonDate);
            billingDTO.setData(date);

            billingRepository.save(billingDTO.billingDtoToBilling());
        } catch (Exception e){
            System.out.println("Nao foi possivel salvar a ordem: " + consumerRecord);
        }
    }

}
