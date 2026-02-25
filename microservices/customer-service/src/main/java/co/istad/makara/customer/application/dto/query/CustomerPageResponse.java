package co.istad.makara.customer.application.dto.query;

import lombok.Builder;

import java.util.List;

@Builder
public record CustomerPageResponse(
        List<CustomerResponse> content,
        int currentPage,
        int pageSize,
        long totalElements,
        int totalPages
) {
}
