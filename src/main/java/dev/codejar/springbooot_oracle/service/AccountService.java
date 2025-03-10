package dev.codejar.springbooot_oracle.service;


import dev.codejar.springbooot_oracle.models.entity.AccountEntity;
import dev.codejar.springbooot_oracle.models.payload.request.AccountRequest;
import dev.codejar.springbooot_oracle.models.payload.response.BaseResponse;
import dev.codejar.springbooot_oracle.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountRepository accountRepository;

    //CREATE NEW ACCOUNT
    public AccountEntity createAccount(AccountRequest account){

        AccountRequest request = AccountRequest.builder()
                .balance(account.getBalance())
                .build();

        AccountEntity saved = modelMapper.map(request, AccountEntity.class);

        return accountRepository.save(saved);
    }


}
