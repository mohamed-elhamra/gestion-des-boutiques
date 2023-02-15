package com.boutique.api.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
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
    private LocalDateTime dateCreation;

    @OneToMany(mappedBy = "boutique")
    @ToString.Exclude
    private Set<Produit> produits = new HashSet<>();

    @OneToMany(mappedBy = "boutique")
    @ToString.Exclude
    private Set<HoraireOuverture> horaireOuvertures = new HashSet<>();

}
