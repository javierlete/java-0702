<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ tag description="Unión de etiqueta con cuadro de formulario"
	pageEncoding="UTF-8"%>
<%@ attribute name="id" required="false"%>
<%@ attribute name="idBoton" required="false"%>
<%@ attribute name="etiqueta" required="false"%>
<%@ attribute name="nombre" required="false"%>
<%@ attribute name="valor" required="false"%>
<%@ attribute name="tipo" required="false"%>
<%@ attribute name="opciones" type="java.lang.Iterable" required="false"%>
<%@ attribute name="onclick" required="false"%>
<div id="${id}" class="row mb-3">
	<c:if test="${tipo != 'submit'}">
		<label for="${nombre}"
			class="col-sm-2 ${tipo == 'checkbox' ? 'form-check-label' : 'col-form-label'}">${etiqueta != null ? etiqueta : nombre}</label>
	</c:if>
	<div class="${tipo == 'submit' ? 'offset-sm-2' : ''} col-sm-10">
		<c:choose>
			<c:when test="${tipo == 'select'}">
				<select id="${nombre}" class="form-select" name="${nombre}">
					<c:forEach items="${opciones}" var="o">
						<option value="${o.id}" ${o.id == valor ? 'selected' : '' }>
							${o.nombre}
						</option>
					</c:forEach>
				</select>
			</c:when>
			<c:when test="${tipo == 'submit'}">
				<button id="${idBoton}" class="btn btn-primary">${etiqueta}</button>
			</c:when>
			<c:otherwise>
				<input type="${tipo}"
					class="form-${tipo == 'checkbox' ? 'check-input' : 'control' } ${errores == null ? '' : (errores.get(nombre) != null ? 'is-invalid' : 'is-valid') }"
					id="${nombre}" name="${nombre}" value="${valor}" ${tipo=='checkbox' && valor ? 'checked' : '' }
					onclick="${onclick}">
			</c:otherwise>
		</c:choose>
		<div class="invalid-feedback">${errores.get(nombre)}</div>
	</div>
</div>