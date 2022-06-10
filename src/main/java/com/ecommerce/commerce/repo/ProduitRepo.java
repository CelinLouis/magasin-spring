package com.ecommerce.commerce.repo;

import com.ecommerce.commerce.model.Stocke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProduitRepo extends JpaRepository<Stocke, Long> {

    /** DELETE Stocke , Historique FROM Stocke INNER JOIN Historique ON Stocke.id = Historique.produit WHERE Stocke.id = :id;**/
    /** DELETE FROM Stocke as contract WHERE contract.id = :id**/
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "DELETE FROM Historique WHERE Historique.produit = :id", nativeQuery = true)
    void deleteProduitById(Long id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "DELETE FROM Stocke as contract WHERE contract.id = :id", nativeQuery = true)
    void deleteParent(Long id);


    Optional<Stocke> findProduitById(Long id);

}
