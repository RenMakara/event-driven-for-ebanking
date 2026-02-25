package co.istad.makara.customer.application.dto.query;

import lombok.*;

@Builder
public record GetCustomerQuery(
        int pageNumber,
        int pageSize
) {

}
