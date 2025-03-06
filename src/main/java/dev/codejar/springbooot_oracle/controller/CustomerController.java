package dev.codejar.springbooot_oracle.controller;


import dev.codejar.springbooot_oracle.models.dto.CustomerDto;
import dev.codejar.springbooot_oracle.models.entity.CustomerEntity;
import dev.codejar.springbooot_oracle.models.payload.request.CustomerRequest;
import dev.codejar.springbooot_oracle.models.payload.response.BaseResponse;
import dev.codejar.springbooot_oracle.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public String hello(){
        return "this section hello";
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCs(@Valid @RequestBody CustomerRequest customerRequest){
        return customerService.createCustomer(customerRequest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse<CustomerEntity>> updateCs(@PathVariable Integer id, @RequestBody CustomerEntity updateCs){
        return customerService.updateCustomer(id, updateCs);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteCs(@PathVariable String name){
        return customerService.deleteCustomer(name);
    }

    @GetMapping("/all")
    public List<CustomerEntity> customers(){
        return customerService.getAllCus();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerError(MethodArgumentNotValidException exception){
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors()
                .forEach(objectError -> {
                    String fieldError = ((FieldError) objectError).getField();
                    String message = objectError.getDefaultMessage();
                    errors.put(fieldError, message);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

}
