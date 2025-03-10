package dev.codejar.springbooot_oracle.service;


import dev.codejar.springbooot_oracle.models.entity.CustomerEntity;
import dev.codejar.springbooot_oracle.models.payload.request.CustomerRequest;
import dev.codejar.springbooot_oracle.repository.CsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CsService {

    @Autowired
    private CsRepository csRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    private ModelMapper modelMapper;


    @Transactional
    public ResponseEntity<Void> updateStatus(Long id, String status){
        return csRepository.updateCustomersName(id, status);
    }

    @Transactional
    public ResponseEntity<Void> updateBirthDate(Long id, LocalDateTime date){
        return csRepository.updateBirthDate(id, date);
    }

    @Transactional(readOnly = true)
    public Optional<CustomerEntity> findById(Long id){
        return csRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<CustomerEntity> findAllCustomer(){
        return csRepository.findAll();
    }

    @Transactional
    public CustomerEntity saveCs(CustomerRequest customerRequest){
        CustomerRequest request = CustomerRequest.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .phone(customerRequest.getPhone())
                .status(customerRequest.getStatus())
                .build();

        CustomerEntity saved = modelMapper.map(request, CustomerEntity.class);
        return csRepository.save(saved);
    }

    @Transactional(readOnly = true)
    public Integer calculateRiskScore(Long customerId){
        return csRepository.calculateRiskScore(customerId);
    }

    @Transactional(readOnly = true)
    public String getCustomerLevel(Long customerId){
        return csRepository.getCustomerLevel(customerId);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getAccountInfo(Long customerId){
        StoredProcedureQuery query = entityManager
                .createNamedStoredProcedureQuery("Customer.getAccountInfo");
        query.setParameter("p_customer_id", customerId);

        query.execute();


        Map<String, Object> result = new HashMap<>();
        result.put("accountCount", query.getOutputParameterValue("p_account_count"));
        result.put("totalBalance", query.getOutputParameterValue("p_total_balance"));

        return result;
    }


    @Transactional
    public void transferFunds(Long fromAccoundId, Long toAccountId, Double amount){
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("ACCOUNT_PKG.TRANSFER_FUNDS")
                .registerStoredProcedureParameter("p_from_account", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_to_account", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_amount", Double.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_success", Boolean.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_message", String.class, ParameterMode.OUT);

        query.setParameter("p_from_account", fromAccoundId);
        query.setParameter("p_to_account", toAccountId);
        query.setParameter("p_amount", amount);

        query.execute();

        Boolean success = (Boolean) query.getOutputParameterValue("p_success");
        String message = (String) query.getOutputParameterValue("p_message");

        if (!success){
            throw new RuntimeException("Transfer Failed : " + message);
        }
    }


}
