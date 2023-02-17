import { Component, OnInit } from '@angular/core';
import { BoutiqueService } from '../services/boutique.service'; 
import { boutiqueResponse } from '../models/boutiqueResponse'; 
import { Boutique } from '../models/boutique.model';
import { HoraireOuvertureFermeture } from '../models/HoraireOuvrertureFermeture';
import { ActionCellRendererComponent } from '../action-cell-renderer/action-cell-renderer.component';

@Component({
  selector: 'app-liste-boutiques',
  templateUrl: './liste-boutiques.component.html',
  styleUrls: ['./liste-boutiques.component.css']
})
export class ListeBoutiquesComponent implements OnInit {

  boutiques: boutiqueResponse[] = [];
  jours: string[] = ["", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"];

  private gridApi: any;
  private gridColumnApi: any;

  columnDefs = [
    { field: 'publicId', headerName: 'Public ID', sortable: true, filter: true },
    { field: 'nom', headerName: 'Nom', sortable: true, filter: true },
    { field: 'conge', headerName: 'Congé', sortable: true, filter: true,cellRenderer: (params: any) => params.value ? 'Oui' : 'Non'},
    { field: 'dateCreation', headerName: 'Date de création', sortable: true, filter: true },
    {    field: 'horaireOuvertures',    headerName: 'Horaires d\'ouverture',    cellRenderer: (params: any) => {      const horaires: HoraireOuvertureFermeture[] = params.value;
      let html = '<div class="horaires-container">';
      for (let i = 1; i <= 7; i++) {
        const horaire = horaires.find(h => h.jour === i);
        const ouverture = horaire ? horaire.ouverture : '';
        const fermeture = horaire ? horaire.fermeture : '';
        html += `
          <div class="horaires-row">
            <div class="horaires-cell"><b>Le</b> ${this.jours[i]} <b>Ouvre</b> ${ouverture} <b>& Ferme</b> ${fermeture}</div>
          
          </div>
        `;
      }
      html += '</div>';
      return html;
    },
    cellClass: 'ag-cell-wrap-text' // Ajout de la classe CSS pour envelopper le texte
  }
  ];

  defaultColDef = {
    resizable: true,
    flex: 1,
    minWidth: 100,
    filter: true,
    sortable: true
  };

  frameworkComponents = {
    actionCellRenderer: ActionCellRendererComponent,
  };
  gridOptions = {
    context: {
      componentParent: this
    },
    rowHeight: 300,
  };
  constructor(private boutiqueService: BoutiqueService) { }
  

  ngOnInit(): void {
    /*this.boutiqueService.getAllBoutiques().subscribe(
      data => {
        this.boutiques = data;
      },
      error => {
        console.log(error);
      }
    );*/
  }
  onGridReady(params: any) {
    params.api.sizeColumnsToFit();
    params.api.setColumnDefs(this.columnDefs);
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;
    this.boutiqueService.getAllBoutiques().subscribe(
      data => {
        this.boutiques = data;
        this.gridApi.setRowData(this.boutiques);
      },
      error => {
        console.log(error);
      }
    );
  }

}
