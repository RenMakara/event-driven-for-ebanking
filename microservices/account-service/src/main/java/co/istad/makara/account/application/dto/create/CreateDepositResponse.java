package co.istad.makara.account.application.dto.create;

import lombok.Builder;

@Builder
public record CreateDepositResponse(
        String amount,
        String remark
) {
}
