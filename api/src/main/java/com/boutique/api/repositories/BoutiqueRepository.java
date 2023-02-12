package com.boutique.api.repositories;

import com.boutique.api.entities.Boutique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoutiqueRepository extends JpaRepository<Boutique, Long> {
}
