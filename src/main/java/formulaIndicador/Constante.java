package formulaIndicador;

import java.math.BigDecimal;

public class Constante implements FormulaIndicador {

	private final BigDecimal numero;
	private static final int SCALE = 2;

	public Constante(String num) {
		this.numero = new BigDecimal(num).setScale(SCALE);
	}

	public BigDecimal calcular() {

		return this.numero;
	}
}