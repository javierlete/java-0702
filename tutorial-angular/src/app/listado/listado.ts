import { Component, inject, OnInit } from '@angular/core';
import { Vehiculo } from '../vehiculo';
import { VehiculoServicio } from '../vehiculo-servicio';
import { Formulario } from "../formulario/formulario";

@Component({
  selector: 'app-listado',
  imports: [Formulario],
  templateUrl: './listado.html',
  styleUrl: './listado.css'
})
export class Listado implements OnInit {
  // vehiculos: Vehiculo[] = [
    //   { id: 1, matricula: '1234ZXC' },
    //   { id: 2, matricula: '2222AAA' },
  //   { id: 3, matricula: '3333BBB' },
  //   { id: 4, matricula: '4444CCC' },
  // ];

  vehiculos: Vehiculo[] = [];
  vehiculo: Vehiculo = {};

  mostrarFormulario = false;
    
  private readonly vehiculoServicio = inject(VehiculoServicio);

  ngOnInit(): void {
    this.vehiculoServicio.obtenerTodos().subscribe(
      resultado => this.vehiculos = resultado
    )
  }

  editar(id: number | undefined): void {
    this.mostrarFormulario = true;

    this.vehiculoServicio.obtenerPorId(id).subscribe(
      v => this.vehiculo = v
    );
  }

  insertar() {
    this.mostrarFormulario = true;
  }

  refrescarListado(vehiculo: Vehiculo): void {
    if(vehiculo.id) {
       this.vehiculos = this.vehiculos.filter(v => v.id !== vehiculo.id);
    }

    this.vehiculos.push(vehiculo);
  }

  borrar(id?: number) {
    this.vehiculoServicio.borrar(id!).subscribe(
      () => this.vehiculos = this.vehiculos.filter(v => v.id !== id)
    );
  }
}
