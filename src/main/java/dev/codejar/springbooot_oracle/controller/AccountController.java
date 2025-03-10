package dev.codejar.springbooot_oracle.controller;


import dev.codejar.springbooot_oracle.models.entity.AccountEntity;
import dev.codejar.springbooot_oracle.models.payload.request.AccountRequest;
import dev.codejar.springbooot_oracle.models.payload.response.BaseResponse;
import dev.codejar.springbooot_oracle.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<BaseResponse<AccountEntity>> createAccount(@Valid @RequestBody AccountRequest account){
        AccountEntity saved = accountService.createAccount(account);

        BaseResponse<AccountEntity> response = BaseResponse.<AccountEntity>builder()
                .success(true)
                .message("succees add new account")
                .data(saved)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
