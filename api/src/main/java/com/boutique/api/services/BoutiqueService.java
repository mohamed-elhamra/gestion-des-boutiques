package com.boutique.api.services;


import com.boutique.api.dtos.boutiques.BoutiqueCreationDto;
import com.boutique.api.dtos.boutiques.BoutiqueResponseDto;
import com.boutique.api.dtos.produits.ProduitResponseDto;

import java.util.List;

public interface BoutiqueService {

    BoutiqueResponseDto createBoutique(BoutiqueCreationDto boutiqueCreationDto);

    List<BoutiqueResponseDto> listeBoutiques();

    BoutiqueResponseDto updateBoutique(String publicId, BoutiqueCreationDto boutiqueCreationDto);

    void deleteBoutique(String publicId);

    BoutiqueResponseDto getBoutique(String publicId);

    List<ProduitResponseDto> getProduits(String publicId);

}
