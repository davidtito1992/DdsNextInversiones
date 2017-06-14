package formulaIndicador;

import java.math.BigDecimal;

public class Resta implements Operacion {

	@Override
	public BigDecimal ejecutar(FormulaIndicador nodoIzquierdo,
			FormulaIndicador nodoDerecho) {

		return nodoIzquierdo.restar(nodoDerecho);
	}
}
