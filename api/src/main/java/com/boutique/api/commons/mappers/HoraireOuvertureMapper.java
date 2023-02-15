package com.boutique.api.commons.mappers;

import com.boutique.api.dtos.horaireouverture.HoraireOuvertureCreationDto;
import com.boutique.api.dtos.horaireouverture.HoraireOuvertureResponseDto;
import com.boutique.api.entities.HoraireOuverture;
import org.mapstruct.Mapping;

import java.util.Set;

@org.mapstruct.Mapper(componentModel = "spring")
public abstract class HoraireOuvertureMapper {

    public abstract HoraireOuverture toHoraireOuverture(HoraireOuvertureCreationDto horaireOuvertureCreationDto);

    public abstract HoraireOuvertureResponseDto toHoraireOuvertureResponseDto(HoraireOuvertureResponseDto horaireOuvertureResponseDto);

    public abstract Set<HoraireOuverture> toListHoraireOuverture(Set<HoraireOuvertureCreationDto> listHoraireOuvertureCreationDto);

    public abstract Set<HoraireOuvertureResponseDto> toListHoraireOuvertureResponseDto(Set<HoraireOuverture> listHoraireOuverture);


}
