package com.boutique.api.dtos.boutiques;

import com.boutique.api.dtos.horaireouverture.HoraireOuvertureCreationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoutiqueCreationDto {

    private String nom;
    private boolean isConge;
    Set<HoraireOuvertureCreationDto> horaireOuvertures;

}
