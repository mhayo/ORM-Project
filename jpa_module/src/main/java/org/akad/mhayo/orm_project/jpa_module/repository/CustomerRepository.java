package org.akad.mhayo.orm_project.jpa_module.repository;


import org.akad.mhayo.orm_project.jpa_module.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findCustomerByUsername(String username);

}
