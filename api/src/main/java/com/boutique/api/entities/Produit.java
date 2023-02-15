package com.boutique.api.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "produits")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String publicId;

    @Column(length = 60, nullable = false, unique = true)
    private String nom;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Float prix;

    @Column(nullable = false)
    private Long quantite;

    @ManyToOne
    @JoinColumn(name = "boutique_id")
    private Boutique boutique;

    @ManyToMany(mappedBy = "produits")
    @ToString.Exclude
    private Set<Categorie> categories;

}
