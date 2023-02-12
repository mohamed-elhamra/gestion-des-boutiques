package com.boutique.api.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "horaire_ouverture")
public class HoraireOuverture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String publicId;

    @Column(nullable = false)
    private int jourOuverture;

    @Column(nullable = false)
    private LocalTime ouverture;

    @Column(nullable = false)
    private LocalTime fermeture;

    @ManyToOne()
    @JoinColumn(name = "boutique_id")
    private Boutique boutique;

}
