package com.example.demo.exchangerate;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/allexchangerates")
public class AllExchangeRatesController {
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public AllExchangeRatesController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }


    @GetMapping
    public String getAllExchangeRates(String base) {
        return exchangeRateService.getAllExchangeRates(base);
    }

}
