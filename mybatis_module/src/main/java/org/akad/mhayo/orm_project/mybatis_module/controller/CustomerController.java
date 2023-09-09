package org.akad.mhayo.orm_project.mybatis_module.controller;

import org.akad.mhayo.orm_project.mybatis_module.dto.CustomerDTO;
import org.akad.mhayo.orm_project.mybatis_module.model.Customer;
import org.akad.mhayo.orm_project.mybatis_module.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer")
    public List<CustomerDTO> getCustomers(){

        return customerService.getAllCustomers().stream().map(this::convertCustomerEntityToDTO).toList();
    }

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomerById(@PathVariable long id){

        return convertCustomerEntityToDTO(customerService.getCustomer(id));
    }

    @PostMapping("/customer")
    public void saveCustomer(@RequestBody CustomerDTO customerDTO){

        customerService.save(convertCustomerDTOtoEntity(customerDTO));
    }

    @PutMapping("/customer")
    public void updateCustomer (@RequestBody CustomerDTO customerDTO){

        customerService.update(convertCustomerDTOtoEntity(customerDTO));
    }

    // DTO Conversion

    public Customer convertCustomerDTOtoEntity(CustomerDTO customerDTO){

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);

        return customer;
    }

    public CustomerDTO convertCustomerEntityToDTO(Customer customer){

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        return customerDTO;
    }

}