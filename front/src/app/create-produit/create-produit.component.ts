import { Component, OnInit } from '@angular/core';
import { boutiqueResponse } from '../models/boutiqueResponse';
import { CategorieResponse } from '../models/categorieResponse.model';
import { ProduitResponse } from '../models/produitResponse.model'; 
import { ProduitService } from '../services/produit.service';
import { CategorieService } from '../services/categorie.service';
import { BoutiqueService } from '../services/boutique.service';
import { Produit } from '../models/produit.model';



@Component({
  selector: 'app-create-produit',
  templateUrl: './create-produit.component.html',
  styleUrls: ['./create-produit.component.css']
})
export class CreateProduitComponent implements OnInit {

  produit: Produit = new Produit();
  boutiques: boutiqueResponse[] = [];
  categories: CategorieResponse[] = [];
  constructor(private produitService: ProduitService, private categorieService: CategorieService, private boutiqueService: BoutiqueService  ) { }

  ngOnInit(): void {
    this.boutiqueService.getAllBoutiques().subscribe(boutiques => this.boutiques = boutiques);
    this.categorieService.getAllCategories().subscribe(categories => this.categories = categories);
  }
  onSubmit() {
    this.produitService.createProduit(this.produit).subscribe(
      response => {
        console.log(response);
        alert('Le produit a été ajoutée avec succès.');
      },
      error => {
        console.log(error);
        alert('Une erreur s\'est produite lors de l\'ajout du produti.');
      }
    );
  }

}
