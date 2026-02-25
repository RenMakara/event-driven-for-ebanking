package co.istad.makara.customer.rest;

import co.istad.makara.customer.application.CustomerQueryService;
import co.istad.makara.customer.application.dto.query.CustomPageResponse;
import co.istad.makara.customer.application.dto.query.CustomerPageResponse;
import co.istad.makara.customer.application.dto.query.CustomerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerQueryController {


    private final CustomerQueryService customerQueryService;
    @GetMapping
    public CustomerPageResponse getAllCustomers(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "10", required = false) int pageSize
    ){
        return customerQueryService.getAllCustomers(pageNumber, pageSize);
    }
}

/** Flow
 * Controller → CustomerQueryService → QueryGateway
                                           ↓
               CustomerProjection (@QueryHandler)
                                           ↓
               CustomerRepository (paginated)
                                           ↓
               CustomerApplicationMapper
                                           ↓
               PageResponse<CustomerEntityResponse>
*/