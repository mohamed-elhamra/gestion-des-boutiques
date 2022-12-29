package com.boutique.api.commons.mappers;

import com.boutique.api.dtos.CategorieDto;
import com.boutique.api.entities.Categorie;

@org.mapstruct.Mapper(componentModel = "spring")
public abstract class Mapper {

    public abstract CategorieDto toCategorieDto(Categorie categorie);

    public abstract Categorie toCategorie(CategorieDto categorieDto);

}
