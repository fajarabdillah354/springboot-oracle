package dev.codejar.springbooot_oracle.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("api/customer")
public class CustomerController {


    @GetMapping()
    public String hello(){
        return "this section hello";
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
