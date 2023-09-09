package org.akad.mhayo.orm_project.jpa_module.repository;


import org.akad.mhayo.orm_project.jpa_module.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    public List<Cart> findAllByCustomer_Id(long id);
}
