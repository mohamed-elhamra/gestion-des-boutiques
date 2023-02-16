import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategorieService {

  constructor(private http: HttpClient) { }
  ajouterCategorie(nomCategorie: string): Observable<any> {
    const url = `${environment.apiURL}/categories`;
    const categorie = { nom: nomCategorie };
    return this.http.post(url, categorie);
  }
}
