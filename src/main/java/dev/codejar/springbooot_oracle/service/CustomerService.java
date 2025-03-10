//package dev.codejar.springbooot_oracle.service;
//
//
//import dev.codejar.springbooot_oracle.models.dto.CustomerDto;
//import dev.codejar.springbooot_oracle.models.entity.CustomerEntity;
//import dev.codejar.springbooot_oracle.models.payload.request.CustomerRequest;
//import dev.codejar.springbooot_oracle.models.payload.response.BaseResponse;
//import dev.codejar.springbooot_oracle.repository.CustomerRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CustomerService {
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private final CustomerRepository customerRepository;
//
//
//    public CustomerService(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
//
//
//
//    //CREATE CUSTOMER
//    public ResponseEntity<Object> createCustomer(CustomerRequest csRequest){
//        return customerRepository.findByEmail(csRequest.getEmail())
//                .map(customerEntityExist -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build())
//                .orElseGet(() -> {
//                    CustomerEntity entity = CustomerEntity.builder()
//                            .customerId(csRequest.getCustomerId())
//                            .name(csRequest.getName())
//                            .email(csRequest.getEmail())
//                            .phone(csRequest.getPhone())
//                            .build();
//
//                    CustomerEntity savedEntity = customerRepository.save(entity);
//
//                    BaseResponse<CustomerEntity> response = BaseResponse.<CustomerEntity>builder()
//                            .success(true)
//                            .message("success add customer")
//                            .data(savedEntity)
//                            .build();
//
//                    return ResponseEntity.ok(response);
//
//                });
//
//        /*
//        Contoh menggunakan Optional biasa
//         */
////        Optional<CustomerEntity> optional = customerRepository.findByName(csRequest.getName());
////        if (optional.isPresent()){
////            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
////        }
////
////        CustomerEntity entity = new CustomerEntity();
////        entity.setCustomerId(csRequest.getCustomerId());
////        entity.setName(csRequest.getName());
////        entity.setEmail(csRequest.getEmail());
////        entity.setPhone(csRequest.getPhone());
////
////        BaseResponse<CustomerEntity> response = new BaseResponse<>();
////        response.setSuccess(Boolean.TRUE);
////        response.setMessage("customer success added");
////        response.setData(entity);
////
////        return ResponseEntity.ok().build();
//    }
//
//
//    //UPDATE CUSTOMER
//    public ResponseEntity<BaseResponse<CustomerEntity>> updateCustomer(Long id, CustomerEntity updateCs){
//        return customerRepository.findById(id)
//                .map(customerEntity -> {
//                    customerEntity.setCustomerId(updateCs.getCustomerId());
//                    customerEntity.setName(updateCs.getName());
//                    customerEntity.setEmail(updateCs.getEmail());
//                    customerEntity.setPhone(updateCs.getPhone());
//
//                    modelMapper.map(customerEntity, CustomerDto.class);
//                    CustomerEntity saved = customerRepository.save(customerEntity);
//
//                    BaseResponse<CustomerEntity> response = BaseResponse.<CustomerEntity>builder()
//                            .success(true)
//                            .message("success update customer")
//                            .data(saved)
//                            .build();
//
//                    return ResponseEntity.ok(response);
//
//
//                }).orElseThrow(() -> new RuntimeException(String.valueOf(ResponseEntity.status(HttpStatus.NOT_FOUND))));
//    }
//
//
//    //DETELE CUSTOMER
//    public ResponseEntity<?> deleteCustomer(String email){
//            Optional<CustomerEntity> optional = customerRepository.findByEmail(email);
//            if (optional.isPresent()){
//                customerRepository.delete(optional.get());
//                return ResponseEntity.ok("customer with name : " + email + " already deleted");
//            }else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            }
//    }
//
//    //GET ALL CUSTOMER
//    public List<CustomerEntity> getAllCus(){
//        return customerRepository.findAll();
//    }
//
//
//}
