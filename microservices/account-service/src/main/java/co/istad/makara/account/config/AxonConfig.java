package co.istad.makara.account.config;

import co.istad.makara.account.domain.handler.interceptors.AccountCreationDispatchInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Autowired
    public void registerInterceptors(CommandBus commandBus, AccountCreationDispatchInterceptor accountInterceptor) {
        commandBus.registerDispatchInterceptor(accountInterceptor);
    }

}