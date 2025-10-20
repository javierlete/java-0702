import { Component, model } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'jl-label-input',
  imports: [FormsModule],
  templateUrl: './label-input.html',
  styleUrl: './label-input.css'
})
export class LabelInput {
  name = model<string>();
  etiqueta = model<string>();
  type = model<string>();
  placeholder = model<string>();
  value = model<any>();
  opciones = model<Opcion[]>();
}

export interface Opcion {
  id?: string;
  texto: string;
}