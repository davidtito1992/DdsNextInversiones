package formulaIndicador;

import java.math.BigDecimal;

public class Variable implements FormulaIndicador {

	private String nombre;
	private BigDecimal numero;

	public Variable(String nombreVariable) {
		this.nombre = nombreVariable;

	}

	public String getNombre() {

		return this.nombre;
	}

	public void setValor(BigDecimal num) {
		this.numero = num.setScale(SCALE);
	}

	@Override
	public BigDecimal calcular() {

		return this.numero;
	}
}