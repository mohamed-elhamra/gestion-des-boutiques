package com.boutique.api.dtos.boutiques;

import com.boutique.api.dtos.horaireouverture.HoraireOuvertureCreationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoutiqueResponseDto {

    private String publicId;
    private String nom;
    private boolean isConge;
    private Instant dateCreation;
    Set<HoraireOuvertureCreationDto> horaireOuvertures;

}
