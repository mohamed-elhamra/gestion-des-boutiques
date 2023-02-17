import { Component, OnInit } from '@angular/core';
import { Produit } from '../models/produit.model';
import { Boutique } from '../models/boutique.model';
import { ProduitService } from '../services/produit.service';
import { BoutiqueService } from '../services/boutique.service';
import { boutiqueResponse } from '../models/boutiqueResponse';
import { ProduitResponse } from '../models/produitResponse.model';

@Component({
  selector: 'app-liste-produits',
  templateUrl: './liste-produits.component.html',
  styleUrls: ['./liste-produits.component.css']
})
export class ListeProduitsComponent implements OnInit {

  produits: ProduitResponse[] = [];
  boutiques: boutiqueResponse[] = [];
  selectedBoutique: string = '';

  columnDefs = [
    { field: 'nom' },
    { field: 'description' },
    { field: 'prix' },
    { field: 'quantite' }
  ];

  frameworkComponents = {};

  defaultColDef = {
    flex: 1,
    minWidth: 150,
    filter: true,
    sortable: true,
  };

  gridOptions = {
    rowHeight: 50,
    headerHeight: 50,
    suppressCellSelection: true,
  };


  constructor(private produitService: ProduitService, private boutiqueService: BoutiqueService) { }

  ngOnInit(): void {
     // Récupérer la liste des boutiques
     this.boutiqueService.getAllBoutiques().subscribe(boutiques => {
      this.boutiques = boutiques;
      // Sélectionner la première boutique de la liste
      if (this.boutiques.length > 0) {
        this.selectedBoutique = this.boutiques[0].publicId;
        // Récupérer la liste des produits correspondant à la première boutique
        this.getProduitsByBoutique(this.selectedBoutique);
      }
    });
  }
  onBoutiqueChange() {
    this.getProduitsByBoutique(this.selectedBoutique);
  }

  getProduitsByBoutique(boutiqueId: string) {
    this.produitService.getProduitsByBoutique(boutiqueId).subscribe(produits => {
      console.log(this.produits = produits);
    });
  }
  onGridReady(params: any) {
    params.api.sizeColumnsToFit();
  }

}
