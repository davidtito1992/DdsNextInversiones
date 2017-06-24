package formulaIndicador;

import java.math.BigDecimal;

public class Variable implements FormulaIndicador {

	private String nombre;

	public Variable(String nombreVariable) {
		this.nombre = nombreVariable;

	}

	public String getNombre() {

		return this.nombre;
	}

	private BigDecimal numero;

	public void setValor(BigDecimal num) {
		this.numero = num;
	}

	@Override
	public BigDecimal calcular() {

		return this.numero;
	}
}