package com.example.demo.exchangerate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/exchangerate")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }


    /**
     * Get exchange rate from Currency A to Currency B
     *
     * @param from Currency A
     * @param to Currency B
     * @return exchange rate
     */
    @GetMapping
    public String getExchangeRate(String from, String to) {
        return exchangeRateService.getExchangeRate(from, to);
    }

}
