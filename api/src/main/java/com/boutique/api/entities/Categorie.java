package com.boutique.api.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "categories")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String publicId;

    @Column(length = 60, nullable = false, unique = true)
    private String nom;

    @ManyToMany(mappedBy = "categories")
    @ToString.Exclude
    private Set<Produit> produits;

}
