package com.ecommerce.commerce.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Stocke implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String nom;
    private Float prix, gain;
    private Integer quantite;
    @UpdateTimestamp
    private LocalDateTime date_modification;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime date_creaction;

    public Stocke() {
    }

    public Stocke(String nom, Float prix, Float gain, Integer quantite, LocalDateTime date_modification, LocalDateTime date_creaction) {
        this.nom = nom;
        this.prix = prix;
        this.gain = gain;
        this.quantite = quantite;
        this.date_modification = date_modification;
        this.date_creaction = date_creaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Float getGain() {
        return gain;
    }

    public void setGain(Float gain) {
        this.gain = gain;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public LocalDateTime getDate_modification() {
        return date_modification;
    }

    public void setDate_modification(LocalDateTime date_modification) {
        this.date_modification = date_modification;
    }

    public LocalDateTime getDate_creaction() {
        return date_creaction;
    }

    public void setDate_creaction(LocalDateTime date_creaction) {
        this.date_creaction = date_creaction;
    }

    @Override
    public String toString() {
        return "Stocke{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", gain=" + gain +
                ", quantite=" + quantite +
                ", date_modification=" + date_modification +
                ", date_creaction=" + date_creaction +
                '}';
    }
}
