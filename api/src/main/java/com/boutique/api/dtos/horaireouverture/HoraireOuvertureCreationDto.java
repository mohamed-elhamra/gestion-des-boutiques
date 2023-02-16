package com.boutique.api.dtos.horaireouverture;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoraireOuvertureCreationDto {

    @Min(value = 1, message = "Le jour doit être une valeur entre 1 (Dimanche) et 7 (Samedi).")
    @Max(value = 7, message = "Le jour doit être une valeur entre 1 (Dimanche) et 7 (Samedi).")
    private int jour;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime ouverture;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime fermeture;

}
