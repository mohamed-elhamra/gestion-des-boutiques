package com.boutique.api.services;

import com.boutique.api.dtos.categories.CategorieCreationDto;

public interface CategorieService {

    CategorieCreationDto createCategorie(CategorieCreationDto categorieCreationDto);

    CategorieCreationDto updateCategorie(CategorieCreationDto categorieCreationDto);

}
