package com.boutique.api.services;

import com.boutique.api.commons.exceptions.BoutiqueException;
import com.boutique.api.commons.mappers.CategorieMapper;
import com.boutique.api.commons.utils.Constants;
import com.boutique.api.commons.utils.IDGenerator;
import com.boutique.api.dtos.categories.CategorieCreationDto;
import com.boutique.api.dtos.categories.CategorieDto;
import com.boutique.api.entities.Categorie;
import com.boutique.api.repositories.CategorieRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private CategorieRepository categorieRepository;
    private CategorieMapper categorieMapper;
    private IDGenerator idGenerator;
    private final Logger logger = LoggerFactory.getLogger(CategorieServiceImpl.class);

    @Override
    public CategorieDto createCategorie(CategorieCreationDto categorieCreationDto) {
        logger.trace("Exécution de createCategorie()");
        logger.debug("Vérifier si la catégorie existe déjà.");
        boolean categorieExists = categorieRepository.findByNom(categorieCreationDto.getNom()).isPresent();

        if (categorieExists)
            throw new BoutiqueException("La catégorie avec le nom: " + categorieCreationDto.getNom() + ", existe déjà.");
        Categorie categorieToCreate = categorieMapper.toCategorie(categorieCreationDto);
        categorieToCreate.setPublicId(idGenerator.generateStringId(Constants.PUBLIC_ID_LENGTH));

        logger.debug("Création de la catégorie qui va être sauvegarder.");
        Categorie createdCategorie = categorieRepository.save(categorieToCreate);
        logger.debug("La catégorie a été sauvegardée.");

        return categorieMapper.toCategorieDto(createdCategorie);
    }

    @Override
    public CategorieDto updateCategorie(String publicId, CategorieCreationDto categorieCreationDto) {
        logger.trace("Exécution de updateCategorie()");
        logger.debug("Vérifier si la catégorie existe déjà.");
        Categorie categorie = categorieRepository.findByPublicId(publicId)
                .orElseThrow(() -> new BoutiqueException("La catégorie avec l'id: " + publicId + ", n'existe pas."));

        logger.debug("Vérifier si la catégorie existe déjà.");
        Optional<Categorie> categorieExists =
                categorieRepository.findByNom(categorieCreationDto.getNom());

        if (categorieExists.isPresent() && !categorie.getNom().equals(categorieExists.get().getNom()))
            throw new BoutiqueException("La catégorie avec le nom: " + categorieCreationDto.getNom() + ", existe déjà.");

        categorie.setNom(categorieCreationDto.getNom());

        logger.debug("MàJ de la catégorie.");
        return categorieMapper.toCategorieDto(categorieRepository.save(categorie));
    }

    @Override
    public void deleteCategorie(String publicId) {
        logger.trace("Exécution de deleteCategorie()");
        logger.debug("Vérifier si la catégorie existe.");
        Categorie categorie = categorieRepository.findByPublicId(publicId)
                .orElseThrow(() -> new BoutiqueException("La catégorie avec l'id: " + publicId + ", n'existe pas."));
        categorieRepository.delete(categorie);
        logger.debug("Catégorie supprimée.");
    }

}
