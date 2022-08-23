package com.example.demo.exchangerate;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "api/v1")
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
    @GetMapping("/exchangerate")
    public BigDecimal getExchangeRate(EnumCurrency from, EnumCurrency to) {
        return exchangeRateService.getExchangeRate(from, to);
    }


    /**
     * Controller to get all exchange rates from Currency A
     *
     * @param from Currency A
     * @return list of all exchange rates
     */
    @ApiOperation("Get all exchange rates from Currency A")
    @GetMapping("/allexchangerates")
    public String getAllExchangeRates(EnumCurrency from) {
        return exchangeRateService.getAllExchangeRates(from.toString());
    }


    /**
     * Controller to get value conversion from Currency A to Currency B
     *
     * @param from Currency A
     * @param to Currency B
     * @param amount of Currency A
     * @return value conversion
     */
    @ApiOperation("Get value conversion from Currency A to Currency B")
    @GetMapping("/valueconversion")
    public BigDecimal getValueConversion(EnumCurrency from, EnumCurrency to, BigDecimal amount) {
        return exchangeRateService.getValueConversion(from, to, amount);
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
    @GetMapping("/valueconversionlist")
    public String getValueConversionCurrenciesList(EnumCurrency from, EnumCurrency[] to, BigDecimal amount) {
        return exchangeRateService.getValueConversionCurrenciesList(from, to, amount);
    }
}
