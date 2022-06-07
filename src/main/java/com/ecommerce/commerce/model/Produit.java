package com.ecommerce.commerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class Produit {
    @Id
    @GeneratedValue
    private int id;
    private  String nom;
    private int prix;
    private  int quantite;
    private  int prixAchat;

   // private Set<Achat> achats;
    public Produit(){

    }

    public Produit(int id, String nom, int prix,int prixAchat,int quantite) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.prixAchat = prixAchat;
        this.quantite = quantite;
    }

    public int getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    public Map<String,Object> toMap(){
        Map<String,Object> res = new HashMap<>();
        res.put("id",this.id);
        res.put("nom",this.nom);
        res.put("prix",this.prix);
        res.put("prixAchat",this.prixAchat);
        res.put("quantite",this.quantite);
        return  res;
    }
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString(){
        return "Product{"+
                "id=" + id +
                ", nom='"+ nom + '\'' +
                ", prix='"+ prix + '\'' +
                ", prixAchat='"+ prixAchat + '\'' +
                ", quantite=" + quantite+ '}';
    }
}
