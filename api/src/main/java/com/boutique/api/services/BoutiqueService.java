package com.boutique.api.services;


import com.boutique.api.dtos.boutiques.BoutiqueCreationDto;
import com.boutique.api.dtos.boutiques.BoutiqueResponseDto;

public interface BoutiqueService {

    BoutiqueResponseDto createBoutique(BoutiqueCreationDto boutiqueCreationDto);

}
