import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategorieResponse } from '../models/categorieResponse.model';
import { CategorieService } from '../services/categorie.service';

@Component({
  selector: 'app-details-categorie',
  templateUrl: './details-categorie.component.html',
  styleUrls: ['./details-categorie.component.css']
})
export class DetailsCategorieComponent implements OnInit {

  categorie: CategorieResponse | undefined;

  constructor(private categorieService: CategorieService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const publicId = this.route.snapshot.paramMap.get('publicId');
    if (publicId) {
      this.categorieService.getCategorie(publicId).subscribe(categorie => {
        this.categorie = categorie;
      });
    }
  }

}
