package dev.codejar.springbooot_oracle.repository;

import dev.codejar.springbooot_oracle.models.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;


import java.time.LocalDateTime;
import java.util.Optional;

public interface CsRepository extends JpaRepository<CustomerEntity, Long> {

    /*
        pada PLSQL pemanggilan PROCEDURE dan FUNCTION berbeda
        1. jika @Procedure untuk memanggil PROCEDURE pada PLSQL
        2. jika @Query(native = true) untuk memanggil FUNCTION pada PLSQL
     */

    Optional<CustomerEntity> findByEmail(String email);


    //procedure
    @Procedure(name = "Customer.updateStatus")
    ResponseEntity<Void> updateCustomersName(@Param("p_customer_id") Long customerId, @Param("p_status") String status);

    @Query(value = "SELECT CUSTOMER_PKG.CALCULATE_RISK_SCORE(:p_customer_id) FROM DUAL", nativeQuery = true)
    Integer calculateRiskScore(@Param("p_customer_id") Long customerId);

    //function dengan menggunakan query
    @Query(value = "SELECT CUSTOMER_PKG.GET_CUSTOMER_LEVEL(:p_customer_id) FROM DUAL", nativeQuery = true)
    String getCustomerLevel(@Param("p_customer_id") Long customerId);

    //procedure
    @Procedure(name = "Customer.updateBirthDate")
    ResponseEntity<Void>updateBirthDate(@Param("p_customer_id") Long customerId, @Param("p_date") LocalDateTime date);

}
