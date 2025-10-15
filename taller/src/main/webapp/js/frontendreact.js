'use strict';

function App() {
	const [idVehiculo, setIdVehiculo] = React.useState();
	
	return <>
		<Listado setIdVehiculo={setIdVehiculo} />
		<Formulario idVehiculo={idVehiculo} />
	</>;
}

function Listado({setIdVehiculo}) {
	const [vehiculos, setVehiculos] = React.useState([]);

	React.useEffect(() => {
		// Llamada a la API cuando el componente se monta
		fetch('http://localhost:8080/taller/api/v1/vehiculos/')
			.then(respuesta => respuesta.json())
			.then(vs => {
				setVehiculos(vs)
			})
			.catch(error => {
				console.error('Error al obtener usuarios:', error);
				setCargando(false);
			});
	}, []); // El array vacío [] asegura que solo se ejecute una vez al montar

	return <ul>{vehiculos.map(v => <li key={v.id}><button onClick={() => setIdVehiculo(v.id)}>{v.matricula}</button></li>)}</ul>;
}

function Formulario({idVehiculo}) {
	const [vehiculo, setVehiculo] = React.useState({});
	
	React.useEffect(() => {
			// Llamada a la API cuando el componente se monta
			idVehiculo && fetch('http://localhost:8080/taller/api/v1/vehiculos/' + idVehiculo)
				.then(respuesta => respuesta.json())
				.then(v => setVehiculo(v))
				.catch(error => {
					console.error('Error al obtener usuarios:', error);
					setCargando(false);
				});
		}, [idVehiculo]); // Reacciona cuando cambie el idVehiculo

	const opciones = [
		'PRESUPUESTO',
		'NO_REPARABLE',
		'PENDIENTE_ACEPTACION',
		'EN_CURSO',
		'REPARADO',
		'FACTURADO',
		'ENTREGADO',
		'RECIBIDO',
	];
	
	return <form>
	<p>{JSON.stringify(vehiculo)}</p>
		<LabelInput etiqueta="Id" name="id" value={vehiculo.id} />
		<LabelInput etiqueta="Matrícula" name="matricula" value={vehiculo.matricula} onChange={e => setVehiculo({...vehiculo, matricula: e.target.value})} />
		<LabelInput etiqueta="Bastidor" name="bastidor" value={vehiculo.bastidor} onChange={e => setVehiculo({...vehiculo, bastidor: e.target.value})} />
		<LabelInput etiqueta="Modelo" name="modelo" value={vehiculo.modelo} onChange={e => setVehiculo({...vehiculo, modelo: e.target.value})} />
		<LabelInput etiqueta="Marca" name="marca" value={vehiculo.marca} onChange={e => setVehiculo({...vehiculo, marca: e.target.value})} />
		<LabelInput etiqueta="Estado de la reparación" name="estadoReparacion" type="select" opciones={opciones} value={vehiculo.estadoReparacion} onChange={e => setVehiculo({...vehiculo, estadoReparacion: e.target.value})} />
	</form>
}

function LabelInput({ etiqueta, type, placeholder, name, opciones, value, onChange }) {
	return <div style={{ display: 'flex' }}>
		<label style={{ width: '5rem' }} htmlFor={name}>{etiqueta}</label>
		{type === 'select' ?
			<select style={{ flexGrow: 1 }} onChange={e => onChange(e)} value={value}>
				{opciones.map(o => <option key={o}>{o}</option>)}
			</select> :

			<input style={{ flexGrow: 1 }} type={type} name={name} placeholder={placeholder} value={value} onChange={e => onChange(e)}/>
		}
	</div>;
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);
