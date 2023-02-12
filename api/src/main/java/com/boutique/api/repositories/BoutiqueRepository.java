package com.boutique.api.repositories;

import com.boutique.api.entities.Boutique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoutiqueRepository extends JpaRepository<Boutique, Long> {

    Optional<Boutique> findByNom(String nom);

}
