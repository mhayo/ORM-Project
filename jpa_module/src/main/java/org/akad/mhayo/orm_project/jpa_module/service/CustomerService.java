package org.akad.mhayo.orm_project.jpa_module.service;


import org.akad.mhayo.orm_project.jpa_module.model.Customer;
import org.akad.mhayo.orm_project.jpa_module.repository.CustomerRepository;
import org.akad.mhayo.orm_project.util.exceptions.CustomerException;
import org.springframework.stereotype.Service;
import org.akad.mhayo.orm_project.util.Measurement;


import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    long queryStart;
    long queryEnd;

    public void save(Customer customer){

        queryStart = System.currentTimeMillis();
        customerRepository.save(customer);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa","saveCustomer",queryEnd-queryStart);

    }

    public List<Customer> getAllCustomers(){

        queryStart = System.currentTimeMillis();
        List<Customer> temp = customerRepository.findAll();
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa","getAllCustomers",queryEnd-queryStart);

        return temp;

    }

    public Customer getCustomerByUsername(String username){

        return customerRepository.findCustomerByUsername(username);
    }


    public Customer getCustomer(long id){

        Customer customer;
        queryStart = System.currentTimeMillis();
        Optional<Customer> temp = customerRepository.findById(id);

        if(temp.isPresent()){
             customer = temp.get();
        }else {
            throw new CustomerException("No Customer found with id: " + id);
        }

        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa","getCustomerById",queryEnd-queryStart);

        return customer;
    }

    public void update(Customer customer){

        queryStart = System.currentTimeMillis();
        customerRepository.save(customer);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa","updateCustomer",queryEnd-queryStart);


    }

    public void delete(Customer customer){

        queryStart = System.currentTimeMillis();
        customerRepository.delete(customer);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jpa","deleteCustomer",queryEnd-queryStart);

    }

}