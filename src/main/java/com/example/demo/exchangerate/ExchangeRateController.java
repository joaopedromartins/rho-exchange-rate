package com.example.demo.exchangerate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "api/v1/exchangerate")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }


    /**
     * Controller to get exchange rate from Currency A to Currency B
     *
     * @param from Currency A
     * @param to Currency B
     * @return exchange rate
     */
    @ApiOperation("Get exchange rate from Currency A to Currency B")
    @GetMapping
    public BigDecimal getExchangeRate(EnumCurrency from, EnumCurrency to) {
        return exchangeRateService.getExchangeRate(from, to);
    }

}
