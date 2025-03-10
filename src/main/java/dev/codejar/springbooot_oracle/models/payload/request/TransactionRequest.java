package dev.codejar.springbooot_oracle.models.payload.request;


import dev.codejar.springbooot_oracle.models.entity.AccountEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequest {

    @NotBlank(message = "transaction type cannot be blank")
    private String transactionType;
    
    private Double amount;

    @NotBlank(message = "description cannot be  blank")
    private String description;


    private AccountEntity account;

}
