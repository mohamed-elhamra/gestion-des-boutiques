import { HoraireOuvertureFermeture } from "./HoraireOuvrertureFermeture";

export class Boutique{
    nom: string = '';
    isConge: boolean = false;
    horaireOuvertures!: HoraireOuvertureFermeture[];
}