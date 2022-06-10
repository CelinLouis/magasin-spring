package com.ecommerce.commerce;

import com.ecommerce.commerce.model.Stocke;
import com.ecommerce.commerce.service.HistoriqueService;
import com.ecommerce.commerce.service.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produit")
public class ProduitResource {
    private final ProduitService produitService;
    private final HistoriqueService historiqueService;


    public ProduitResource(ProduitService produitService, HistoriqueService historiqueService) {
        this.produitService = produitService;
        this.historiqueService = historiqueService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Stocke>> getAllProduit () {
        List<Stocke> produits = produitService.findAllProduit();
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Stocke> getproduitById (@PathVariable("id") Long id) {
        Stocke produit = produitService.findProduitbyId(id);
        return new ResponseEntity<>(produit, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Stocke> addProduit(@RequestBody Stocke produit) {
        Stocke newProduit = produitService.addProduit(produit);
        return new ResponseEntity<>(newProduit, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduit(@PathVariable("id") Long id) {
        /**historiqueService.deleteproduit(id);**/
        produitService.deleteProduit(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Stocke> updateProduit(@RequestBody Stocke produit) {
        Stocke produitId = produitService.findProduitbyId(produit.getId());
        String nom = produit.getNom();
        if (nom == ""){
         produit.setNom(produitId.getNom());
        }
        Stocke updateProduit = produitService.updateProduit(produit);
        return new ResponseEntity<>(updateProduit, HttpStatus.OK);
    }

    @PutMapping("/approvisionner")
    public ResponseEntity<Stocke> approvisionnerProduit(@RequestBody Stocke produit) {
        Stocke produitId = produitService.findProduitbyId(produit.getId());
        Integer oldQt = produitId.getQuantite();
        Integer newQt = produit.getQuantite();
        if ( newQt > 0) {
            produit.setQuantite(newQt + oldQt);
            Stocke updateProduit = produitService.approvisionnerProduit(produit);
            return ResponseEntity.ok().body(updateProduit);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            historiqueService.deleteAll();
            produitService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
