package com.boutique.api.commons.mappers;

import com.boutique.api.dtos.categories.CategorieCreationDto;
import com.boutique.api.dtos.categories.CategorieDto;
import com.boutique.api.entities.Categorie;

@org.mapstruct.Mapper(componentModel = "spring")
public abstract class CategorieMapper {

    public abstract CategorieCreationDto toCategorieCreationDto(Categorie categorie);

    public abstract CategorieDto toCategorieDto(Categorie categorie);

    public abstract Categorie toCategorie(CategorieCreationDto categorieCreationDto);

    public abstract Categorie toCategorie(CategorieDto categorieDto);

}