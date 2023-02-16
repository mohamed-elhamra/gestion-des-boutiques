package com.boutique.api.services;

import com.boutique.api.commons.exceptions.BoutiqueException;
import com.boutique.api.commons.mappers.CategorieMapper;
import com.boutique.api.commons.mappers.ProduitMapper;
import com.boutique.api.commons.utils.IDGenerator;
import com.boutique.api.dtos.produits.ProduitCreationDto;
import com.boutique.api.dtos.produits.ProduitResponseDto;
import com.boutique.api.entities.Boutique;
import com.boutique.api.entities.Categorie;
import com.boutique.api.entities.Produit;
import com.boutique.api.repositories.BoutiqueRepository;
import com.boutique.api.repositories.CategorieRepository;
import com.boutique.api.repositories.ProduitRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProduitServiceImpl implements ProduitService{

    private CategorieRepository categorieRepository;
    private BoutiqueRepository boutiqueRepository;
    private ProduitRepository produitRepository;
    private ProduitMapper produitMapper;
    private IDGenerator idGenerator;
    private final Logger logger = LoggerFactory.getLogger(CategorieServiceImpl.class);

    @Override
    public ProduitResponseDto createProduit(ProduitCreationDto produitCreationDto) {
        logger.trace("Exécution de createProduit()");

        logger.debug("Vérifier si la boutique existe.");
        Boutique boutiqueByPublicId = boutiqueRepository.findByPublicId(produitCreationDto.getBoutiquePublicId())
                .orElseThrow(() -> new BoutiqueException("Il n y a pas une boutique avec cet id: " + produitCreationDto.getBoutiquePublicId()));

        logger.debug("Vérifier si le produit existe dans cette boutique.");
        produitRepository.findByNomAndBoutique(produitCreationDto.getNom(), boutiqueByPublicId)
                .ifPresent(produit -> {
                    throw new BoutiqueException("Ce nom de produit (" + produit.getNom() + ") existe déjà dans cette boutique.");
                });

        logger.debug("Récupération des catégories.");
        Set<Categorie> categories = produitCreationDto.getCategoriesPublicId().stream()
                .map(this::fromPublicIdToCategorie)
                .collect(Collectors.toSet());

        Produit produit = produitMapper.toProduit(produitCreationDto);
        produit.setPublicId(idGenerator.generateStringId());
        produit.setBoutique(boutiqueByPublicId);
        produit.setCategories(categories);

        logger.debug("Sauvegarde produit.");
        return produitMapper.toProduitResponseDto(produitRepository.save(produit));
    }

    private Categorie fromPublicIdToCategorie(String publicId){
        return categorieRepository.findByPublicId(publicId)
                .orElseThrow(() -> new BoutiqueException("La catégorie avec l'id: " + publicId + ", n'existe pas."));
    }

}
