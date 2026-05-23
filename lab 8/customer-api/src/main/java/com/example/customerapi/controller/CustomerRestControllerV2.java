package com.example.customerapi.controller;

import com.example.customerapi.dto.CustomerRequestDTO;
import com.example.customerapi.dto.CustomerResponseDTO;
import com.example.customerapi.dto.CustomerUpdateDTO;
import com.example.customerapi.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/customers")
@CrossOrigin(origins = "*")
public class CustomerRestControllerV2 {

    private final CustomerService customerService;

    @Autowired
    public CustomerRestControllerV2(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Helper to add HATEOAS links to a customer DTO
    private void addHateoasLinks(CustomerResponseDTO customer) {
        customer.add(linkTo(methodOn(CustomerRestControllerV2.class).getCustomerById(customer.getId())).withSelfRel());
        customer.add(linkTo(methodOn(CustomerRestControllerV2.class).getAllCustomers(null, null, null, "asc")).withRel("all-customers"));
    }

    // GET customers mapping (V2 with HATEOAS Links for each record)
    @GetMapping
    public ResponseEntity<?> getAllCustomers(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        if (page != null && size != null) {
            Page<CustomerResponseDTO> customerPage;
            if (sortBy != null && !sortBy.trim().isEmpty()) {
                customerPage = customerService.getAllCustomersCombined(page, size, sortBy, sortDir);
            } else {
                customerPage = customerService.getAllCustomers(page, size);
            }

            customerPage.getContent().forEach(this::addHateoasLinks);

            Map<String, Object> response = new HashMap<>();
            response.put("customers", customerPage.getContent());
            response.put("currentPage", customerPage.getNumber());
            response.put("totalItems", customerPage.getTotalElements());
            response.put("totalPages", customerPage.getTotalPages());
            
            // Add self page link
            response.put("_links", Map.of(
                    "self", linkTo(methodOn(CustomerRestControllerV2.class).getAllCustomers(page, size, sortBy, sortDir)).toString()
            ));
            
            return ResponseEntity.ok(response);

        } else if (sortBy != null && !sortBy.trim().isEmpty()) {
            Sort sort = sortDir.equalsIgnoreCase("asc")
                    ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();
            List<CustomerResponseDTO> customers = customerService.getAllCustomers(sort);
            customers.forEach(this::addHateoasLinks);
            return ResponseEntity.ok(customers);
        }

        List<CustomerResponseDTO> customers = customerService.getAllCustomers();
        customers.forEach(this::addHateoasLinks);
        return ResponseEntity.ok(customers);
    }

    // GET customer by ID (with self and list links)
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        CustomerResponseDTO customer = customerService.getCustomerById(id);
        addHateoasLinks(customer);
        return ResponseEntity.ok(customer);
    }

    // POST create customer (201 Created)
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO requestDTO) {
        CustomerResponseDTO createdCustomer = customerService.createCustomer(requestDTO);
        addHateoasLinks(createdCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    // PUT update customer
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDTO requestDTO) {
        CustomerResponseDTO updatedCustomer = customerService.updateCustomer(id, requestDTO);
        addHateoasLinks(updatedCustomer);
        return ResponseEntity.ok(updatedCustomer);
    }

    // PATCH partial update
    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> partialUpdateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerUpdateDTO updateDTO) {
        CustomerResponseDTO updatedCustomer = customerService.partialUpdateCustomer(id, updateDTO);
        addHateoasLinks(updatedCustomer);
        return ResponseEntity.ok(updatedCustomer);
    }

    // DELETE customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Customer deleted successfully (V2)");
        return ResponseEntity.ok(response);
    }

    // GET search customers
    @GetMapping("/search")
    public ResponseEntity<List<CustomerResponseDTO>> searchCustomers(@RequestParam String keyword) {
        List<CustomerResponseDTO> customers = customerService.searchCustomers(keyword);
        customers.forEach(this::addHateoasLinks);
        return ResponseEntity.ok(customers);
    }

    // GET customers by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<CustomerResponseDTO>> getCustomersByStatus(@PathVariable String status) {
        List<CustomerResponseDTO> customers = customerService.getCustomersByStatus(status);
        customers.forEach(this::addHateoasLinks);
        return ResponseEntity.ok(customers);
    }
}
