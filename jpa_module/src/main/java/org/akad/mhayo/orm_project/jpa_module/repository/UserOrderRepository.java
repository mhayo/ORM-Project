package org.akad.mhayo.orm_project.jpa_module.repository;

import org.akad.mhayo.orm_project.jpa_module.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
}
