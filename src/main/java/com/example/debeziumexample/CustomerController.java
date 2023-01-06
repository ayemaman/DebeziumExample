package com.example.debeziumexample;

import com.example.debeziumexample.model.Customer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        return ResponseEntity.ok().body(customerRepository.findById(id).orElse(null));
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok().body(customerRepository.findAll());
    }

    @PostMapping()
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok().body(customerRepository.save(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer doesn't exist with id:%s".formatted(id)));
        customer.setEmail(customerDetails.getEmail());
        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        return ResponseEntity.ok().body(customerRepository.save(customer));
    }
}
