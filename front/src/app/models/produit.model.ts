import { boutiqueResponse } from "./boutiqueResponse";
import { CategorieResponse } from "./categorieResponse.model";


export class Produit{
nom: string = '';
  description: string = '';
  prix: number = 0;
  quantite: number = 0;
  boutiquePublicId: string = '';
  categoriesPublicId: string[] = [];
}