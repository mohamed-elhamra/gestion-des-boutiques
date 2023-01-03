package com.boutique.api.services;

import com.boutique.api.dtos.categories.CategorieCreationDto;
import com.boutique.api.dtos.categories.CategorieDto;

import java.util.List;

public interface CategorieService {

    CategorieDto createCategorie(CategorieCreationDto categorieCreationDto);

    CategorieDto updateCategorie(String publicId, CategorieCreationDto categorieCreationDto);

    void deleteCategorie(String publicId);

    List<CategorieDto> getAllCategories();

}
