package com.boutique.api.repositories;

import com.boutique.api.entities.Boutique;
import com.boutique.api.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    Optional<Produit> findByPublicId(String publicId);
    Optional<Produit> findByNomAndBoutique(String nom, Boutique boutique);

}
