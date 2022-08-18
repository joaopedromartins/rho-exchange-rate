package com.example.demo.exchangerate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class ExchangeRateService {

    @Autowired
    private RestTemplate restTemplate;


    /**
     * Get Exchange rate from Currency A to Currency B
     * RESTful consumer from REST API https://api.exchangerate.host/convert
     * see API documentation at https://exchangerate.host/#/#docs
     *
     * @param from Currency A
     * @param to Currency B
     * @return exchange rate
     */
    public BigDecimal getExchangeRate(String from, String to) {

        // request url
        String url_str = "https://api.exchangerate.host/convert?from={currencyA}&to={currencyB}";

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // send GET request using getForObject
        String response = restTemplate.getForObject(url_str, String.class, from, to);

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
     * @param base Currency A
     * @return exchange rates list
     */
    public String getAllExchangeRates(String base) {

        // request url
        String url_str = "https://api.exchangerate.host/latest?base={currencyA}";

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // send GET request using getForObject
        String response = restTemplate.getForObject(url_str, String.class, base);

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
    public BigDecimal getValueConversion(String from, String to, String amount) {

        // request url
        String url_str = "https://api.exchangerate.host/convert?from={currencyA}&to={currencyB}&amount={amount}";

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // send GET request using getForObject
        String response = restTemplate.getForObject(url_str, String.class, from, to, amount);

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
    public String getValueConversionCurrenciesList(String from, String[] to, String amount) {


        // request url
        String url_str = "https://api.exchangerate.host/latest?base={currencyA}&amount={amount}";

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // send GET request using getForObject
        String response = restTemplate.getForObject(url_str, String.class, from, amount);

        // using Gson JsonParser to get rates list
        JsonElement root = JsonParser.parseString(response);
        JsonObject jsonObject = root.getAsJsonObject();
        JsonObject jsonRates = jsonObject.getAsJsonObject("rates");

        // filter rates/values to list of supplied currencies
        StringBuilder result = new StringBuilder("{");
        boolean first = true;
        for (String currency: to) {
            String value = jsonRates.get(currency).getAsString();
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
