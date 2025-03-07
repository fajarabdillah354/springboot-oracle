package dev.codejar.springbooot_oracle.models.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "Customer.updateStatus",
                procedureName = "CUSTOMER_PKG.UPDATE_CUSTOMER_STATUS",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "Customer.getAccountInfo",
                procedureName = "CUSTOMER_PKG.GET_ACCOUNT_INFO",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_account_count", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_total_balance", type = Double.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "Customer.updateBirthDate",
                procedureName = "CUSTOMER_PKG.UPDATE_BIRTH_DATE",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_date", type = Date.class)
                }
        )
})
public class CustomerEntity {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "id_seq", allocationSize = 1)
    private Integer id;


    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private String status;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
        this.updatedDate = this.createdDate;
    }

    @PostPersist
    protected void onUpdate(){
        this.updatedDate = LocalDateTime.now();
    }

}
