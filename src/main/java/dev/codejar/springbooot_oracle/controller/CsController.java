package dev.codejar.springbooot_oracle.controller;


import dev.codejar.springbooot_oracle.models.entity.CustomerEntity;
import dev.codejar.springbooot_oracle.models.payload.request.CustomerRequest;
import dev.codejar.springbooot_oracle.models.payload.response.BaseResponse;
import dev.codejar.springbooot_oracle.service.CsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cs")
public class CsController {

    @Autowired
    private CsService csService;

    // GET BY-ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getCustomer(@PathVariable Long id){
        return csService.findById(id)
                .map(customer -> ResponseEntity.ok(customer))
                .orElse(ResponseEntity.notFound().build());
    }


    //GET ALL V
    @GetMapping("/all")
    public List<CustomerEntity> findAllCs(){
        return csService.findAllCustomer();
    }


    //CREATE V
    @PostMapping
    public ResponseEntity<BaseResponse<CustomerEntity>> createCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        CustomerEntity savedCs = csService.saveCs(customerRequest);

        BaseResponse<CustomerEntity> response = BaseResponse.<CustomerEntity>builder()
                .success(true)
                .message("success add new customer")
                .data(savedCs)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    //UPDATE STATUS V
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam String status){
        return csService.updateStatus(id, status);
    }


    //UPDATE BIRTH-DATE V
    @PatchMapping("/{id}/date")
    public ResponseEntity<Void> updateBirthDate(@PathVariable Long id, @RequestParam String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parse = LocalDate.parse(date, formatter);
        LocalDateTime dateTime = parse.atStartOfDay();
        return csService.updateBirthDate(id, dateTime);
    }


    // GET RISK-SCORE V
    @GetMapping("/{id}/risk-score")
    public ResponseEntity<Integer> calculateRisk(@PathVariable Long id){
        Integer riskScore = csService.calculateRiskScore(id);
        return ResponseEntity.ok(riskScore);
    }


    //GET LEVEL V
    @GetMapping("/{id}/level")
    public ResponseEntity<String> getCustomerLevel(@PathVariable Long id){
        String level = csService.getCustomerLevel(id);
        return ResponseEntity.ok(level);
    }


    @GetMapping("/{id}/account")
    public ResponseEntity<Map<String, Object>> getCustomerAccount(@PathVariable Long id){
        Map<String, Object> accountInfo = csService.getAccountInfo(id);
        return ResponseEntity.ok(accountInfo);
    }


    @PostMapping("/transfer")
    public ResponseEntity<Void> transferFunds(@RequestParam Long fromAccount, @RequestParam Long toAccount, @RequestParam Double amount){
        csService.transferFunds(fromAccount, toAccount, amount);
        return ResponseEntity.ok().build();
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<?> hadlerError(MethodArgumentNotValidException e){
        var err = new HashMap<String, String>();
        e.getBindingResult().getAllErrors()
                .forEach(objectError -> {
                    String fieldError = ((FieldError) objectError).getField();
                    String message = objectError.getDefaultMessage();

                    err.put(fieldError, message);
                });
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }


}
