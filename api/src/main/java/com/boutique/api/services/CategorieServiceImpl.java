package com.boutique.api.services;

import com.boutique.api.commons.exceptions.BoutiqueException;
import com.boutique.api.commons.mappers.CategorieMapper;
import com.boutique.api.commons.utils.Constants;
import com.boutique.api.commons.utils.IDGenerator;
import com.boutique.api.dtos.categories.CategorieCreationDto;
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
    public CategorieCreationDto createCategorie(CategorieCreationDto categorieCreationDto) {
        boolean categorieExists = categorieRepository.findByNom(categorieCreationDto.getNom()).isPresent();

        if(categorieExists)
            throw new BoutiqueException("La catégorie avec le nom: " + categorieCreationDto.getNom() + ", existe déjà.");
        Categorie categorieToCreate = categorieMapper.toCategorie(categorieCreationDto);
        categorieToCreate.setPublicId(idGenerator.generateStringId(Constants.PUBLIC_ID_LENGTH));
        Categorie createdCategorie = categorieRepository.save(categorieMapper.toCategorie(categorieCreationDto));

        return categorieMapper.toCategorieCreationDto(createdCategorie);
    }

    @Override
    public CategorieCreationDto updateCategorie(CategorieCreationDto categorieCreationDto) {
        return null;
    }

}
