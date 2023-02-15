package com.boutique.api.services;

import com.boutique.api.dtos.categories.CategorieCreationDto;
import com.boutique.api.dtos.categories.CategorieResponseDto;

import java.util.List;

public interface CategorieService {

    CategorieResponseDto createCategorie(CategorieCreationDto categorieCreationDto);

    CategorieResponseDto updateCategorie(String publicId, CategorieCreationDto categorieCreationDto);

    void deleteCategorie(String publicId);

    List<CategorieResponseDto> getAllCategories();

    CategorieResponseDto getCategorie(String publicId);

}
