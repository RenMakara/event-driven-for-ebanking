package co.istad.makara.account.domain.handler.interceptors;


import co.istad.makara.account.domain.command.CreateAccountCommand;
import co.istad.makara.account.config.external.CustomerClient;
import co.istad.makara.common.domain.valueobject.CustomerId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountCreationDispatchInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final CustomerClient customerClient;

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {

            // first check command, is Command for create account?
            if (CreateAccountCommand.class.equals(command.getPayloadType())) {

                CreateAccountCommand createCommand = (CreateAccountCommand) command.getPayload();

                CustomerId customerId = createCommand.customerId();

                log.info("Interceptor: Checking Customer ID: {} ...", customerId);

                try {
                    // 3. call WebClient to check
                    customerClient.validateCustomer(customerId);
                    log.info("Interceptor: Customer {} : ", customerId);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Validation Failed : " + e.getMessage());
                }
            }
            return command;
        };
    }
}