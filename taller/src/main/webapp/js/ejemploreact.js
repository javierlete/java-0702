function App() {
	return <>
		<Titulo texto="Taller" />

		<LabelInput name="email" type="email" etiqueta="Email" />
		<LabelInput name="password" type="password" etiqueta="Contraseña" />
		<LabelInput name="tipo" type="select" etiqueta="Tipo" opciones={['Login', 'Registro']} />
	</>;
}

function Titulo({ texto }) {
	return <h1>{texto}</h1>;
}

function LabelInput({ etiqueta, type, placeholder, name, opciones }) {
	return <div style={{ display: 'flex' }}>
		<label style={{ width: '5rem' }} htmlFor={name}>{etiqueta}</label>
		{type === 'select' ?
			<select style={{ flexGrow: 1 }}>
				{opciones.map(o => <option>{o}</option>)}
			</select> :

			<input style={{ flexGrow: 1 }} type={type} name={name} placeholder={placeholder} />
		}
	</div>;

}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);