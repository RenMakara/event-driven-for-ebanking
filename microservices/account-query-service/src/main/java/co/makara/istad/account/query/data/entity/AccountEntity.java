package co.makara.istad.account.query.data.entity;

import co.istad.makara.common.domain.valueobject.AccountStatus;
import co.istad.makara.common.domain.valueobject.AccountTypeCode;
import co.istad.makara.common.domain.valueobject.Currency;
import co.istad.makara.common.domain.valueobject.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts")
public class AccountEntity {

    @Id
    private UUID accountId;

    private UUID customerId;

    private UUID branchId;

    private UUID accountTypeId;

    private String accountNumber;

    private String accountHolder;

    private BigDecimal balance;

    private Currency currency;

    private AccountStatus accountStatus;

    private String createdBy;

    private String updatedBy;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}