import { Component, Input, input, InputSignal, model } from '@angular/core';
import { LabelInput } from '../label-input/label-input';
import { Vehiculo } from '../vehiculo';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-formulario',
  imports: [LabelInput, JsonPipe],
  templateUrl: './formulario.html',
  styleUrl: './formulario.css'
})
export class Formulario {
  vehiculo = model<Vehiculo>({
    id: 0,
    matricula: '',
    bastidor: '',
    marca: '',
    modelo: '',
  });

  guardar() {
    console.log(this.vehiculo());
  }

}
