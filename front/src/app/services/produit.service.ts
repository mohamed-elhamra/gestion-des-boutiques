import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Produit } from '../models/produit.model';
import { ProduitResponse } from '../models/produitResponse.model';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {

  constructor(private http: HttpClient) { }

  createProduit(produitCreation: Produit): Observable<ProduitResponse> {
    console.log(`${environment.apiURL}/produits`);
    return this.http.post<ProduitResponse>(`${environment.apiURL}/produits`, produitCreation);
  }
  
  getProduitsByBoutique(boutiqueId: string): Observable<ProduitResponse[]> {
    const url = `${environment.apiURL}/boutiques/${boutiqueId}/produits`;
    return this.http.get<ProduitResponse[]>(url);
  }
}
