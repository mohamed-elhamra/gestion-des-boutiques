package com.boutique.api.dtos.categories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorieCreationDto {

    @Size(min = 4, max = 50, message = "Le nom doit être compris entre 4 et 20 caractères.")
    private String nom;

}
