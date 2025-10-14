class LabelInput extends HTMLElement {
	get etiqueta() { return this.getAttribute('etiqueta'); }

	get placeholder() {
		const p = this.getAttribute('placeholder');

		return p ?? '';
	}

	get name() { return this.getAttribute('name'); }

	get type() { return this.getAttribute('type'); }

	get opciones() { return this.getAttribute('opciones'); }

	connectedCallback() {
		let opcionesHtml = '';

		if (this.opciones) {
			JSON.parse(this.opciones).forEach(o => {
				opcionesHtml += `<option>${o}</option>`;
			});
		}
		
		this.innerHTML = `
			<div style="display: flex">
				<label style="width: 5rem" for="${this.name}">${this.etiqueta}</label>
				<${this.type === 'select' ? 'select' : 'input'} type="${this.type}" style="flex-grow: 1" placeholder="${this.placeholder}" id="${this.name}" name="${this.name}">
					${opcionesHtml}
				${this.type === 'select' ? '</select>' : ''}
			</div>
			`;
	}
}

customElements.define('jl-label-input', LabelInput);
