package co.istad.makara.account.config.external;

import co.istad.makara.common.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class CustomerClient {

    private final WebClient webClient;

    public CustomerClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://CUSTOMER/api/customers").build();
    }

    public void validateCustomer(CustomerId customerId) {
        // call API to Customer Service
        String status = webClient.get()
                .uri("/{customerId}", customerId)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // (Synchronous Check)



    }
}