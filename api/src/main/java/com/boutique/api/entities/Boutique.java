package com.boutique.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "boutiques")
public class Boutique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String publicId;

    @Column(length = 60, nullable = false, unique = true)
    private String nom;

    @Column(nullable = false)
    private boolean isConge;

    @Column(nullable = false)
    private Instant dateCreation;

    @OneToMany(mappedBy = "boutique")
    private Set<Produit> produits = new HashSet<>();

    @OneToMany(mappedBy = "horaireOuvertureId.boutique")
    private Set<HoraireOuverture> horaireOuvertures = new HashSet<>();

}
