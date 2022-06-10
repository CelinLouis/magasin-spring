package com.ecommerce.commerce.service;

import com.ecommerce.commerce.exception.UserNotFoundException;
import com.ecommerce.commerce.model.Stocke;
import com.ecommerce.commerce.repo.HistoriqueRepo;
import com.ecommerce.commerce.repo.ProduitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProduitService {
    private final ProduitRepo produitRepo;
    private final HistoriqueRepo historiqueRepo;

    @Autowired
    public ProduitService(ProduitRepo produitRepo, HistoriqueRepo historiqueRepo) {
        this.produitRepo = produitRepo;
        this.historiqueRepo = historiqueRepo;
    }



    public Stocke addProduit(Stocke produit){
        produit.setGain(Float.valueOf(0));
        return produitRepo.save(produit);
    }

    public List<Stocke> findAllProduit() {
        return produitRepo.findAll(Sort.by(Sort.Direction.ASC, "nom"));
    }

    public Stocke updateProduit(Stocke produit)
    {
        Integer qt=produit.getQuantite();
        produit.setQuantite(Integer.valueOf(qt));
        produit.setGain(Float.valueOf(produit.getGain()));
        return produitRepo.save(produit);
    }

    public Stocke approvisionnerProduit(Stocke produit)
    {
        produit.setGain(Float.valueOf(produit.getGain()));
        return produitRepo.save(produit);
    }

    public Stocke findProduitbyId(Long id) {
        return produitRepo.findProduitById(id)
                .orElseThrow(() -> new UserNotFoundException("Je n'est pas trouver id" + id + " dans la base"));
    }

    public void deleteProduit(Long id) {
        produitRepo.deleteProduitById(id);
        produitRepo.deleteParent(id);
    }

    public void deleteAll() {
        produitRepo.deleteAll();
    }

}
