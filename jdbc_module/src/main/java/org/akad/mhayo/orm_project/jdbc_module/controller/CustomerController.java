package org.akad.mhayo.orm_project.jdbc_module.controller;


import org.akad.mhayo.orm_project.jdbc_module.dto.CustomerDTO;
import org.akad.mhayo.orm_project.jdbc_module.model.Customer;
import org.akad.mhayo.orm_project.jdbc_module.service.CustomerService;
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
    public List<CustomerDTO> getCustomers() {

            return customerService.getAllCustomers().stream().map(this::convertEntityToDTO).toList();

    }

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomerById(@PathVariable long id){

        return convertEntityToDTO(customerService.getCustomerById(id));
    }

    @PostMapping("/customer")
    public void saveCustomer(@RequestBody CustomerDTO customerDTO){

        customerService.save(convertDTOToEntity(customerDTO));
    }

    @PutMapping("/customer")
    public void updateCustomer (@RequestBody CustomerDTO customerDTO){

        customerService.update(convertDTOToEntity(customerDTO));
    }

    // DTO Conversion

    public CustomerDTO convertEntityToDTO(Customer customer){

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);

        return  customerDTO;
    }

    public Customer convertDTOToEntity(CustomerDTO customerDTO){

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);

        return customer;
    }

}
