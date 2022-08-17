package com.example.demo.exchangerate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateService {

    @Autowired
    private RestTemplate restTemplate;

    public String getExchangeRate(String from, String to) {

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

        return result;
    }
}
