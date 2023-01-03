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
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private CategorieRepository categorieRepository;
    private CategorieMapper categorieMapper;
    private IDGenerator idGenerator;

    @Override
    public CategorieDto createCategorie(CategorieCreationDto categorieCreationDto) {
        boolean categorieExists = categorieRepository.findByNom(categorieCreationDto.getNom()).isPresent();

        if (categorieExists)
            throw new BoutiqueException("La catégorie avec le nom: " + categorieCreationDto.getNom() + ", existe déjà.");
        Categorie categorieToCreate = categorieMapper.toCategorie(categorieCreationDto);
        categorieToCreate.setPublicId(idGenerator.generateStringId(Constants.PUBLIC_ID_LENGTH));
        Categorie createdCategorie = categorieRepository.save(categorieToCreate);

        return categorieMapper.toCategorieDto(createdCategorie);
    }

    @Override
    public CategorieDto updateCategorie(String publicId, CategorieCreationDto categorieCreationDto) {
        Categorie categorie = categorieRepository.findByPublicId(publicId)
                .orElseThrow(() -> new BoutiqueException("La catégorie avec l'id: " + publicId + ", n'existe pas."));
        categorie.setNom(categorieCreationDto.getNom());

        return categorieMapper.toCategorieDto(categorieRepository.save(categorie));
    }

}
