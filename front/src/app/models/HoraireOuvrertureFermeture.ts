export class HoraireOuvertureFermeture{
    jour: number;
  ouverture: string;
  fermeture: string;

  constructor(jour: number, ouverture: string, fermeture: string) {
    this.jour = jour;
    this.ouverture = ouverture;
    this.fermeture = fermeture;
  }
}