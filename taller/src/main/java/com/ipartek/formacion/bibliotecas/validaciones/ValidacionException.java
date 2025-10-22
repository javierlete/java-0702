package com.ipartek.formacion.bibliotecas.validaciones;

import java.util.Map;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.StandardException;

@StandardException
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class ValidacionException extends RuntimeException {

	private static final long serialVersionUID = -7349044535843235946L;

	private Map<Object, Object> errores;
}
