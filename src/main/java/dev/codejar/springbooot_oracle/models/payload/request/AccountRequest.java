package dev.codejar.springbooot_oracle.models.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRequest {

    @NotNull(message = "balance cannot be null")
    @Positive(message = "balance mush be greater then zero")
    private Double balance;


}
