package com.boutique.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "produits")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false, unique = true)
    private String nom;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Float prix;

    @Column(nullable = false)
    private Long quantite;

    @ManyToOne()
    @JoinColumn(name = "boutique_id")
    private Boutique boutique;

    @ManyToMany(mappedBy = "produits")
    private Set<Categorie> categories;

}
