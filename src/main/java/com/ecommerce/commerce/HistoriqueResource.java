package com.ecommerce.commerce;

import com.ecommerce.commerce.model.Historique;
import com.ecommerce.commerce.model.Stocke;
import com.ecommerce.commerce.service.HistoriqueService;
import com.ecommerce.commerce.service.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historique")
public class HistoriqueResource {
    private final HistoriqueService historiqueService;
    private final ProduitService produitService;

    public HistoriqueResource(HistoriqueService historiqueService, ProduitService produitService) {
        this.historiqueService = historiqueService;
        this.produitService = produitService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Historique>> getAllAchats () {
        List<Historique> historiques = historiqueService.findAllAchat();
        return new ResponseEntity<>(historiques, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Historique> getAchatsById (@PathVariable("id") Long id) {
        Historique historique = historiqueService.findAchatById(id);
        return new ResponseEntity<>(historique, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Historique> addAchat(@RequestBody Historique historique) {
        Stocke produit1= historique.getProduit();
        Stocke produit = produitService.findProduitbyId(produit1.getId());
        Integer stock = produit.getQuantite();
        Float beni = produit.getGain();
        if (stock >= historique.getQuantite() && historique.getQuantite() > 0) {
            historique.setUnitaire(produit.getPrix());
            historique.setPrix_total(historique.getUnitaire() * historique.getQuantite());
            historique.setProduit(produit);
            produit.setGain(beni + historique.getPrix_total());
            produit.setQuantite(stock - historique.getQuantite());
            Historique newAchat = historiqueService.addHistorique(historique);
            return new ResponseEntity<>(newAchat, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAchatById(@PathVariable("id") Long id) {
        historiqueService.deleteAchat(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
