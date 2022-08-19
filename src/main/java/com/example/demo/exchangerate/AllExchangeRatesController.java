package com.example.demo.exchangerate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    /**
     * Controller to get all exchange rates from Currency A
     *
     * @param currency Currency A
     * @return list of all exchange rates
     */
    @ApiOperation("Get all exchange rates from Currency A")
    @GetMapping
    public String getAllExchangeRates(EnumCurrency currency) {
        return exchangeRateService.getAllExchangeRates(currency);
    }

}
