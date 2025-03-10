package dev.codejar.springbooot_oracle.controller;


import dev.codejar.springbooot_oracle.models.entity.TransactionEntity;
import dev.codejar.springbooot_oracle.models.payload.request.TransactionRequest;
import dev.codejar.springbooot_oracle.models.payload.response.BaseResponse;
import dev.codejar.springbooot_oracle.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/ts")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{id}")
    public Optional<TransactionEntity> findById(@PathVariable Long id){
        return transactionService.findById(id);
    }


    @GetMapping("/all")
    public List<TransactionEntity> findAllTs(){
        return transactionService.findAllTransaction();
    }


    @PostMapping
    public ResponseEntity<BaseResponse<TransactionEntity>> createTransaction(@Valid @RequestBody TransactionRequest transaction){
        TransactionEntity savedTs = transactionService.saveTs(transaction);

        BaseResponse<TransactionEntity> response = BaseResponse.<TransactionEntity>builder()
                .success(true)
                .message("success add new transaction")
                .data(savedTs)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> hadleError(MethodArgumentNotValidException e){
        var error = new HashMap<String, String>();
        e.getBindingResult().getAllErrors()
                .forEach(objectError -> {
                    String fieldName = ((FieldError) objectError).getField();
                    String messageError = objectError.getDefaultMessage();

                    error.put(fieldName, messageError);
                });
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



}
