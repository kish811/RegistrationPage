package com.Customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Customer.model.Customer;
import com.Customer.repository.CustomerRepository;

@CrossOrigin(origins ="http://localhost:3000")
@RestController
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	@PostMapping("/customers")
	Customer newCustomer (@RequestBody Customer newCustomer) {
		return customerRepository.save(newCustomer);
	}
	
	@GetMapping("/customers")
	List<Customer> getAllCustomer(){
		return customerRepository.findAll();
	}
	
	@DeleteMapping("/customers/{id}")
	 public String deleteCustomer(@PathVariable Long id){
        customerRepository.deleteById(id);
        return "Deleted successfully";
    }
	@PutMapping("customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updateCustomer) {
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid customer"+id));
        customer.setName(updateCustomer.getName());
        customer.setLastname(updateCustomer.getLastname());
        customer.setNumber(updateCustomer.getNumber());
        
        final Customer updatedCustomerData = customerRepository.save(customer);

        return ResponseEntity.ok(updatedCustomerData);
    }
	
	}
	