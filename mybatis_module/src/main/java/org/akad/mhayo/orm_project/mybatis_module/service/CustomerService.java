package org.akad.mhayo.orm_project.mybatis_module.service;


import org.akad.mhayo.orm_project.mybatis_module.mapper.CustomerMapper;
import org.akad.mhayo.orm_project.mybatis_module.model.Customer;
import org.akad.mhayo.orm_project.util.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CustomerService {


    @Autowired
    private CustomerMapper customerMapper;

    long queryStart;
    long queryEnd;

    public void save(Customer customer){

        queryStart = System.currentTimeMillis();
        customerMapper.insert(customer);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","saveCustomer",queryEnd-queryStart);


    }

    public List<Customer> getAllCustomers(){

        queryStart = System.currentTimeMillis();
        List<Customer> temp = customerMapper.getAllCustomers();
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","getAllCustomers",queryEnd-queryStart);

        return temp;

    }

    public Customer getCustomer(long id){

        queryStart = System.currentTimeMillis();
        Customer temp =  customerMapper.getCustomerById(id);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","getCustomerById",queryEnd-queryStart);
        return temp;
    }

    public void update(Customer customer){

        queryStart = System.currentTimeMillis();
        customerMapper.update(customer);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","updateCustomer",queryEnd-queryStart);


    }

    public void delete(long id){

        queryStart = System.currentTimeMillis();
        customerMapper.delete(id);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("mybatis","deleteCustomer",queryEnd-queryStart);

    }

}