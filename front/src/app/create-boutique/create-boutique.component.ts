import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { BoutiqueService } from '../services/boutique.service';

@Component({
  selector: 'app-create-boutique',
  templateUrl: './create-boutique.component.html',
  styleUrls: ['./create-boutique.component.css']
})
export class CreateBoutiqueComponent implements OnInit {

  boutiqueForm!: FormGroup;
  submitted: boolean = false;

  constructor(private formBuilder: FormBuilder, private boutiqueService: BoutiqueService) { }

  ngOnInit(): void {
    this.boutiqueForm = this.formBuilder.group({
      nom: ['', Validators.required],
      conge: [false],
      horaireOuvertures: this.formBuilder.array([])
    });
  }

  get horaireOuvertures() {
    return this.boutiqueForm.get('horaireOuvertures') as FormArray;
  }

  addHoraireOuverture() {
    this.horaireOuvertures.push(this.createHoraireOuverture());
  }

  createHoraireOuverture(): FormGroup {
    return this.formBuilder.group({
      jour: ['', Validators.required],
      ouverture: ['', Validators.required],
      fermeture: ['', Validators.required]
    });
  }
 

  removeHoraireOuverture(index: number) {
    this.horaireOuvertures.removeAt(index);
  }

  onSubmit() {
    this.boutiqueService.createBoutique(this.boutiqueForm.value).subscribe(
        response => {
          console.log(response);
          alert('La boutique a été ajoutée avec succès.');
        },
        error => {
          console.log(error);
        alert('Une erreur s\'est produite lors de l\'ajout de la boutique.');
        }
      );

    // Soumettre le formulaire
    console.log(this.boutiqueForm.value);
  }

}
