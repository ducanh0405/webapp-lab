package com.example.customerapi.service;

import com.example.customerapi.dto.CustomerRequestDTO;
import com.example.customerapi.dto.CustomerResponseDTO;
import com.example.customerapi.dto.CustomerUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CustomerService {

    // Classic CRUD Methods
    List<CustomerResponseDTO> getAllCustomers();

    CustomerResponseDTO getCustomerById(Long id);

    CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO);

    CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO requestDTO);

    void deleteCustomer(Long id);

    // Homework 5: Search & Filter Methods
    List<CustomerResponseDTO> searchCustomers(String keyword);

    List<CustomerResponseDTO> getCustomersByStatus(String status);

    List<CustomerResponseDTO> advancedSearch(String name, String email, String status);

    // Homework 6: Pagination & Sorting
    Page<CustomerResponseDTO> getAllCustomers(int page, int size);

    List<CustomerResponseDTO> getAllCustomers(Sort sort);

    Page<CustomerResponseDTO> getAllCustomersCombined(int page, int size, String sortBy, String sortDir);

    // Homework 7: Partial Update (PATCH)
    CustomerResponseDTO partialUpdateCustomer(Long id, CustomerUpdateDTO updateDTO);
}
