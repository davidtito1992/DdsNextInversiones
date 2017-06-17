package formulaIndicador;

import java.math.BigDecimal;

import semanticaIndicador.SemanticaVariable;

public class Variable implements FormulaIndicador {

	private String nombre;

	public Variable(String nombreVariable) {
		this.nombre = nombreVariable;

	}

	@Override
	public BigDecimal calcular(String empresa, int anio, int semestre)
			throws RuntimeException {

		return new SemanticaVariable(this.nombre, empresa, anio, semestre)
				.valor();
	}

}