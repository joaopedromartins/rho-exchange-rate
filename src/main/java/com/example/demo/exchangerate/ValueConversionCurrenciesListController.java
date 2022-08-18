package com.example.demo.exchangerate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "api/v1/valueconversionlist")
public class ValueConversionCurrenciesListController {
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ValueConversionCurrenciesListController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }


    @GetMapping
    public String getValueConversionCurrenciesList(String base, String[] to, BigDecimal amount) {
        return exchangeRateService.getValueConversionCurrenciesList(base, to, amount.toString());
    }

}
