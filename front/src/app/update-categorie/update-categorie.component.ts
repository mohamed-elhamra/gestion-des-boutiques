import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategorieService } from '../services/categorie.service';
import { CategorieResponse } from '../models/categorieResponse.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-update-categorie',
  templateUrl: './update-categorie.component.html',
  styleUrls: ['./update-categorie.component.css']
})
export class UpdateCategorieComponent implements OnInit {
  categorie!: CategorieResponse;
  updateForm: FormGroup = this.formBuilder.group({
    nom: ['', Validators.required]
  });

  constructor(
    private categorieService: CategorieService,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    const publicId = this.route.snapshot.paramMap.get('publicId') ?? '';
    this.categorieService.getCategorie(publicId).subscribe(categorie => {
      this.categorie = categorie;
      this.updateForm = this.formBuilder.group({
        nom: [this.categorie?.nom || '', Validators.required]
      });
    });
  }

  onSubmit() {
    const updatedCategorie = { nom: this.updateForm.value.nom };
    this.categorieService.updateCategorie(this.categorie.publicId, updatedCategorie).subscribe(() => {
      this.router.navigate(['/listeCategories']);
    });
  }

}
