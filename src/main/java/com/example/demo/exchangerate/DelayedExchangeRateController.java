package com.example.demo.exchangerate;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "api/v1/delayed")
public class DelayedExchangeRateController {

    private final DelayedExchangeRateService delayedExchangeRateService;

    @Autowired
    public DelayedExchangeRateController(DelayedExchangeRateService exchangeRateService) {
        this.delayedExchangeRateService = exchangeRateService;
    }


    /**
     * Delayed Controller to get exchange rate from Currency A to Currency B
     *
     * @param server selection
     * @param from Currency A
     * @param to Currency B
     * @return exchange rate
     */
    @ApiOperation("Get exchange rate from Currency A to Currency B")
    @GetMapping("/exchangerate")
    public BigDecimal getExchangeRate(EnumExchangeServer server, EnumCurrency from, EnumCurrency to) {
        return delayedExchangeRateService.getExchangeRate(server, from, to);
    }

    /**
     * Delayed Controller to get all exchange rates from Currency A
     *
     * @param server selection
     * @param from Currency A
     * @return list of all exchange rates
     */
    @ApiOperation("Get all exchange rates from Currency A")
    @GetMapping("/allexchangerates")
    public String getAllExchangeRates(EnumExchangeServer server, EnumCurrency from) {
        return delayedExchangeRateService.getAllExchangeRates(server, from);
    }


    /**
     * Delayed Controller to get value conversion from Currency A to Currency B
     *
     * @param server selection
     * @param from Currency A
     * @param to Currency B
     * @param amount of Currency A
     * @return value conversion
     */
    @ApiOperation("Get value conversion from Currency A to Currency B")
    @GetMapping("/valueconversion")
    public BigDecimal getValueConversion(EnumExchangeServer server, EnumCurrency from, EnumCurrency to, BigDecimal amount) {
        return delayedExchangeRateService.getValueConversion(server, from, to, amount);
    }


    /**
     * Delayed Controller to get value conversion from Currency A to a list of supplied currencies
     *
     * @param server selection
     * @param from Currency A
     * @param to list of supplied currencies
     * @param amount of Currency A
     * @return value conversion list
     */
    @ApiOperation("Get value conversion from Currency A to a list of supplied currencies")
    @GetMapping("/valueconversionlist")
    public String getValueConversionCurrenciesList(EnumExchangeServer server, EnumCurrency from, EnumCurrency[] to, BigDecimal amount) {
        return delayedExchangeRateService.getValueConversionCurrenciesList(server, from, to, amount);
    }
}
