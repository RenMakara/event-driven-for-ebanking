package co.istad.makara.account.config;


import co.istad.makara.account.domain.handler.interceptors.AccountCreationDispatchInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {

    @Autowired
    public void registerInterceptors(CommandBus commandBus, AccountCreationDispatchInterceptor accountInterceptor) {
        commandBus.registerDispatchInterceptor(accountInterceptor);
    }

//    @Bean
//    @Primary
//    public Serializer jacksonSerializer() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        return JacksonSerializer.builder()
//                .objectMapper(objectMapper)
//                .build();
//    }
}