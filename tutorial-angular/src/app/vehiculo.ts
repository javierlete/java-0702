import { EstadoReparacion } from "./estado-reparacion";

export interface Vehiculo {
    id?: number,
    matricula?: string,
    bastidor?: string,
    modelo?: string;
	marca?: string;
    estadoReparacion?: EstadoReparacion;
}
