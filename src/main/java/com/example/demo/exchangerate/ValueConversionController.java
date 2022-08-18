package com.example.demo.exchangerate;

import io.swagger.annotations.ApiOperation;
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


    /**
     * Controller to get value conversion from Currency A to Currency B
     *
     * @param from Currency A
     * @param to Currency B
     * @param amount of Currency A
     * @return value conversion
     */
    @ApiOperation("Get value conversion from Currency A to Currency B")
    @GetMapping
    public BigDecimal getValueConversion(String from, String to, BigDecimal amount) {
        return exchangeRateService.getValueConversion(from, to, amount.toString());
    }

}
