import { Component, signal } from '@angular/core';
import { Listado } from "./listado/listado";

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.css',
  imports: [Listado]
})
export class App {
  protected readonly title = signal('Taller');
}
