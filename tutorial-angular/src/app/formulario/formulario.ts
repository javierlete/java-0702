import { Component, inject, Input, input, InputSignal, model, output } from '@angular/core';
import { LabelInput, Opcion } from '../label-input/label-input';
import { Vehiculo } from '../vehiculo';
import { JsonPipe } from '@angular/common';
import { VehiculoServicio } from '../vehiculo-servicio';

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
    estadoReparacion: undefined
  });

  guardado = output<Vehiculo>();

  private vehiculoServicio = inject(VehiculoServicio);

  estados: Opcion[] = [
    { texto: 'RECIBIDO' }, { texto: 'PRESUPUESTO' }, { texto: 'NO_REPARABLE' }, { texto: 'PENDIENTE_ACEPTACION' }, { texto: 'EN_CURSO' }, { texto: 'REPARADO' }, { texto: 'FACTURADO' }, { texto: 'ENTREGADO' }
  ]

  guardar() {
    console.log(this.vehiculo());

    if (this.vehiculo().id) {
      this.vehiculoServicio.modificar(this.vehiculo()).subscribe(
        vehiculoModificado => this.guardado.emit(vehiculoModificado)
      );
    } else {
      this.vehiculoServicio.insertar(this.vehiculo()).subscribe(
        vehiculoNuevo => this.guardado.emit(vehiculoNuevo)
      );
    }
  }

}
