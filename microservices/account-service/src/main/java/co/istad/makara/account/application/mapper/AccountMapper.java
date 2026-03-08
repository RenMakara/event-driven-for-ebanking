package co.istad.makara.account.application.mapper;

import co.istad.makara.account.application.dto.create.CreateAccountRequest;
import co.istad.makara.account.domain.command.CreateAccountCommand;
import co.istad.makara.account.domain.command.DepositMoneyCommand;
import co.istad.makara.common.domain.valueobject.AccountId;
import co.istad.makara.common.domain.valueobject.Money;
import co.istad.makara.common.domain.valueobject.TransactionId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    CreateAccountCommand toCreateAccountCommand(AccountId accountId, CreateAccountRequest createAccountRequest);
    DepositMoneyCommand toDepositMoneyCommand(AccountId accountId, TransactionId transactionId, Money amount, String remark);
}
