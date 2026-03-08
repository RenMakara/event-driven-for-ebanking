package co.istad.makara.account.domain.aggregate;

import co.istad.makara.account.domain.command.CreateAccountCommand;
import co.istad.makara.account.domain.command.DepositMoneyCommand;
import co.istad.makara.account.domain.command.WithdrawMoneyCommand;
import co.istad.makara.account.domain.event.AccountCreatedEvent;
import co.istad.makara.account.domain.event.MoneyDepositedEvent;
import co.istad.makara.account.domain.exception.AccountDomainException;
import co.istad.makara.account.domain.validate.AccountValidation;
import co.istad.makara.account.domain.valueobject.AccountStatus;
import co.istad.makara.account.domain.valueobject.AccountTypeCode;
import co.istad.makara.common.application.exception.ApplicationException;
import co.istad.makara.common.domain.exception.DomainException;
import co.istad.makara.common.domain.valueobject.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Aggregate
@NoArgsConstructor
@Getter // this has only getter
@EqualsAndHashCode
@Slf4j
public class AccountAggregate {

    @AggregateIdentifier
    private AccountId accountId;

    private String accountNumber;

    private String accountHolder;

    private CustomerId customerId;

    private AccountTypeCode accountTypeCode;

    private BranchId branchId;

    private Money balance;

    private AccountStatus accountStatus;

    private ZonedDateTime createdAt;
    private String createdBy;
    private ZonedDateTime updatedAt;
    private String updatedBy;


    private void validateAccountType(AccountTypeCode accountTypeCode){
        if (accountTypeCode != AccountTypeCode.SAVINGS){
            throw new AccountDomainException("Account type can be only SAVINGS!");
        }
    }

    private void validateInitialBalance(Money initialBalance){
        Money minInitialBalance = new Money(BigDecimal.valueOf(10), Currency.USD);
        if(initialBalance.isLessThan(minInitialBalance)){
            throw new AccountDomainException("Create account require 10$");
        }

    }

    private void validateAccountIsActive() {
        if (this.accountStatus != AccountStatus.ACTIVE) {
            throw new AccountDomainException("Deposit is not allowed. Account status: " + this.accountStatus);
        }
    }

    private void validateDepositAmount(Money amount) {

        if (amount == null) {
            throw new AccountDomainException("Deposit amount cannot be null");
        }

        Money zero = new Money(BigDecimal.ZERO, Currency.USD);
        if (amount.isLessThanOrEqual(zero)) {
            throw new AccountDomainException("Deposit amount must be greater than zero");
        }
    }



    // Constructor CommandHandler required for first event
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {

        log.info("Aggregate on Create Account Command : {}", command.accountId());

        // Validate account number
        AccountValidation.validateAccountNumber(command.accountNumber());
        // Validate account type code
        validateAccountType(command.accountTypeCode());
        // validate initial balance
        validateInitialBalance(command.initialBalance());

        // create Event
         AccountCreatedEvent event = AccountCreatedEvent.builder()
                .accountId(command.accountId())
                .accountNumber(command.accountNumber())
                .accountHolder(command.accountHolder())
                .customerId(command.customerId())
                .accountTypeCode(command.accountTypeCode())
                .branchId(command.branchId())
                .initialBalance(command.initialBalance())
                .accountStatus(AccountStatus.ACTIVE)
                .createdAt(ZonedDateTime.now())
                .createdBy("ADMIN")
                .build();

        // Save Event to Event Store
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.accountId = event.accountId();
        this.accountNumber = event.accountNumber();
        this.accountHolder = event.accountHolder();
        this.customerId = event.customerId();
        this.accountTypeCode = event.accountTypeCode();
        this.branchId = event.branchId();
        this.balance = event.initialBalance();
        this.accountStatus = event.accountStatus();
        this.createdAt = event.createdAt();
        this.createdBy = event.createdBy();

        log.info("Account Created Event applied: {}", event.accountId());
    }

    @CommandHandler
    public void handle(DepositMoneyCommand command) {

        log.info("Handling DepositMoneyCommand for accountId = {}", command.accountId().value());

        validateAccountIsActive();
        validateDepositAmount(command.amount());

        Money newBalance = this.balance.add(command.amount());

        AggregateLifecycle.apply(
                new MoneyDepositedEvent(
                        this.accountId,
                        this.customerId,
                        command.transactionId(),
                        command.amount(),
                        newBalance,
                        command.remark(),
                        ZonedDateTime.now()
                )
        );
    }

    @EventSourcingHandler
    public void on(MoneyDepositedEvent event) {

        this.balance = event.newBalance();

        log.info("MoneyDepositedEvent applied: accountId={}, newBalance={}",
                event.accountId().value(),
                this.balance
        );
    }

    @CommandHandler
    public void handle(WithdrawMoneyCommand command){

    }

}
