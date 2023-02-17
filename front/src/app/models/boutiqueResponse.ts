import { HoraireOuvertureFermeture } from "./HoraireOuvrertureFermeture";

export class boutiqueResponse{
    publicId!: string;
    nom!: string;
    isConge!: boolean;
    dateCreation!: Date;
    horaireOuvertures!: HoraireOuvertureFermeture[];
}