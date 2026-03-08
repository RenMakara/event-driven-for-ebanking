package co.istad.makara.account.rest;

import co.istad.makara.account.application.AccountService;
import co.istad.makara.account.application.dto.create.CreateAccountResponse;
import co.istad.makara.account.application.dto.create.CreateAccountRequest;
import co.istad.makara.account.application.dto.create.CreateDepositRequest;
import co.istad.makara.account.application.dto.create.CreateDepositResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateAccountResponse createAccountResponse(@RequestBody CreateAccountRequest request) {
        return accountService.createAccount(request);
    }
    @PostMapping("/deposit")
    public CreateDepositResponse createDepositResponse(@RequestBody CreateDepositRequest request){
        return accountService.depositAmount(request);
    }


//    @PostMapping
//    public CompletableFuture<ResponseEntity<String>> createAccount(@RequestBody CreateAccountRequest request) {
//
//        AccountId newAccountId = new AccountId(UUID.randomUUID());
//
//        // Map DTO -> Command
//
//        CreateAccountCommand command = new CreateAccountCommand(
//                newAccountId,
//                request.accountNumber(),
//                request.accountHolder(),
//                request.customerId(),
//                request.accountTypeCode(),
//                request.branchId(),
//                request.initialBalance(),
//                AccountStatus.ACTIVE
//        );
//
//        return commandGateway.send(command).thenApply(result -> {
//            return new ResponseEntity<>("Account Created Successfully: " + result, HttpStatus.CREATED);
//        }).exceptionally(e -> {
//            // Exception from Interceptor (Validation Failed)
//            return new ResponseEntity<>("Customer not found : " + e.getMessage(), HttpStatus.NOT_FOUND);
//        });
//        return commandGateway.sendAndWait(command);
//    }







//    @GetMapping
//    public ResponseEntity<Map<String, Object> > unsecureEndpoint(){
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("id", 1);
//        response.put("name", "Ren Makara");
//        response.put("age", 23);
//        response.put("isStudent", true);
//        response.put("address", "Kampong thom");
//        response.put("Role", "Footballer");
//
//        return ResponseEntity.ok(response);
//    }





}
