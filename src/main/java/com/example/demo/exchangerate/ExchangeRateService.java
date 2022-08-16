package com.example.demo.exchangerate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateService {

    private static String url_str = "https://api.exchangerate.host/convert";

    @Autowired
    private RestTemplate restTemplate;

    public String getExchangeRate(String from, String to) {
//        String url_str = "https://api.exchangerate.host/convert?from=EUR&to=USD";
//
//        URL url = new URL(url_str);
//        HttpURLConnection request = (HttpURLConnection) url.openConnection();
//        request.connect();
//
//        JsonParser jp = new JsonParser();
//        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
//        JsonObject jsonobj = root.getAsJsonObject();
//
//        String req_result = jsonobj.get("result").getAsString();
//
//        return req_result;

        String url = url_str + "?from=" + from + "&to=" + to;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }
}
