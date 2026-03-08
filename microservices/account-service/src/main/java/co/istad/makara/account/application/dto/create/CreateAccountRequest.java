package co.istad.makara.account.application.dto.create;

import co.istad.makara.account.domain.valueobject.AccountStatus;
import co.istad.makara.account.domain.valueobject.AccountTypeCode;
import co.istad.makara.common.domain.valueobject.BranchId;
import co.istad.makara.common.domain.valueobject.CustomerId;
import co.istad.makara.common.domain.valueobject.Money;

import java.time.ZonedDateTime;

public record CreateAccountRequest(

        String accountNumber,

        String accountHolder,

        CustomerId customerId,

        AccountTypeCode accountTypeCode,

        BranchId branchId,

        Money initialBalance
) {
}
