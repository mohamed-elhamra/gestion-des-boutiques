package com.boutique.api.commons.mappers;

import com.boutique.api.dtos.categories.CategorieCreationDto;
import com.boutique.api.dtos.categories.CategorieResponseDto;
import com.boutique.api.entities.Categorie;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public abstract class CategorieMapper {

    public abstract Categorie toCategorie(CategorieCreationDto categorieCreationDto);

    public abstract CategorieResponseDto toCategorieResponseDto(Categorie categorie);

    public abstract List<CategorieResponseDto> toListCategorieResponseDto(List<Categorie> categorieList);

}
