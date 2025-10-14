class Titulo extends HTMLElement {
	get texto() { return this.getAttribute('texto'); }
	
	connectedCallback() {
		this.innerHTML = `<h1>${this.texto}</h1>`;
	}
}

customElements.define('jl-titulo', Titulo);
