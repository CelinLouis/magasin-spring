package com.ecommerce.commerce.repo;

import com.ecommerce.commerce.model.Historique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HistoriqueRepo extends JpaRepository<Historique, Long> {

    void deleteAchatById(Long id);

    Optional<Historique> findAchatById(Long id);

}
