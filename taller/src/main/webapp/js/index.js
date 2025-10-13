'use strict';

const URL = '/taller/api/v1/vehiculos/';

window.addEventListener('DOMContentLoaded', async () => {
	document.querySelector('button').addEventListener('click', guardar);

	await cargarListado();
});

async function cargarListado() {
	const respuesta = await fetch(URL);
	const vehiculos = await respuesta.json();

	const ul = document.querySelector('ul');

	ul.innerHTML = '';

	vehiculos.forEach(v => {
		const li = document.createElement('li');

		li.innerHTML = `
			<a href="javascript:editar(${v.id})">${v.matricula}</a>
			<a href="javascript:borrar(${v.id})">[X]</a>
		`;

		ul.appendChild(li);
	});
}

async function editar(id) {
	console.log('editar', id);

	const f = document.forms[0];

	const respuesta = await fetch(URL + id);
	const vehiculo = await respuesta.json();

	f.id.value = vehiculo.id;
	f.matricula.value = vehiculo.matricula;
	f.bastidor.value = vehiculo.bastidor;
	f.modelo.value = vehiculo.modelo;
	f.marca.value = vehiculo.marca;
	f.estadoReparacion.value = vehiculo.estadoReparacion;
}

async function guardar() {
	const f = document.forms[0];

	const vehiculo = {
		matricula: f.matricula.value,
		bastidor: f.bastidor.value,
		modelo: f.modelo.value,
		marca: f.marca.value,
		estadoReparacion: f.estadoReparacion.value
	};

	let respuesta;

	if (f.id.value) {
		vehiculo.id = f.id.value;
		
		respuesta = await fetch(URL + vehiculo.id, {
			method: 'PUT',
			body: JSON.stringify(vehiculo),
		});
	} else {
		respuesta = await fetch(URL, {
			method: 'POST',
			body: JSON.stringify(vehiculo),
		});
	}

	console.log(respuesta);

	f.reset();
	
	cargarListado();
}

async function borrar(id) {
	const respuesta = await fetch(URL + id, { method: 'DELETE' });

	console.log(respuesta);

	cargarListado();
}