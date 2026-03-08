package co.istad.makara.account.application.mapper;

import co.istad.makara.account.application.dto.create.CreateAccountRequest;
import co.istad.makara.account.domain.command.CreateAccountCommand;
import co.istad.makara.account.domain.command.DepositMoneyCommand;
import co.istad.makara.common.domain.valueobject.AccountId;
import co.istad.makara.common.domain.valueobject.Currency;
import co.istad.makara.common.domain.valueobject.Money;
import co.istad.makara.common.domain.valueobject.TransactionId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    CreateAccountCommand toCreateAccountCommand(AccountId accountId, CreateAccountRequest createAccountRequest);

    @Mapping(target = "accountId", source = "accountId")
    @Mapping(target = "transactionId", source = "transactionId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "remark", source = "remark")
    DepositMoneyCommand toDepositMoneyCommand(AccountId accountId, TransactionId transactionId, Money amount, String remark);

    // Default mapping method for BigDecimal to Money conversion
    default Money map(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return new Money(value, Currency.USD); // Default currency, adjust as needed
    }
}
