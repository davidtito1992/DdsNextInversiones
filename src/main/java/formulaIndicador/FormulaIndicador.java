package formulaIndicador;

import java.math.BigDecimal;

public abstract class FormulaIndicador {

	private int semestre;
	private int anio;
	private String empresa;

	public abstract BigDecimal calcular(String empresa, int anio, int semestre);

	public BigDecimal sumar(FormulaIndicador addition) {

		return this.calcular(empresa, anio, semestre).add(
				addition.calcular(empresa, anio, semestre));
	}

	public BigDecimal restar(FormulaIndicador subraction) {
		return this.calcular(empresa, anio, semestre).subtract(
				subraction.calcular(empresa, anio, semestre));
	}

	public BigDecimal multiplicadoPor(FormulaIndicador multiple) {
		return this.calcular(empresa, anio, semestre).multiply(
				multiple.calcular(empresa, anio, semestre));
	}

	public BigDecimal divididoPor(FormulaIndicador divisor) {
		return this.calcular(empresa, anio, semestre).divide(
				divisor.calcular(empresa, anio, semestre));
	}

}
