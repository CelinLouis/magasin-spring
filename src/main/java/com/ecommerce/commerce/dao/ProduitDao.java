package com.ecommerce.commerce.dao;

import com.ecommerce.commerce.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProduitDao extends JpaRepository<Produit,Integer> {
  //  public List<Produit> findAll();
    public  Produit findById(int id);
    public  Produit save(Produit product);
  // Produit findById(int id);
  // Produit update(Produit produit);
}
