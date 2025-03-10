package dev.codejar.springbooot_oracle.service;


import dev.codejar.springbooot_oracle.models.entity.AccountEntity;
import dev.codejar.springbooot_oracle.models.entity.TransactionEntity;
import dev.codejar.springbooot_oracle.models.payload.request.AccountRequest;
import dev.codejar.springbooot_oracle.models.payload.request.TransactionRequest;
import dev.codejar.springbooot_oracle.models.payload.response.BaseResponse;
import dev.codejar.springbooot_oracle.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Autowired
    private ModelMapper modelMapper;


    //FIND BY ID
    @Transactional(readOnly = true)
    public Optional<TransactionEntity> findById(Long id){
        return transactionRepository.findById(id);
    }


    //FIND ALL TRANSACTION
    @Transactional(readOnly = true)
    public List<TransactionEntity> findAllTransaction(){
        return transactionRepository.findAll();
    }


    //SAVE TRANSACTION
    @Transactional
    public TransactionEntity saveTs(TransactionRequest transaction){

        TransactionRequest request = TransactionRequest.builder()
                .account(transaction.getAccount())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .transactionType(transaction.getTransactionType())
                .build();

        TransactionEntity saved = modelMapper.map(request, TransactionEntity.class);



        return transactionRepository.save(saved);
    }

    //UPDATE ALL
    @Transactional
    public ResponseEntity<BaseResponse<TransactionEntity>> updateAllColumn(Long id, TransactionRequest transactionRequest, AccountRequest accountRequest){

        return transactionRepository.findById(id)
                .map(transaction -> {
                    transaction.setTransactionType(transactionRequest.getTransactionType());
                    transaction.setAmount(transactionRequest.getAmount());
                    transaction.setDescription(transactionRequest.getDescription());
                    AccountEntity accountEntity = AccountEntity.builder()
                                    .balance(accountRequest.getBalance())
                                            .build();

                    transaction.setAccount(accountEntity);

                    BaseResponse<TransactionEntity> response = BaseResponse.<TransactionEntity>builder()
                            .success(true)
                            .message("success update transaction")
                            .data(transaction)
                            .build();
                    return ResponseEntity.ok(response);
                }).orElseThrow(() -> new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND)));
    }




}
