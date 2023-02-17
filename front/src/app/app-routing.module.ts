import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateBoutiqueComponent } from './create-boutique/create-boutique.component';
import { CreateCategorieComponent } from './create-categorie/create-categorie.component';
import { ListeCategoriesComponent } from './liste-categories/liste-categories.component';
import { UpdateCategorieComponent } from './update-categorie/update-categorie.component';
import { DetailsCategorieComponent } from './details-categorie/details-categorie.component';
import { ListeBoutiquesComponent } from './liste-boutiques/liste-boutiques.component';
import { CreateProduitComponent } from './create-produit/create-produit.component';
import { ListeProduitsComponent } from './liste-produits/liste-produits.component';

const routes: Routes = [
  { path: 'createBoutique', component: CreateBoutiqueComponent },
  { path: 'createCategorie', component: CreateCategorieComponent },
  { path: 'listeCategories', component: ListeCategoriesComponent },
  { path: 'listeBoutiques', component: ListeBoutiquesComponent },
  { path: 'categories/:publicId', component: DetailsCategorieComponent },
  { path: 'updateCategorie/:publicId', component: UpdateCategorieComponent },
  { path: 'createProduit', component: CreateProduitComponent },
  { path: 'listeProduits', component: ListeProduitsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
