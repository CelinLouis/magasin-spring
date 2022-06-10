package com.ecommerce.commerce.service;

import com.ecommerce.commerce.exception.AchatNotFoundException;
import com.ecommerce.commerce.model.Historique;
import com.ecommerce.commerce.model.Stocke;
import com.ecommerce.commerce.repo.HistoriqueRepo;
import com.ecommerce.commerce.repo.ProduitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HistoriqueService{
    private final HistoriqueRepo historiqueRepo;
    private final ProduitRepo produitRepo;

    @Autowired
    public HistoriqueService(HistoriqueRepo historiqueRepo, ProduitRepo produitRepo) {
        this.historiqueRepo = historiqueRepo;
        this.produitRepo = produitRepo;
    }

    public Historique addHistorique(Historique historique) {
        return historiqueRepo.save(historique);
    }

    public List<Historique> findAllAchat() {
        return historiqueRepo.findAll();
    }

    public Historique findAchatById(Long id) {
        return historiqueRepo.findAchatById(id)
                .orElseThrow(() -> new AchatNotFoundException("Achat id " + id + "n'existe pas"));
    }

    public void deleteAchat(Long id) {
        historiqueRepo.deleteAchatById(id);
    }

    public void deleteAll() {
        produitRepo.deleteAll();
    }

}
