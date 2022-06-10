package com.ecommerce.commerce.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Historique implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "produit")
    private Stocke produit;
    private Float unitaire;
    private Integer quantite;
    private Float prix_total;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime date_achat;

    public Historique() {
    }

    public Historique(Stocke produit, Float unitaire, Integer quantite, Float prix_total, LocalDateTime date_achat) {
        this.produit = produit;
        this.unitaire = unitaire;
        this.quantite = quantite;
        this.prix_total = prix_total;
        this.date_achat = date_achat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stocke getProduit() {
        return produit;
    }

    public void setProduit(Stocke produit) {
        this.produit = produit;
    }

    public Float getUnitaire() {
        return unitaire;
    }

    public void setUnitaire(Float unitaire) {
        this.unitaire = unitaire;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(Float prix_total) {
        this.prix_total = prix_total;
    }

    public LocalDateTime getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(LocalDateTime date_achat) {
        this.date_achat = date_achat;
    }

    @Override
    public String toString() {
        return "Historique{" +
                "id=" + id +
                ", produit_id=" + produit +
                ", unitaire=" + unitaire +
                ", quantite=" + quantite +
                ", prix_total=" + prix_total +
                ", date_achat=" + date_achat +
                '}';
    }
}
