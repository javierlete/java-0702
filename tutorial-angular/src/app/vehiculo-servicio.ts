import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vehiculo } from './vehiculo';

const URL = 'http://localhost:8080/taller/api/v1/vehiculos/';

@Injectable({
  providedIn: 'root'
})
export class VehiculoServicio {

  private readonly http = inject(HttpClient);

  obtenerTodos(): Observable<Vehiculo[]> {
    return this.http.get<Vehiculo[]>(URL);
  }

  obtenerPorId(id: number | undefined) {
    return this.http.get<Vehiculo>(URL + id);
  }

  insertar(vehiculo: Vehiculo): Observable<Vehiculo> {
    delete vehiculo.id;
    return this.http.post(URL, vehiculo);
  }

  modificar(vehiculo: Vehiculo): Observable<Vehiculo> {
    return this.http.put(URL + vehiculo.id, vehiculo);
  }

  borrar(id: number): Observable<any> {
    return this.http.delete(URL + id);
  }
}