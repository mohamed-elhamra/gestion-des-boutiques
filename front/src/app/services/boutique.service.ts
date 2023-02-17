import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Boutique } from '../models/boutique.model'; 
import { boutiqueResponse } from '../models/boutiqueResponse'; 
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BoutiqueService {
  private baseUrl = `${environment.apiURL}/boutiques`;

  constructor(private http: HttpClient) { }

  createBoutique(boutiqueData: any): Observable<any> {
    return this.http.post(this.baseUrl, boutiqueData);
  }
}
