package com.boutique.api.services;

import com.boutique.api.commons.exceptions.BoutiqueException;
import com.boutique.api.commons.mappers.BoutiqueMapper;
import com.boutique.api.commons.mappers.HoraireOuvertureMapper;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoutiqueServiceImpl implements BoutiqueService {

    private BoutiqueRepository boutiqueRepository;
    private HoraireOuvertureRepository horaireOuvertureRepository;
    private BoutiqueMapper boutiqueMapper;
    private HoraireOuvertureMapper hoMapper;
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

        boutique.setDateCreation(LocalDateTime.now());
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

    @Override
    @Transactional
    public BoutiqueResponseDto updateBoutique(String publicId, BoutiqueCreationDto boutiqueCreationDto) {
        logger.trace("Exécution de updateBoutique()");

        Boutique boutiqueByPublicId = boutiqueRepository.findByPublicId(publicId)
                .orElseThrow(() -> new BoutiqueException("Il n y a pas une boutique avec cet id: " + publicId));
        Optional<Boutique> boutiqueByNom = boutiqueRepository.findByNom(boutiqueCreationDto.getNom());

        logger.debug("Vérifier si la boutique existe déjà avec le même nom.");
        if(boutiqueByNom.isPresent() && !boutiqueByNom.get().getNom().equals(boutiqueByPublicId.getNom()))
            throw new BoutiqueException("La boutique avec le nom: " + boutiqueCreationDto.getNom() + ", existe déjà.");

        logger.debug("MàJ de la boutique.");
        boutiqueByPublicId.setNom(boutiqueCreationDto.getNom());
        boutiqueByPublicId.setConge(boutiqueCreationDto.isConge());

        horaireOuvertureRepository.deleteAll(boutiqueByPublicId.getHoraireOuvertures());
        Set<HoraireOuverture> horaireOuvertures = hoMapper.toListHoraireOuverture(boutiqueCreationDto.getHoraireOuvertures());

        logger.debug("Vérifier si l'horaire de fermeture est supérieur de l'horaire d'ouverture.");
        horaireOuvertures.forEach(ho -> {
            if (!ho.getOuverture().isBefore(ho.getFermeture()))
                throw new BoutiqueException("Horaire impossible, la fermeture doit être supérieur de l'ouverture");
            ho.setPublicId(idGenerator.generateStringId());
            ho.setBoutique(boutiqueByPublicId);
        });

        logger.debug("MàJ des horaires d'ouvertures.");
        horaireOuvertureRepository.saveAll(horaireOuvertures);

        logger.debug("Sauvegarde de la boutique.");
        Boutique updatedBoutique = boutiqueRepository.save(boutiqueByPublicId);
        updatedBoutique.getHoraireOuvertures().clear();
        updatedBoutique.setHoraireOuvertures(horaireOuvertures);

        return boutiqueMapper.toBoutiqueResponseDto(updatedBoutique);
    }

}
