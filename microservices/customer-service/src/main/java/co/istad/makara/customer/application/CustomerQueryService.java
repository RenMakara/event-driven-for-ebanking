package co.istad.makara.customer.application;

import co.istad.makara.customer.application.dto.query.CustomPageResponse;
import co.istad.makara.customer.application.dto.query.CustomerPageResponse;


public interface CustomerQueryService {
    CustomerPageResponse getAllCustomers(int pageNumber, int pageSize);
}
