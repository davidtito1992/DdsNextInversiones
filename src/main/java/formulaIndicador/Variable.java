package formulaIndicador;

import java.math.BigDecimal;
import app.Dsl;

public class Variable implements FormulaIndicador {

	private String nombre;

	public Variable(String nombreVariable) {
		this.nombre = nombreVariable;

	}

	@Override
	public BigDecimal calcular(String empresa, int anio, int semestre)
			throws RuntimeException {

		return new Dsl().traducirVariable(this.nombre, empresa, anio, semestre);

	}

}