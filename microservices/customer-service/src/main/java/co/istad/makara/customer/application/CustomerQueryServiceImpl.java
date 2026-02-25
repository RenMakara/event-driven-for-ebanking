package co.istad.makara.customer.application;
import co.istad.makara.customer.application.dto.query.CustomerPageResponse;
import co.istad.makara.customer.application.dto.query.GetCustomerQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerQueryServiceImpl implements CustomerQueryService{

    private final QueryGateway queryGateway;

    @Override
    public CustomerPageResponse getAllCustomers(int pageNumber, int pageSize) {
        return queryGateway.query(
                new GetCustomerQuery(pageNumber, pageSize),
                ResponseTypes.instanceOf(CustomerPageResponse.class)
        ).join();
    }
//    @Override
//    public CustomPageResponse getAllCustomers(int pageNumber, int pageSize){
//        GetCustomerQuery getCustomerQuery = GetCustomerQuery.builder()
//                .pageNumber(pageNumber)
//                .pageSize(pageSize)
//                .build();
//        return queryGateway
//                .query(getCustomerQuery, ResponseTypes.instanceOf(CustomPageResponse.class))
//                .join();
//    }


}
