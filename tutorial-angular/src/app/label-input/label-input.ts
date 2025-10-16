import { Component, EventEmitter, Input, input, model, Output, output } from '@angular/core';
import { Vehiculo } from '../vehiculo';
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
}
