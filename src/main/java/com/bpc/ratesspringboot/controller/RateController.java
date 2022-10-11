package com.bpc.ratesspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping
public class RateController {

    @GetMapping(value = "/ratesondate/{YYYY-MM-DD}")
//    public String getAllRatesOnDate() { //по умолчанию получим перечень валют в виде текстового файла
    public List<Object> getAllRatesOnDate(String ondate) { // получаем массив json'ов
        String uri = String.format("https://www.nbrb.by/api/exrates/rates?ondate=%s&periodicity=0", ondate);
        RestTemplate restTemplate = new RestTemplate();
        Object ratesondate = restTemplate.getForObject(uri, Object[].class);
//        String result = restTemplate.getForObject(uri, String.class);
//        return result;
        return Arrays.asList(ratesondate);
    }

    @GetMapping(value = "/ratebycodeandondate/{ABC}/{yyyy-mm-dd}")
//    public List<Object> getRateByCodeAndOnDate(String cur, String ondate) {
        public String getRateByCodeAndOnDate (String cur, String ondate) {
        String uri = String.format("https://www.nbrb.by/api/exrates/rates/%s?ondate=%s&parammode=2", cur, ondate);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
//        Object getRateByCodeAndOnDate = restTemplate.getForObject(uri, Object[].class);
//        return Arrays.asList(getRateByCodeAndOnDate);
    }
}
