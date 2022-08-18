# rho-exchange-rate
RHO Exchange Rate Challenge


## Delpoy
Download project archive at 
https://github.com/joaopedromartins/rho-exchange-rate/archive/refs/heads/main.zip
and unzip it to a folder in your computer.
Open project with an IDE such as IteliJ IDEA.
Open file DemoAplication.java , right-click and select "run DemoApplication main()"


## API Operations Examples
Open in your browser http://localhost:8080/swagger-ui.html to see documentation and a testing interface

You can also test the following examples directly in your browser

### Hello World
http://localhost:8080/api/v1/hello-world
Hello World


### Get exchange rate from Currency A (EUR) to Currency B (USD)
http://localhost:8080/api/v1/exchangerate?from=EUR&to=USD
1.017868

### Get all exchange rates from Currency A (EUR)
http://localhost:8080/api/v1/allexchangerates?from=EUR
{"AED":3.73971, ...  ,"ZMW":16.415399,"ZWL":327.749653}

### Get value conversion from Currency A (EUR) to Currency B (USD) where amount is (10.00)
http://localhost:8080/api/v1/valueconversion?from=EUR&to=USD&amount=10.00
10.179534

### Get value conversion from Currency A (EUR) to a list of supplied currencies where amount is (1000.00)
http://localhost:8080/api/v1/valueconversionlist?base=EUR&to=USD,GBP&amount=1000.00
{"USD":1017.867795, "GBP":844.890275}