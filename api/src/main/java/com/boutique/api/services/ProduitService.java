package com.boutique.api.services;

import com.boutique.api.dtos.produits.ProduitCreationDto;
import com.boutique.api.dtos.produits.ProduitResponseDto;

public interface ProduitService {

    ProduitResponseDto createProduit(ProduitCreationDto produitCreationDto);

    ProduitResponseDto updateProduit(String publicId, ProduitCreationDto produitCreationDto);

    ProduitResponseDto getProduit(String publicId);

}
