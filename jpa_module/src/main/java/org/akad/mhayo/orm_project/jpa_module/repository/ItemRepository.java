package org.akad.mhayo.orm_project.jpa_module.repository;


import org.akad.mhayo.orm_project.jpa_module.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
}
