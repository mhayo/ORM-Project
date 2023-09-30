package org.akad.mhayo.orm_project.jdbc_module.service;


import org.akad.mhayo.orm_project.jdbc_module.dao.CustomerDAO;
import org.akad.mhayo.orm_project.jdbc_module.model.Customer;
import org.akad.mhayo.orm_project.util.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    long queryStart;
    long queryEnd;

    public Customer getCustomerById(long id){

        queryStart = System.currentTimeMillis();
        Customer temp = customerDAO.getCustomerById(id);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","getCustomerById",queryEnd-queryStart);

        return temp;
    }


    public List<Customer> getAllCustomers()  {

        queryStart = System.currentTimeMillis();
        List<Customer> temp = customerDAO.getAllCustomers();
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","getAllCustomers",queryEnd-queryStart);

        return temp;

    }

    public void save(Customer customer){

        queryStart = System.currentTimeMillis();
        customerDAO.save(customer);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","saveCustomer",queryEnd-queryStart);

    }

    public void update(Customer customer){

        queryStart = System.currentTimeMillis();
        customerDAO.update(customer);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","updateCustomer",queryEnd-queryStart);

    }

    public void delete(Customer customer){

        queryStart = System.currentTimeMillis();
        customerDAO.update(customer);
        queryEnd = System.currentTimeMillis();
        Measurement.writeToCsv("jdbc","deleteCustomer",queryEnd-queryStart);

    }

}
