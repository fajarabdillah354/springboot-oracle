package dev.codejar.springbooot_oracle.models.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerRequest {

    @NotBlank(message = "customerId cannot be blank")
    private String customerId;

    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank(message = "phone cannot be blank")
    private String phone;

}
