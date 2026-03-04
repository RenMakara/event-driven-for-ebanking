package co.istad.makara.account.rest.dto;

import java.util.Map;
import java.util.UUID;

public record CustomerResponse(
        UUID customerId,
        Map<String, String> name
) {
}
