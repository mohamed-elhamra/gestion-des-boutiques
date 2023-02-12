package com.boutique.api.services;

import com.boutique.api.commons.mappers.BoutiqueMapper;
import com.boutique.api.commons.utils.IDGenerator;
import com.boutique.api.dtos.boutiques.BoutiqueCreationDto;
import com.boutique.api.dtos.boutiques.BoutiqueResponseDto;
import com.boutique.api.entities.Boutique;
import com.boutique.api.entities.HoraireOuverture;
import com.boutique.api.repositories.BoutiqueRepository;
import com.boutique.api.repositories.HoraireOuvertureRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;

@Service
@AllArgsConstructor
public class BoutiqueServiceImpl implements BoutiqueService {

    BoutiqueRepository boutiqueRepository;
    HoraireOuvertureRepository horaireOuvertureRepository;
    BoutiqueMapper boutiqueMapper;
    private IDGenerator idGenerator;
    private final Logger logger = LoggerFactory.getLogger(CategorieServiceImpl.class);

    @Override
    public BoutiqueResponseDto createBoutique(BoutiqueCreationDto boutiqueCreationDto) {
        Boutique boutique = boutiqueMapper.toBoutique(boutiqueCreationDto);
        Set<HoraireOuverture> horaireOuvertures = boutique.getHoraireOuvertures();
        horaireOuvertures.forEach(ho -> {
            ho.setPublicId(idGenerator.generateStringId());
            ho.setBoutique(boutique);
        });

        boutique.setDateCreation(Instant.now());
        boutique.setPublicId(idGenerator.generateStringId());

        Boutique createdBoutique = boutiqueRepository.save(boutique);
        horaireOuvertureRepository.saveAll(horaireOuvertures);
        return boutiqueMapper.toBoutiqueResponseDto(createdBoutique);
    }

}
