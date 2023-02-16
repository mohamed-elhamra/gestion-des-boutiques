import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ICellRendererParams } from 'ag-grid-community';

@Component({
  selector: 'app-action-cell-renderer',
  template: `
    <button class="btn btn-primary mr-2" (click)="details()">Détails</button>
    <button class="btn btn-primary mr-2" (click)="edit()">Modifier</button>
    <button class="btn btn-danger" (click)="delete()">Supprimer</button>
  `,
})
export class ActionCellRendererComponent implements ICellRendererAngularComp {
  public params!: ICellRendererParams;

  constructor() {}

  agInit(params: ICellRendererParams): void {
    this.params = params;
  }

  refresh(params: ICellRendererParams): boolean {
    return true;
  }

  details(): void {
    this.params.context.componentParent.voirDetails(this.params.data.publicId);
  }

  edit(): void {
    this.params.context.componentParent.modifierCategorie(this.params.data.publicId);
  }

  delete(): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette catégorie?')) {
      this.params.context.componentParent.onDelete(this.params.data.publicId);
    }
  }
}
