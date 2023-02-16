import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateBoutiqueComponent } from './create-boutique/create-boutique.component';
import { CreateCategorieComponent } from './create-categorie/create-categorie.component';

const routes: Routes = [
  { path: 'createBoutique', component: CreateBoutiqueComponent },
  { path: 'createCategorie', component: CreateCategorieComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
