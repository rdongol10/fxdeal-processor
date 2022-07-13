-fxdeal-processor is a rest-api that takes FxDeal object validates it and persists it to database
-Run the script file /startup.sh to execute the program.
    -The script file will wil compile the project and load the component in the docker
-Once the project is up and running you can access the api at http://localhost:8080
    -The api takes one fxDeal request at a request , processes and persists it.
    -To save a fxDeal , do POST request at the URL "http://localhost:8080/fxDeal" with JSON body as follows:
        {
            "dealId":"1234",
            "orderCurrencyCode":"USD",
            "toCurrencyCode":"NPR",
            "dateTime":"2011-12-03T10:15:30",
            "dealAmount":"1000"
        }