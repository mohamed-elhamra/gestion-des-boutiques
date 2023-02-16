package com.boutique.api.dtos.produits;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitCreationDto {

    @Size(min = 4, max = 50, message = "Le nom doit être compris entre 4 et 20 caractères.")
    private String nom;

    @Size(min = 8, message = "La description doit être plus exhaustive.")
    private String description;

    @Min(value = 1, message = "Le prix doit être supérieur de 1 euro.")
    private Float prix;

    @Min(value = 1, message = "La quantité doit être au moins supérieur à 1.")
    private Long quantite;

    @NotBlank(message = "L'id public de la boutique est obligatoire.")
    private String boutiquePublicId;

    @NotBlank(message = "L'id public de la catégorie est obligatoire.")
    private List<String> categoriesPublicId;

}
