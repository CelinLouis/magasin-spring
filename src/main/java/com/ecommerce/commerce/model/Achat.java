package com.ecommerce.commerce.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Achat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_achat;
    private int quantite;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "achat_produit",
            joinColumns = { @JoinColumn(name = "id_achat") },
            inverseJoinColumns = { @JoinColumn(name = "id") })
    private Set<Produit> produit;
    private float total;
    private String date;

    public Achat(int id_achat, int quantite,Set<Produit> produit, String date) {
        this.id_achat = id_achat;
        this.quantite = quantite;
        this.produit = produit;
        float t = 0;
        for(Produit p: produit){
            t += p.getPrix();
        }
        this.total = t*quantite;
        System.out.println(this.total);
        this.date = date;
    }

    public Achat() {
    }

    public int getId_achat() {
        return id_achat;
    }

    public void setId_achat(int id_achat) {
        this.id_achat = id_achat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Set <Produit> getProduit() {
        return produit;
    }

    public void setProduit(Set <Produit> produit) {
        this.produit = produit;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public Map<String,Object> toMap(){
        Map<String,Object> res = new HashMap<>();
        res.put("id_achat",this.id_achat);
        res.put("produit",this.produit);
        res.put("total",this.total);
        res.put("quantite",this.quantite);
        res.put("date",this.date);
        return  res;
    }
}
