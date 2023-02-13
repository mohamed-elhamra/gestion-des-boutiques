package com.boutique.api.services;

import com.boutique.api.commons.exceptions.BoutiqueException;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoutiqueServiceImpl implements BoutiqueService {

    private BoutiqueRepository boutiqueRepository;
    private HoraireOuvertureRepository horaireOuvertureRepository;
    private BoutiqueMapper boutiqueMapper;
    private IDGenerator idGenerator;
    private final Logger logger = LoggerFactory.getLogger(CategorieServiceImpl.class);

    @Override
    @Transactional
    public BoutiqueResponseDto createBoutique(BoutiqueCreationDto boutiqueCreationDto) {
        logger.trace("Exécution de createBoutique()");

        logger.debug("Vérifier si une boutique existe déjà avec le même nom.");
        boutiqueRepository.findByNom(boutiqueCreationDto.getNom())
                .ifPresent((boutique) -> {throw new BoutiqueException("Une boutique avec le nom: " + boutique.getNom() +", existe déjà");});

        Boutique boutique = boutiqueMapper.toBoutique(boutiqueCreationDto);
        Set<HoraireOuverture> horaireOuvertures = boutique.getHoraireOuvertures();

        logger.debug("Vérifier si l'horaire de fermeture est supérieur de l'horaire d'ouverture.");
        horaireOuvertures.forEach(ho -> {
            if (!ho.getOuverture().isBefore(ho.getFermeture()))
                throw new BoutiqueException("Horaire impossible, la fermeture doit être supérieur de l'ouverture");
            ho.setPublicId(idGenerator.generateStringId());
            ho.setBoutique(boutique);
        });

        boutique.setDateCreation(Instant.now());
        boutique.setPublicId(idGenerator.generateStringId());

        logger.debug("Sauvegarde de la boutique.");
        Boutique createdBoutique = boutiqueRepository.save(boutique);

        logger.debug("Sauvegarde des horaires d'ouvertures.");
        horaireOuvertureRepository.saveAll(horaireOuvertures);
        return boutiqueMapper.toBoutiqueResponseDto(createdBoutique);
    }

    @Override
    public List<BoutiqueResponseDto> listeBoutiques() {
        logger.trace("Exécution de listeBoutiques()");

        return boutiqueRepository.findAll().stream()
                .map(boutiqueMapper::toBoutiqueResponseDto)
                .collect(Collectors.toList());
    }

}
