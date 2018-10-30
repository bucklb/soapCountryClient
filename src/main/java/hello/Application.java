package hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hello.wsdl.GetCountryResponse;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // A little too stark to just show currency
    private static void displayResponse(GetCountryResponse response){
        System.out.println(String.format("name: %s, capital: %s, currency: %s, population: %d",
                response.getCountry().getName(),
                response.getCountry().getCapital(),
                response.getCountry().getCurrency(),
                response.getCountry().getPopulation()));
    }



    @Bean
    CommandLineRunner lookup(CountryClient countryClient) {
        return args -> {
            String country = "Spain";

            if (args.length > 0) {
                country = args[0];
            }
            GetCountryResponse response = countryClient.getCountry(country);
            System.err.println(response.getCountry().getCurrency());

            displayResponse(response);
        };
    }

}