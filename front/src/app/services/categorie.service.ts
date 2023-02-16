import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CategorieResponse } from '../models/categorieResponse.model';
import { Categorie } from '../models/categorie.model';
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
  getAllCategories(): Observable<CategorieResponse[]> {
    const url = `${environment.apiURL}/categories`;
    return this.http.get<CategorieResponse[]>(url);
  }
  getCategorie(publicId: string): Observable<CategorieResponse> {
    const url = `${environment.apiURL}/categories/${publicId}`;
    return this.http.get<CategorieResponse>(url);
  }
  updateCategorie(id: string, categorie: Categorie): Observable<Categorie> {
    return this.http.patch<Categorie>(`${environment.apiURL}/categories/${id}`, categorie);
  }
  deleteCategorie(publicId: string): Observable<any> {
    const url = `${environment.apiURL}/categories/${publicId}`;
    return this.http.delete(url, {observe: 'response'});
  }
}
