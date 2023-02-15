package com.boutique.api.commons.mappers;

import com.boutique.api.dtos.produits.ProduitCreationDto;
import com.boutique.api.dtos.produits.ProduitResponseDto;
import com.boutique.api.entities.Produit;

@org.mapstruct.Mapper(componentModel = "spring")
public abstract class ProduitMapper {

    public abstract Produit toProduit(ProduitCreationDto produitCreationDto);

    public abstract ProduitResponseDto toProduitResponseDto(Produit produit);

}
