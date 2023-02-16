import { Component, OnInit } from '@angular/core';
import { CategorieService } from '../services/categorie.service';
import { Categorie } from '../models/categorie.model';
import { CategorieResponse } from '../models/categorieResponse.model';
import { Router } from '@angular/router';
import { ActionCellRendererComponent } from '../action-cell-renderer/action-cell-renderer.component';

@Component({
  selector: 'app-liste-categories',
  templateUrl: './liste-categories.component.html',
  styleUrls: ['./liste-categories.component.css']
})
export class ListeCategoriesComponent implements OnInit {

  categories: CategorieResponse[] = [];
  columnDefs = [
    {
      field: 'nom',
      sortable: true,
      resizable: true,
      filter: 'agTextColumnFilter',
      cellClass: ['text-center'],
    },
    {
      headerName: 'Action',
      sortable: true,
      resizable: true,
      filter: 'agTextColumnFilter',
      cellClass: ['text-center'],
      cellRenderer: 'actionCellRenderer',
    },
  ];

  defaultColDef = {
    flex: 1,
    minWidth: 100,
    resizable: true,
  };

  gridOptions = {
    context: {
      componentParent: this
    },
    rowHeight: 50,
  };

  
  frameworkComponents = {
    actionCellRenderer: ActionCellRendererComponent,
  };
  constructor(private categorieService: CategorieService, private router: Router) { }

  ngOnInit(): void {
    this.categorieService.getAllCategories().subscribe(
      response => {
        console.log(response);
        this.categories = response;
      },
      error => {
        console.log(error);
        alert('Une erreur s\'est produite lors de la récupération des catégories.');
      }
    );
  }
  
  onGridReady(params: any) {
    params.api.sizeColumnsToFit();
    params.context.componentParent = this;
    params.api.setColumnDefs(this.columnDefs);
    this.categorieService.getAllCategories().subscribe(
      (response) => {
        console.log(response);
        this.categories = response;
        params.api.setRowData(this.categories);
      },
      (error) => {
        console.log(error);
        alert("Une erreur s'est produite lors de la récupération des catégories.");
      }
    );
  }
  voirDetails(publicId: string) {
    this.router.navigate(['/categories', publicId]);
  }
  modifierCategorie(publicId: string) {
    this.router.navigate(['/updateCategorie', publicId]);
  }
  onDelete(publicId: string) {
    this.categorieService.deleteCategorie(publicId).subscribe(() => {
      this.categories = this.categories.filter(categorie => categorie.publicId !== publicId);
    });
  }
  confirmDelete(publicId: string) {
  if (confirm('Êtes-vous sûr de vouloir supprimer cette catégorie?')) {
    this.categorieService.deleteCategorie(publicId).subscribe(() => {
      this.categories = this.categories.filter(categorie => categorie.publicId !== publicId);
    });
  }
}

}
