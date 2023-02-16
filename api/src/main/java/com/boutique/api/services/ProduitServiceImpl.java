package com.boutique.api.services;

import com.boutique.api.commons.exceptions.BoutiqueException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProduitServiceImpl implements ProduitService {

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

    @Override
    public ProduitResponseDto updateProduit(String publicId, ProduitCreationDto produitCreationDto) {
        logger.trace("Exécution de updateProduit()");

        logger.debug("Vérifier si le produit existe avec cet id public.");
        Produit produitByPublicId = produitRepository.findByPublicId(publicId)
                .orElseThrow(() -> new BoutiqueException("Il n y a pas un produit avec cet id: " + publicId));

        logger.debug("Vérifier si la boutique existe avec cet id public.");
        Boutique boutiqueByPublicId = boutiqueRepository.findByPublicId(produitCreationDto.getBoutiquePublicId())
                .orElseThrow(() -> new BoutiqueException("Il n y a pas une boutique avec cet id: " + publicId));

        logger.debug("Vérifier si les categories existent avec cet id public.");
        Set<Categorie> categories = produitCreationDto.getCategoriesPublicId().stream()
                .map(this::fromPublicIdToCategorie)
                .collect(Collectors.toSet());

        logger.debug("Vérifier si un produit existe déjà avec le même nom dans la même boutique.");
        Optional<Produit> produitByNomAndBoutique = produitRepository.findByNomAndBoutique(produitCreationDto.getNom(), boutiqueByPublicId);
        if (produitByNomAndBoutique.isPresent() && produitByNomAndBoutique.get().getNom().equals(produitByPublicId.getNom()))
            throw new BoutiqueException("Un produit avec le nom (" + produitCreationDto.getNom() + ") existe déjà dans cette boutique.");

        logger.debug("MàJ du produit.");
        Produit produit = produitMapper.toProduit(produitCreationDto);
        produit.setId(produitByPublicId.getId());
        produit.setPublicId(produitByPublicId.getPublicId());
        produit.setBoutique(boutiqueByPublicId);
        produit.setCategories(categories);

        return produitMapper.toProduitResponseDto(produitRepository.save(produit));
    }

    private Categorie fromPublicIdToCategorie(String publicId) {
        return categorieRepository.findByPublicId(publicId)
                .orElseThrow(() -> new BoutiqueException("La catégorie avec l'id: " + publicId + ", n'existe pas."));
    }

}
