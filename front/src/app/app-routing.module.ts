import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateBoutiqueComponent } from './create-boutique/create-boutique.component';
import { CreateCategorieComponent } from './create-categorie/create-categorie.component';
import { ListeCategoriesComponent } from './liste-categories/liste-categories.component';
import { DetailsCategorieComponent } from './details-categorie/details-categorie.component';
import { UpdateCategorieComponent } from './update-categorie/update-categorie.component';
const routes: Routes = [
  { path: 'createBoutique', component: CreateBoutiqueComponent },
  { path: 'createCategorie', component: CreateCategorieComponent },
  { path: 'listeCategories', component: ListeCategoriesComponent },
  { path: 'categories/:publicId', component: DetailsCategorieComponent },
  { path: 'updateCategorie/:publicId', component: UpdateCategorieComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
