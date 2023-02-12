package com.boutique.api.commons.mappers;

import com.boutique.api.dtos.boutiques.BoutiqueCreationDto;
import com.boutique.api.dtos.boutiques.BoutiqueResponseDto;
import com.boutique.api.entities.Boutique;

@org.mapstruct.Mapper(componentModel = "spring")
public abstract class BoutiqueMapper {

    public abstract Boutique toBoutique(BoutiqueCreationDto boutiqueCreationDto);

    public abstract BoutiqueResponseDto toBoutiqueResponseDto(Boutique boutique);

}
