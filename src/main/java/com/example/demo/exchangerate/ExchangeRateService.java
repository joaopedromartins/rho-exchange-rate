package com.example.demo.exchangerate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Service
public class ExchangeRateService {


    /**
     * Get Exchange rate from Currency A to Currency B
     * RESTful consumer from REST API https://api.exchangerate.host/convert
     * see API documentation at https://exchangerate.host/#/#docs
     *
     * @param from Currency A
     * @param to Currency B
     * @return exchange rate
     */
    public BigDecimal getExchangeRate(EnumCurrency from, EnumCurrency to) {

        // request url https://api.exchangerate.host/convert?from={currencyA}&to={currencyB}
        String url_str = "https://api.exchangerate.host";
        String uri_str = "/convert";

        // create an instance of WebClient
        WebClient webClient = WebClient.create(url_str);

        // send GET request on WebClient
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri_str)
                        .queryParam("from", from.getCurrency())
                        .queryParam("to", to.getCurrency())
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // using Gson JsonParser to get result
        JsonElement root = JsonParser.parseString(response);
        JsonObject jsonObject = root.getAsJsonObject();
        String result = jsonObject.get("result").getAsString();

        return new BigDecimal(result);
    }


    /**
     * Get All Exchange rates from Currency A
     * RESTful consumer from REST API https://api.exchangerate.host/latest
     * See API documentation at https://exchangerate.host/#/#docs
     *
     * @param from Currency A
     * @return exchange rates list
     */
    public String getAllExchangeRates(String from) {

        // request url https://api.exchangerate.host/latest?base={currencyA}
        String url_str = "https://api.exchangerate.host";
        String uri_str = "/latest";

        // create an instance of WebClient
        WebClient webClient = WebClient.create(url_str);

        // send GET request on WebClient
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri_str)
                        .queryParam("base", from)
                        .build())
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


    /**
     * Get value conversion from Currency A to Currency B
     * RESTful consumer from REST API https://api.exchangerate.host/convert
     * See API documentation at https://exchangerate.host/#/#docs
     *
     * @param from Currency A
     * @param to Currency B
     * @param amount value to convert from currency A
     * @return value conversion
     */
    public BigDecimal getValueConversion(EnumCurrency from, EnumCurrency to, BigDecimal amount) {

        // request url https://api.exchangerate.host/convert?from={currencyA}&to={currencyB}&amount={amount}
        String url_str = "https://api.exchangerate.host";
        String uriPath_str = "/convert";

        // create an instance of WebClient
        WebClient webClient = WebClient.create(url_str);

        // send GET request on WebClient
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(uriPath_str)
                        .queryParam("from", from.getCurrency())
                        .queryParam("to", to.getCurrency())
                        .queryParam("amount", amount.toString())
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // using Gson JsonParser to get result
        JsonElement root = JsonParser.parseString(response);
        JsonObject jsonObject = root.getAsJsonObject();
        String result = jsonObject.get("result").getAsString();

        return new BigDecimal(result);
    }

    /**
     * Get value conversion from Currency A to a list of supplied currencies
     * RESTful consumer from REST API https://api.exchangerate.host/latest
     * See API documentation at https://exchangerate.host/#/#docs
     *
     * @param from  Currency A
     * @param to list of supplied currencies
     * @param amount value to convert from currency A
     * @return value conversion list to supplied currencies
     */
    public String getValueConversionCurrenciesList(EnumCurrency from, EnumCurrency[] to, BigDecimal amount) {

        // request url https://api.exchangerate.host/latest?base={currencyA}&amount={amount}
        String url_str = "https://api.exchangerate.host";
        String uriPath_str = "/latest";

        // create an instance of WebClient
        WebClient webClient = WebClient.create(url_str);

        // send GET request on WebClient
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(uriPath_str)
                        .queryParam("base", from.getCurrency())
                        .queryParam("amount", amount.toString())
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // using Gson JsonParser to get rates list
        JsonElement root = JsonParser.parseString(response);
        JsonObject jsonObject = root.getAsJsonObject();
        JsonObject jsonRates = jsonObject.getAsJsonObject("rates");

        // filter rates/values to list of supplied currencies
        StringBuilder result = new StringBuilder("{");
        boolean first = true;
        for (EnumCurrency currency: to) {
            String value = jsonRates.get(currency.toString()).getAsString();
            if (value != null  &&  !value.isEmpty()) {
                if(first) {
                    first = false;
                } else {
                    result.append(", ");
                }
                result.append("\"").append(currency).append("\":").append(value);
            }
        }
        result.append("}");

        return result.toString();
    }
}
