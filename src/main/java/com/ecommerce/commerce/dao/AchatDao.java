package com.ecommerce.commerce.dao;

import com.ecommerce.commerce.model.Achat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchatDao extends JpaRepository<Achat,Integer> {
    Achat findById(int id);
    List<Achat> findByDate(String date);

}
