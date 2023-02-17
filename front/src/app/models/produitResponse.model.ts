import { boutiqueResponse } from "./boutiqueResponse";
import { CategorieResponse } from "./categorieResponse.model";


export class ProduitResponse{
    public publicId: string = "";
    public nom: string = "";
    public description: string = "";
    public prix: number = 0;
    public quantite: number = 0;
    public boutique!: boutiqueResponse;
    public categories!: CategorieResponse[];
}