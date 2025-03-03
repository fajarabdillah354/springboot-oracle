package dev.codejar.springbooot_oracle.models.payload.response;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseResponse<T> {

    private Boolean success;

    private String message;

    private T data;



}
