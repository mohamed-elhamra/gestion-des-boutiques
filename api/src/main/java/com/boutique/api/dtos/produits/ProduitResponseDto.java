package com.boutique.api.dtos.produits;

import com.boutique.api.dtos.boutiques.BoutiqueResponseDto;
import com.boutique.api.dtos.categories.CategorieResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitResponseDto {

    private String publicId;
    private String nom;
    private String description;
    private Float prix;
    private Long quantite;
    private BoutiqueResponseDto boutique;
    private List<CategorieResponseDto> categories;

}
