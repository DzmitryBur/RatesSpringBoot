package com.bpc.ratesspringboot.controller;

import com.bpc.ratesspringboot.model.RatesModel;
import com.bpc.ratesspringboot.responsemodel.ResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;



@RestController
@RequestMapping
public class RateController {



    @GetMapping(value = "/ratesondate")
    public List<Object> getAllRatesOnDate(String ondate) {
        String uri = String.format("https://www.nbrb.by/api/exrates/rates?ondate=%s&periodicity=0", ondate);
        RestTemplate restTemplate = new RestTemplate();
        Object ratesondate = restTemplate.getForObject(uri, Object[].class);
        return Arrays.asList(ratesondate);
    }

    @GetMapping(value = "/ratebycodeandondate")
        public ResponseModel getRateByCodeAndOnDate (String cur, String ondate) throws IOException {
        String uri = String.format("https://www.nbrb.by/api/exrates/rates/%s?ondate=%s&parammode=2", cur, ondate);
        RestTemplate restTemplate = new RestTemplate();
        String currentRate = restTemplate.getForObject(uri, String.class);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.parse(ondate, formatter);
        LocalDate previousDate  = currentDate.minusDays(1);

        String uriDiff = String.format("https://www.nbrb.by/api/exrates/rates/%s?ondate=%s&parammode=2", cur, previousDate);
        String previousRate = restTemplate.getForObject(uriDiff, String.class);

        ObjectMapper om = new ObjectMapper();
        StringReader reader = new StringReader(currentRate);
        RatesModel currentRateModel = om.readValue(reader, RatesModel.class);
        reader = new StringReader(previousRate);
        RatesModel previousRateModel = om.readValue(reader, RatesModel.class);
        int changeCondition = currentRateModel.Cur_OfficialRate > previousRateModel.Cur_OfficialRate ? 0 :
                currentRateModel.Cur_OfficialRate < previousRateModel.Cur_OfficialRate ? 1 : 2;
        ResponseModel responseModel = new ResponseModel();
        responseModel.currentRate = currentRate;
        responseModel.previousRate = previousRate;
        switch (changeCondition){
            case 0:
                responseModel.change = "Current rate is higher than previous";
                break;
            case 1:
                responseModel.change = "Current rate is lower than previous";
                break;
            case 2:
                responseModel.change = "Current rate equals to previous";
                break;
        }

        return responseModel;
    }
}
