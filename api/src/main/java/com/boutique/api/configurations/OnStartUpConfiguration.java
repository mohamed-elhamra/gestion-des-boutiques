package com.boutique.api.configurations;

import com.boutique.api.commons.utils.IDGenerator;
import com.boutique.api.entities.Boutique;
import com.boutique.api.entities.Categorie;
import com.boutique.api.entities.HoraireOuverture;
import com.boutique.api.entities.Produit;
import com.boutique.api.repositories.BoutiqueRepository;
import com.boutique.api.repositories.CategorieRepository;
import com.boutique.api.repositories.HoraireOuvertureRepository;
import com.boutique.api.repositories.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Configuration
@AllArgsConstructor
public class OnStartUpConfiguration implements CommandLineRunner {

    private CategorieRepository categorieRepository;
    private BoutiqueRepository boutiqueRepository;
    private HoraireOuvertureRepository horaireOuvertureRepository;
    private ProduitRepository produitRepository;
    private IDGenerator idGenerator;

    @Override
    public void run(String... args) {
        Set<Produit> produits = new HashSet<>();
        List<Categorie> categories;

        if (
                categorieRepository.findAll().isEmpty()
                        && boutiqueRepository.findAll().isEmpty()
                        && horaireOuvertureRepository.findAll().isEmpty()
                        && produitRepository.findAll().isEmpty()
        ) {

            // Creations des catégories
            categories = createCategories();


            for (int i = 1; i <= 10; i++) {
                // Creations des boutiques
                Boutique createdBoutique = initBoutique(i);
                Set<HoraireOuverture> hos = initHos();
                hos.forEach(ho -> ho.setBoutique(createdBoutique));
                horaireOuvertureRepository.saveAll(hos);

                // Creation produits
                for (int j = 1; j <= 10; j++) {
                    Produit produit = initProduit(i, j);
                    produit.setBoutique(createdBoutique);

                    Random random = new Random();
                    Set<Categorie> randomCategories = new HashSet<>();
                    for(int k = 1; k <= 3; k++){
                        int randomIndex = random.nextInt(categories.size());
                        Categorie randomCategorie = categories.get(randomIndex);
                        if (randomCategories.stream().noneMatch(cat -> cat.getNom().equals(randomCategorie.getNom()))) {
                            randomCategories.add(randomCategorie);
                        }
                    }

                    produit.setCategories(randomCategories);
                    produits.add(produit);
                }
            }
        }
        produitRepository.saveAll(produits);
    }


    List<Categorie> createCategories(){
        List<Categorie> categories = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Categorie categorie = new Categorie();
            categorie.setPublicId(idGenerator.generateStringId());
            categorie.setNom("Categorie " + i);
            categories.add(categorie);
        }
        return categorieRepository.saveAll(categories);
    }

    Boutique initBoutique(int i){
        Boutique boutique = new Boutique();
        boutique.setPublicId(idGenerator.generateStringId());
        boutique.setNom("Boutique " + i);
        boutique.setConge(false);
        boutique.setDateCreation(LocalDateTime.now());
        return boutiqueRepository.save(boutique);
    }

    Set<HoraireOuverture> initHos(){
        Set<HoraireOuverture> hos = new HashSet<>();
        for (int i = 1; i <= 7; i++) {
            HoraireOuverture ho = new HoraireOuverture();
            ho.setPublicId(idGenerator.generateStringId());
            ho.setJour(i);
            ho.setOuverture(LocalTime.parse("08:30:00"));
            ho.setFermeture(LocalTime.parse("18:30:00"));
            hos.add(ho);
        }
        return hos;
    }

    Produit initProduit(int i, int j){
        Produit produit = new Produit();
        produit.setPublicId(idGenerator.generateStringId());
        produit.setNom("Produit " + j + " B" + i);
        produit.setDescription("Produit " + j + " de la boutique " + i);
        produit.setPrix(j + 5.50F);
        produit.setQuantite((long) j);
        return produit;
    }

}
