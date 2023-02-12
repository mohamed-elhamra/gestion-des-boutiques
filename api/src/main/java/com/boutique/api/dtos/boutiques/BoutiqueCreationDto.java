package com.boutique.api.dtos.boutiques;

import com.boutique.api.dtos.horaireouverture.HoraireOuvertureCreationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoutiqueCreationDto {

    private String nom;
    private boolean isConge;
    @Valid
    Set<HoraireOuvertureCreationDto> horaireOuvertures;

}
