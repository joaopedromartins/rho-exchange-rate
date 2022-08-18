package com.example.demo.exchangerate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "api/v1/valueconversion")
public class ValueConversionController {
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ValueConversionController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }


    @GetMapping
    public String getValueConversion(String from, String to, BigDecimal amount) {
        return exchangeRateService.getValueConversion(from, to, amount.toString());
    }

}
