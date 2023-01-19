package com.boutique.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "horaire_ouverture")
public class HoraireOuverture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String publicId;

    private int jourOuverture;

    private Instant ouverture;

    private Instant fermeture;

    @ManyToOne()
    @JoinColumn(name = "boutique_id")
    private Boutique boutique;

}
