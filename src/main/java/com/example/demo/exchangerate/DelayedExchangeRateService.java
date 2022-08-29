package com.example.demo.exchangerate;

import com.example.demo.config.CacheConfig;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import springfox.documentation.annotations.Cacheable;

import java.math.BigDecimal;

@Service
public class DelayedExchangeRateService {

    @Value("${ratedatasource.url}")
    private String customUrl_str;
    @Value("${ratedatasource.uripath}")
    private String customUriPath_str;
    @Value("${ratedatasource.ratesjsonpath}")
    private String ratesJsonPath_str;


    /**
     * Get Delayed Exchange rate from Currency A to Currency B
     *
     * @param server selection
     * @param from Currency A
     * @param to Currency B
     * @return exchange rate
     */
    public BigDecimal getExchangeRate(EnumExchangeServer server, EnumCurrency from, EnumCurrency to) {

        String rates;
        if(server == EnumExchangeServer.ExchangeRateHost) {
            rates = getAllRatesFromExchangeRateHost(from.getCurrency());
        } else {
            rates = getAllRatesFromCustomHost(from.getCurrency());
        }

        // using Gson JsonParser to get rate
        JsonElement root = JsonParser.parseString(rates);
        JsonObject jsonRates = root.getAsJsonObject();
        String rate = jsonRates.get(to.getCurrency()).getAsString();

        return new BigDecimal(rate);
    }

    /**
     * Get Delayed All Exchange rates from Currency A
     * RESTful consumer from REST API https://api.exchangerate.host/latest
     * See API documentation at https://exchangerate.host/#/#docs
     *
     * @param server selection
     * @param from Currency A
     * @return exchange rates list
     */
    public String getAllExchangeRates(EnumExchangeServer server, EnumCurrency from) {
        if(server == EnumExchangeServer.ExchangeRateHost) {
            return getAllRatesFromExchangeRateHost(from.getCurrency());
        } else {
            return getAllRatesFromCustomHost(from.getCurrency());
        }
    }

    /**
     * Get Delayed value conversion from Currency A to Currency B
     *
     * @param server selection
     * @param from Currency A
     * @param to Currency B
     * @param amount value to convert from currency A
     * @return value conversion
     */
    public BigDecimal getValueConversion(EnumExchangeServer server, EnumCurrency from, EnumCurrency to, BigDecimal amount) {
        String rates;
        if(server == EnumExchangeServer.ExchangeRateHost) {
            rates = getAllRatesFromExchangeRateHost(from.getCurrency());
        } else {
            rates = getAllRatesFromCustomHost(from.getCurrency());
        }

        // using Gson JsonParser to get rates list
        JsonElement root = JsonParser.parseString(rates);
        JsonObject jsonRates = root.getAsJsonObject();
        String rateStr = jsonRates.get(to.getCurrency()).getAsString();

        BigDecimal rate = new BigDecimal(rateStr);

        return rate.multiply(amount);
    }

    /**
     * Get Delayed value conversion from Currency A to a list of supplied currencies
     * RESTful consumer from REST API https://api.exchangerate.host/latest
     * See API documentation at https://exchangerate.host/#/#docs
     *
     * @param from  Currency A
     * @param to list of supplied currencies
     * @param amount value to convert from currency A
     * @return value conversion list to supplied currencies
     */
    public String getValueConversionCurrenciesList(EnumExchangeServer server, EnumCurrency from, EnumCurrency[] to, BigDecimal amount) {
        String rates;
        if(server == EnumExchangeServer.ExchangeRateHost) {
            rates = getAllRatesFromExchangeRateHost(from.getCurrency());
        } else {
            rates = getAllRatesFromCustomHost(from.getCurrency());
        }

        // using Gson JsonParser to get rates list
        JsonElement root = JsonParser.parseString(rates);
        JsonObject jsonRates = root.getAsJsonObject();

        // filter rates/values to list of supplied currencies
        StringBuilder result = new StringBuilder("{");
        boolean first = true;
        for (EnumCurrency currency: to) {
            String rate_str = jsonRates.get(currency.toString()).getAsString();
            if (rate_str != null  &&  !rate_str.isEmpty()) {
                if(first) {
                    first = false;
                } else {
                    result.append(", ");
                }
                BigDecimal rate = new BigDecimal(rate_str);
                String value = rate.multiply(amount).toString();
                result.append("\"").append(currency).append("\":").append(value);
            }
        }
        result.append("}");

        return result.toString();
    }



    /**
     * Get Delayed All Exchange rates from Currency A
     * RESTful consumer from REST API customized on
     * application.properties
     *
     * @param currency from Currency A
     * @return exchange rates list
     */@Cacheable(CacheConfig.CACHE_ONE_MINUTE)
    public String getAllRatesFromCustomHost(String currency) {

        // request path for WebClient UriBuilder
        String uri_str = customUriPath_str + "{currencyA}";

        // create an instance of WebClient
        WebClient webClient = WebClient.create(customUrl_str);

        // send GET request
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri_str)
                        .build(currency))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // using Gson JsonParser to get rates list
        JsonElement root = JsonParser.parseString(response);
        JsonObject jsonObject = root.getAsJsonObject();
        JsonObject jsonRates = jsonObject.getAsJsonObject(ratesJsonPath_str);
        String rates = jsonRates.toString();

        return rates;

    }

    /**
     * Get Delayed All Exchange rates from Currency A
     * RESTful consumer from REST API https://api.exchangerate.host/latest
     * See API documentation at https://exchangerate.host/#/#docs
     *
     * @param from Currency A
     * @return exchange rates list
     */
    @Cacheable(CacheConfig.CACHE_ONE_MINUTE)
    public String getAllRatesFromExchangeRateHost(String from) {

        // request url https://api.exchangerate.host/latest?base={currencyA}
        String url_str = "https://api.exchangerate.host";
        String uri_str = "/latest?base={currencyA}";

        // create an instance of WebClient
        WebClient webClient = WebClient.create(url_str);

        // send GET request
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                                .path(uri_str)
                                .build(from))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // using Gson JsonParser to get rates list
        JsonElement root = JsonParser.parseString(response);
        JsonObject jsonObject = root.getAsJsonObject();
        JsonObject jsonRates = jsonObject.getAsJsonObject("rates");
        String rates = jsonRates.toString();

        return rates;
    }
}
