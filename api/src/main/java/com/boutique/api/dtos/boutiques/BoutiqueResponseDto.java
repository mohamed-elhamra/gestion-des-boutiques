package com.boutique.api.dtos.boutiques;

import com.boutique.api.dtos.horaireouverture.HoraireOuvertureCreationDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoutiqueResponseDto {

    private String publicId;
    private String nom;
    private boolean isConge;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreation;

    Set<HoraireOuvertureCreationDto> horaireOuvertures;

}
