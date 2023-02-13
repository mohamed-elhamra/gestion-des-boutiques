package com.boutique.api.services;


import com.boutique.api.dtos.boutiques.BoutiqueCreationDto;
import com.boutique.api.dtos.boutiques.BoutiqueResponseDto;

import java.util.List;

public interface BoutiqueService {

    BoutiqueResponseDto createBoutique(BoutiqueCreationDto boutiqueCreationDto);

    List<BoutiqueResponseDto> listeBoutiques();

}
