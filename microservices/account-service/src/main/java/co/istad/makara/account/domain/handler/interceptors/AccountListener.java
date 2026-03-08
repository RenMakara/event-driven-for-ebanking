package co.istad.makara.account.domain.handler.interceptors;

import co.istad.makara.account.data.entity.AccountEntity;
import co.istad.makara.account.data.entity.AccountTypeEntity;
import co.istad.makara.account.data.repository.AccountEntityRepository;
import co.istad.makara.account.data.repository.AccountTypeRepository;
import co.istad.makara.account.domain.event.AccountCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountListener {

    private final AccountEntityRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;

    @EventHandler
    public void on(AccountCreatedEvent event){

        AccountTypeEntity accountTypeEntity = accountTypeRepository.findByAccountTypeCode(event.accountTypeCode()).orElse(null);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountTypeEntity(accountTypeEntity);
        accountEntity.setAccountHolder(event.accountHolder());
        accountEntity.setAccountId(event.accountId().value());
        accountEntity.setAccountNumber(event.accountNumber());
        accountEntity.setBalance(event.initialBalance());
        accountEntity.setAccountStatus(event.accountStatus());
        accountEntity.setBranchId(event.branchId().value());
        accountEntity.setCreatedBy(event.createdBy());
        accountEntity.setCreatedAt(event.createdAt());
        accountEntity.setCustomerId(event.customerId().value());

        accountRepository.save(accountEntity);

    }
}
