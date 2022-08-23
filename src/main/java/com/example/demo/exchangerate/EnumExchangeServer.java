package com.example.demo.exchangerate;

public enum EnumExchangeServer {
    ExchangeRateHost ("exchangerate.host"),
    Custom ("Custom Rest API");

    private String exchangeServer;

    EnumExchangeServer(String exchangeServer) {
        this.exchangeServer = exchangeServer;
    }

    public String getExchangeServer() {
        return exchangeServer;
    }
}
