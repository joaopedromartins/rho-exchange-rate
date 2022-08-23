package com.example.demo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	MockMvc mockMvc;


	@Test
	void helloWorldShouldReturnDefaultMessageTest() throws Exception {
		this.mockMvc.perform(get("/api/v1/hello-world"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Hello World"));
	}


	@Test
	void getExchangeRateFromCurrencyAtoCurrencyBshouldReturnNumber() throws Exception {
		this.mockMvc.perform(get("/api/v1/exchangerate")
						.param("from", "EUR")
						.param("to","USD"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$").isNumber());
	}

	@Test
	void getAllExchangeRatesFromCurrencyAshouldReturnArray() throws Exception {
		this.mockMvc.perform(get("/api/v1/allexchangerates")
						.param("from", "EUR"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.USD").isNumber())
				.andExpect(jsonPath("$.GBP").isNumber());
	}

	@Test
	void getValueConversionFromCurrencyAtoCurrencyBshouldReturnNumber() throws Exception {
		this.mockMvc.perform(get("/api/v1/exchangerate")
						.param("from", "EUR")
						.param("to","USD")
						.param("amount", String.valueOf(10.00)))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$").isNumber());
	}

	@Test
	void getValueConversionFromCurrencyAtoCurrencyListshouldReturnArray() throws Exception {
		this.mockMvc.perform(get("/api/v1/valueconversionlist")
						.param("from", "EUR")
						.param("to","USD,GBP")
						.param("amount", String.valueOf(10.00)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.USD").isNumber())
				.andExpect(jsonPath("$.GBP").isNumber());
	}


	@Test
	void getDelayedExchangeRateFromCurrencyAtoCurrencyBshouldReturnNumber() throws Exception {
		this.mockMvc.perform(get("/api/v1/delayed/exchangerate")
						.param("server", "ExchangeRateHost")
						.param("from", "EUR")
						.param("to","USD"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$").isNumber());
	}


	@Test
	void getDelayedCustomExchangeRateFromCurrencyAtoCurrencyBshouldReturnNumber() throws Exception {
		this.mockMvc.perform(get("/api/v1/delayed/exchangerate")
						.param("server", "Custom")
						.param("from", "EUR")
						.param("to","USD"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$").isNumber());
	}

	@Test
	void getDelayedAllExchangeRatesFromCurrencyAshouldReturnArray() throws Exception {
		this.mockMvc.perform(get("/api/v1/delayed/allexchangerates")
						.param("server", "ExchangeRateHost")
						.param("from", "EUR"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.USD").isNumber())
				.andExpect(jsonPath("$.GBP").isNumber());
	}

	@Test
	void getDelayedCustomAllExchangeRatesFromCurrencyAshouldReturnArray() throws Exception {
		this.mockMvc.perform(get("/api/v1/delayed/allexchangerates")
						.param("server", "Custom")
						.param("from", "EUR"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.USD").isNumber())
				.andExpect(jsonPath("$.GBP").isNumber());
	}

	@Test
	void getDelayedValueConversionFromCurrencyAtoCurrencyBshouldReturnNumber() throws Exception {
		this.mockMvc.perform(get("/api/v1/delayed/exchangerate")
						.param("server", "ExchangeRateHost")
						.param("from", "EUR")
						.param("to","USD")
						.param("amount", String.valueOf(10.00)))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$").isNumber());
	}

	@Test
	void getDelayedCustomValueConversionFromCurrencyAtoCurrencyBshouldReturnNumber() throws Exception {
		this.mockMvc.perform(get("/api/v1/delayed/exchangerate")
						.param("server", "Custom")
						.param("from", "EUR")
						.param("to","USD")
						.param("amount", String.valueOf(10.00)))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$").isNumber());
	}

	@Test
	void getDelayedValueConversionFromCurrencyAtoCurrencyListshouldReturnArray() throws Exception {
		this.mockMvc.perform(get("/api/v1/delayed/valueconversionlist")
						.param("server", "ExchangeRateHost")
						.param("from", "EUR")
						.param("to","USD,GBP")
						.param("amount", String.valueOf(10.00)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.USD").isNumber())
				.andExpect(jsonPath("$.GBP").isNumber());
	}

	@Test
	void getDelayedCustomValueConversionFromCurrencyAtoCurrencyListshouldReturnArray() throws Exception {
		this.mockMvc.perform(get("/api/v1/delayed/valueconversionlist")
						.param("server", "Custom")
						.param("from", "EUR")
						.param("to","USD,GBP")
						.param("amount", String.valueOf(10.00)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.USD").isNumber())
				.andExpect(jsonPath("$.GBP").isNumber());
	}


	@Test
	void verifyAllCurrenciesFromEnumCurrencyExists() throws Exception {
		this.mockMvc.perform(get("/api/v1/allexchangerates")
						.param("from", "EUR"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.AED").isNumber())
				.andExpect(jsonPath("$.AFN").isNumber())
				.andExpect(jsonPath("$.ALL").isNumber())
				.andExpect(jsonPath("$.AMD").isNumber())
				.andExpect(jsonPath("$.ANG").isNumber())
				.andExpect(jsonPath("$.AOA").isNumber())
				.andExpect(jsonPath("$.ARS").isNumber())
				.andExpect(jsonPath("$.AUD").isNumber())
				.andExpect(jsonPath("$.AWG").isNumber())
				.andExpect(jsonPath("$.AZN").isNumber())
				.andExpect(jsonPath("$.BAM").isNumber())
				.andExpect(jsonPath("$.BBD").isNumber())
				.andExpect(jsonPath("$.BDT").isNumber())
				.andExpect(jsonPath("$.BGN").isNumber())
				.andExpect(jsonPath("$.BHD").isNumber())
				.andExpect(jsonPath("$.BIF").isNumber())
				.andExpect(jsonPath("$.BMD").isNumber())
				.andExpect(jsonPath("$.BND").isNumber())
				.andExpect(jsonPath("$.BOB").isNumber())
				.andExpect(jsonPath("$.BRL").isNumber())
				.andExpect(jsonPath("$.BSD").isNumber())
				.andExpect(jsonPath("$.BTC").isNumber())
				.andExpect(jsonPath("$.BTN").isNumber())
				.andExpect(jsonPath("$.BWP").isNumber())
				.andExpect(jsonPath("$.BYN").isNumber())
				.andExpect(jsonPath("$.BZD").isNumber())
				.andExpect(jsonPath("$.CAD").isNumber())
				.andExpect(jsonPath("$.CDF").isNumber())
				.andExpect(jsonPath("$.CHF").isNumber())
				.andExpect(jsonPath("$.CLF").isNumber())
				.andExpect(jsonPath("$.CLP").isNumber())
				.andExpect(jsonPath("$.CNH").isNumber())
				.andExpect(jsonPath("$.CNY").isNumber())
				.andExpect(jsonPath("$.COP").isNumber())
				.andExpect(jsonPath("$.CRC").isNumber())
				.andExpect(jsonPath("$.CUC").isNumber())
				.andExpect(jsonPath("$.CUP").isNumber())
				.andExpect(jsonPath("$.CVE").isNumber())
				.andExpect(jsonPath("$.CZK").isNumber())
				.andExpect(jsonPath("$.DJF").isNumber())
				.andExpect(jsonPath("$.DKK").isNumber())
				.andExpect(jsonPath("$.DOP").isNumber())
				.andExpect(jsonPath("$.DZD").isNumber())
				.andExpect(jsonPath("$.EGP").isNumber())
				.andExpect(jsonPath("$.ERN").isNumber())
				.andExpect(jsonPath("$.ETB").isNumber())
				.andExpect(jsonPath("$.EUR").isNumber())
				.andExpect(jsonPath("$.FJD").isNumber())
				.andExpect(jsonPath("$.FKP").isNumber())
				.andExpect(jsonPath("$.GBP").isNumber())
				.andExpect(jsonPath("$.GEL").isNumber())
				.andExpect(jsonPath("$.GGP").isNumber())
				.andExpect(jsonPath("$.GHS").isNumber())
				.andExpect(jsonPath("$.GIP").isNumber())
				.andExpect(jsonPath("$.GMD").isNumber())
				.andExpect(jsonPath("$.GNF").isNumber())
				.andExpect(jsonPath("$.GTQ").isNumber())
				.andExpect(jsonPath("$.GYD").isNumber())
				.andExpect(jsonPath("$.HKD").isNumber())
				.andExpect(jsonPath("$.HNL").isNumber())
				.andExpect(jsonPath("$.HRK").isNumber())
				.andExpect(jsonPath("$.HTG").isNumber())
				.andExpect(jsonPath("$.HUF").isNumber())
				.andExpect(jsonPath("$.IDR").isNumber())
				.andExpect(jsonPath("$.ILS").isNumber())
				.andExpect(jsonPath("$.IMP").isNumber())
				.andExpect(jsonPath("$.INR").isNumber())
				.andExpect(jsonPath("$.IQD").isNumber())
				.andExpect(jsonPath("$.IRR").isNumber())
				.andExpect(jsonPath("$.ISK").isNumber())
				.andExpect(jsonPath("$.JEP").isNumber())
				.andExpect(jsonPath("$.JMD").isNumber())
				.andExpect(jsonPath("$.JOD").isNumber())
				.andExpect(jsonPath("$.JPY").isNumber())
				.andExpect(jsonPath("$.KES").isNumber())
				.andExpect(jsonPath("$.KGS").isNumber())
				.andExpect(jsonPath("$.KHR").isNumber())
				.andExpect(jsonPath("$.KMF").isNumber())
				.andExpect(jsonPath("$.KPW").isNumber())
				.andExpect(jsonPath("$.KRW").isNumber())
				.andExpect(jsonPath("$.KWD").isNumber())
				.andExpect(jsonPath("$.KYD").isNumber())
				.andExpect(jsonPath("$.KZT").isNumber())
				.andExpect(jsonPath("$.LAK").isNumber())
				.andExpect(jsonPath("$.LBP").isNumber())
				.andExpect(jsonPath("$.LKR").isNumber())
				.andExpect(jsonPath("$.LRD").isNumber())
				.andExpect(jsonPath("$.LSL").isNumber())
				.andExpect(jsonPath("$.LYD").isNumber())
				.andExpect(jsonPath("$.MAD").isNumber())
				.andExpect(jsonPath("$.MDL").isNumber())
				.andExpect(jsonPath("$.MGA").isNumber())
				.andExpect(jsonPath("$.MKD").isNumber())
				.andExpect(jsonPath("$.MMK").isNumber())
				.andExpect(jsonPath("$.MNT").isNumber())
				.andExpect(jsonPath("$.MOP").isNumber())
				.andExpect(jsonPath("$.MRU").isNumber())
				.andExpect(jsonPath("$.MUR").isNumber())
				.andExpect(jsonPath("$.MVR").isNumber())
				.andExpect(jsonPath("$.MWK").isNumber())
				.andExpect(jsonPath("$.MXN").isNumber())
				.andExpect(jsonPath("$.MYR").isNumber())
				.andExpect(jsonPath("$.MZN").isNumber())
				.andExpect(jsonPath("$.NAD").isNumber())
				.andExpect(jsonPath("$.NGN").isNumber())
				.andExpect(jsonPath("$.NIO").isNumber())
				.andExpect(jsonPath("$.NOK").isNumber())
				.andExpect(jsonPath("$.NPR").isNumber())
				.andExpect(jsonPath("$.NZD").isNumber())
				.andExpect(jsonPath("$.OMR").isNumber())
				.andExpect(jsonPath("$.PAB").isNumber())
				.andExpect(jsonPath("$.PEN").isNumber())
				.andExpect(jsonPath("$.PGK").isNumber())
				.andExpect(jsonPath("$.PHP").isNumber())
				.andExpect(jsonPath("$.PKR").isNumber())
				.andExpect(jsonPath("$.PLN").isNumber())
				.andExpect(jsonPath("$.PYG").isNumber())
				.andExpect(jsonPath("$.QAR").isNumber())
				.andExpect(jsonPath("$.RON").isNumber())
				.andExpect(jsonPath("$.RSD").isNumber())
				.andExpect(jsonPath("$.RUB").isNumber())
				.andExpect(jsonPath("$.RWF").isNumber())
				.andExpect(jsonPath("$.SAR").isNumber())
				.andExpect(jsonPath("$.SBD").isNumber())
				.andExpect(jsonPath("$.SCR").isNumber())
				.andExpect(jsonPath("$.SDG").isNumber())
				.andExpect(jsonPath("$.SEK").isNumber())
				.andExpect(jsonPath("$.SGD").isNumber())
				.andExpect(jsonPath("$.SHP").isNumber())
				.andExpect(jsonPath("$.SLL").isNumber())
				.andExpect(jsonPath("$.SOS").isNumber())
				.andExpect(jsonPath("$.SRD").isNumber())
				.andExpect(jsonPath("$.SSP").isNumber())
				.andExpect(jsonPath("$.STD").isNumber())
				.andExpect(jsonPath("$.STN").isNumber())
				.andExpect(jsonPath("$.SVC").isNumber())
				.andExpect(jsonPath("$.SYP").isNumber())
				.andExpect(jsonPath("$.SZL").isNumber())
				.andExpect(jsonPath("$.THB").isNumber())
				.andExpect(jsonPath("$.TJS").isNumber())
				.andExpect(jsonPath("$.TMT").isNumber())
				.andExpect(jsonPath("$.TND").isNumber())
				.andExpect(jsonPath("$.TOP").isNumber())
				.andExpect(jsonPath("$.TRY").isNumber())
				.andExpect(jsonPath("$.TTD").isNumber())
				.andExpect(jsonPath("$.TWD").isNumber())
				.andExpect(jsonPath("$.TZS").isNumber())
				.andExpect(jsonPath("$.UAH").isNumber())
				.andExpect(jsonPath("$.UGX").isNumber())
				.andExpect(jsonPath("$.USD").isNumber())
				.andExpect(jsonPath("$.UYU").isNumber())
				.andExpect(jsonPath("$.UZS").isNumber())
				.andExpect(jsonPath("$.VES").isNumber())
				.andExpect(jsonPath("$.VND").isNumber())
				.andExpect(jsonPath("$.VUV").isNumber())
				.andExpect(jsonPath("$.WST").isNumber())
				.andExpect(jsonPath("$.XAF").isNumber())
				.andExpect(jsonPath("$.XAG").isNumber())
				.andExpect(jsonPath("$.XAU").isNumber())
				.andExpect(jsonPath("$.XCD").isNumber())
				.andExpect(jsonPath("$.XDR").isNumber())
				.andExpect(jsonPath("$.XOF").isNumber())
				.andExpect(jsonPath("$.XPD").isNumber())
				.andExpect(jsonPath("$.XPF").isNumber())
				.andExpect(jsonPath("$.XPT").isNumber())
				.andExpect(jsonPath("$.YER").isNumber())
				.andExpect(jsonPath("$.ZAR").isNumber())
				.andExpect(jsonPath("$.ZMW").isNumber())
				.andExpect(jsonPath("$.ZWL").isNumber());
	}
}
