package com.boutique.api.services;

import com.boutique.api.dtos.categories.CategorieCreationDto;
import com.boutique.api.dtos.categories.CategorieDto;

public interface CategorieService {

    CategorieDto createCategorie(CategorieCreationDto categorieCreationDto);

    CategorieDto updateCategorie(String publicId, CategorieCreationDto categorieCreationDto);

}
