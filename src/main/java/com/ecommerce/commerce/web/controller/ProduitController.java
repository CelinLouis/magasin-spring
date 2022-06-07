package com.ecommerce.commerce.web.controller;

import com.ecommerce.commerce.dao.ProduitDao;
import com.ecommerce.commerce.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProduitController {

    @Autowired
    private ProduitDao produitDao;
    @RequestMapping(value = "/Produits",method = RequestMethod.GET)
    public ResponseEntity<Object> listeProduits(){
        List<Map<String,Object>> res = new ArrayList<>();
        List<Produit> p = produitDao.findAll();
        p.forEach((produit -> {
            res.add(produit.toMap());
        }));
        return new ResponseEntity<Object>(res,HttpStatus.OK);

    }
    @RequestMapping(value = "/Produits/{id}", method = RequestMethod.GET)
    public Produit afficherUnProduit(@PathVariable int id) {

        return produitDao.findById(id);
    }
    @DeleteMapping(value = "/Produits")
    public ResponseEntity<Object> supprimerProduit(@RequestBody Produit produit){
        Produit produit1 = produitDao.findById(produit.getId());
        if(produit1 != null){
            produitDao.delete(produit);
            return   ResponseEntity.ok("deleted successfully");
        }else {
            return  ResponseEntity.notFound().build();
        }
    }
    @PutMapping (value = "/Produits")
    public ResponseEntity<Object> updateProduit(@RequestBody Produit product) {
        //verify product
        Produit produit = produitDao.findById(product.getId());
        if(produit != null){
            Produit p = produitDao.save(product);
            if(p==null){
                return  ResponseEntity.noContent().build();
            }else{
                return   ResponseEntity.ok(p);
            }
        }else {
            return  ResponseEntity.notFound().build();
        }


    }
    @PostMapping(value = "/Produits")
    public ResponseEntity<Object> ajouterProduit(@RequestBody Produit produit){
        //Produit p = new Produit(Integer.parseInt(produit.get("id").toString()),produit.get("nom").toString(),Integer.parseInt(produit.get("prix").toString()),Integer.parseInt(produit.get("prixAchat").toString()));
        System.out.println(produit);
        Produit produitAdded = produitDao.save(produit);
        if(produitAdded == null){
            return  ResponseEntity.noContent().build();
        }else{
            return  ResponseEntity.ok().body(produitAdded);
        }


    }
}
