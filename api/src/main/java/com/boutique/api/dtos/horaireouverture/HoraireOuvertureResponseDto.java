package com.boutique.api.dtos.horaireouverture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoraireOuvertureResponseDto {

    private String publicId;
    private int jourOuverture;
    private LocalTime ouverture;
    private LocalTime fermeture;

}
