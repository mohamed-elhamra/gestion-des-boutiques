package com.boutique.api.services;

import com.boutique.api.commons.exceptions.BoutiqueException;
import com.boutique.api.commons.mappers.Mapper;
import com.boutique.api.dtos.CategorieDto;
import com.boutique.api.entities.Categorie;
import com.boutique.api.repositories.CategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private CategorieRepository categorieRepository;
    private Mapper mapper;

    @Override
    public CategorieDto createCategorie(CategorieDto categorieDto) {
        boolean categorieExists = categorieRepository.findByNom(categorieDto.getNom()).isPresent();

        if(categorieExists)
            throw new BoutiqueException("La catégorie avec le nom: " + categorieDto.getNom() + ", existe déjà.");
        Categorie createdCategorie = categorieRepository.save(mapper.toCategorie(categorieDto));

        return mapper.toCategorieDto(createdCategorie);
    }

}
