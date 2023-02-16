import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Categorie } from '../models/categorie.model';
import { HttpClient } from '@angular/common/http';
import { CategorieService } from '../services/categorie.service';
@Component({
  selector: 'app-create-categorie',
  templateUrl: './create-categorie.component.html',
  styleUrls: ['./create-categorie.component.css']
})
export class CreateCategorieComponent{

  categorie: Categorie = new Categorie();

  constructor(private http: HttpClient, private categorieService: CategorieService) {}

  onSubmit() {
    console.log(this.categorie);
    this.categorieService.ajouterCategorie(this.categorie.nom).subscribe(
      response => {
        console.log(response);
        alert('La catégorie a été ajoutée avec succès.');
      },
      error => {
        console.log(error);
        alert('Une erreur s\'est produite lors de l\'ajout de la catégorie.');
      }
    );
  }

}
