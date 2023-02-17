import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormBuilder, FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AgGridModule } from 'ag-grid-angular';
import { CreateBoutiqueComponent } from './create-boutique/create-boutique.component';
import { CreateCategorieComponent } from './create-categorie/create-categorie.component';
import { HttpClientModule } from '@angular/common/http';
import { ListeCategoriesComponent } from './liste-categories/liste-categories.component';
import { DetailsCategorieComponent } from './details-categorie/details-categorie.component';
import { UpdateCategorieComponent } from './update-categorie/update-categorie.component';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { ActionCellRendererComponent } from './action-cell-renderer/action-cell-renderer.component';
import { ListeBoutiquesComponent } from './liste-boutiques/liste-boutiques.component';
import { CreateProduitComponent } from './create-produit/create-produit.component';
import { ListeProduitsComponent } from './liste-produits/liste-produits.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateBoutiqueComponent,
    CreateCategorieComponent,
    ListeCategoriesComponent,
    DetailsCategorieComponent,
    UpdateCategorieComponent,
    ActionCellRendererComponent,
    ListeBoutiquesComponent,
    CreateProduitComponent,
    ListeProduitsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AgGridModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    ReactiveFormsModule
  ],
  providers: [
    FormBuilder
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
