package com.ecommerce.commerce.dao;

import com.ecommerce.commerce.model.Achat;
import com.ecommerce.commerce.model.Statistique;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatistiqueDao extends CrudRepository<Achat,Integer> {
    @Query("SELECT" +
            " new com.ecommerce.commerce.model.Statistique(a.date,SUM(a.quantite))" +
            " FROM Achat a " +
            " GROUP BY a.date ORDER BY a.date asc")
    List<Statistique> getStatistique();
}
