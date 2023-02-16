import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CategorieResponse } from '../models/categorieResponse.model';
@Injectable({
  providedIn: 'root'
})
export class CategorieService {

  constructor(private http: HttpClient) { }
  ajouterCategorie(nomCategorie: string): Observable<CategorieResponse> {
    const url = `${environment.apiURL}/categories`;
    const categorie = { nom: nomCategorie };
    return this.http.post<CategorieResponse>(url, categorie);
  }
}
