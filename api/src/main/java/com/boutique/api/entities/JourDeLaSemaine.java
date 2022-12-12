package com.boutique.api.entities;

import com.boutique.api.commons.enums.EJourDeLaSemaine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "jour_de_la_semaines")
public class JourDeLaSemaine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EJourDeLaSemaine jour;

    @OneToMany(mappedBy = "horaireOuvertureId.jourDeLaSemaine")
    private Set<HoraireOuverture> horaireOuvertures = new HashSet<>();

}
