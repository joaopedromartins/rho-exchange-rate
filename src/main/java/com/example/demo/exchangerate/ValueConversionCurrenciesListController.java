package com.example.demo.exchangerate;

import io.swagger.annotations.ApiOperation;
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


    /**
     * Controller to get value conversion from Currency A to a list of supplied currencies
     *
     * @param from Currency A
     * @param to list of supplied currencies
     * @param amount of Currency A
     * @return value conversion list
     */
    @ApiOperation("Get value conversion from Currency A to a list of supplied currencies")
    @GetMapping
    public String getValueConversionCurrenciesList(EnumCurrency from, EnumCurrency[] to, BigDecimal amount) {
        return exchangeRateService.getValueConversionCurrenciesList(from, to, amount);
    }

}
