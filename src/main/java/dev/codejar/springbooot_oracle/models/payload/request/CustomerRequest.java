package dev.codejar.springbooot_oracle.models.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
public class CustomerRequest {


    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank(message = "phone cannot be blank")
    private String phone;

    @NotBlank(message = "please fill the status")
    private String status;

    private LocalDateTime birthDate;

}
