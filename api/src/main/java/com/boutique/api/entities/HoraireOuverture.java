package com.boutique.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "horaire_ouverture")
public class HoraireOuverture {

    @EmbeddedId
    private HoraireOuvertureId horaireOuvertureId;

    @Column(nullable = false, unique = true)
    private String publicId;

    private Instant ouverture;

    private Instant fermeture;

}
