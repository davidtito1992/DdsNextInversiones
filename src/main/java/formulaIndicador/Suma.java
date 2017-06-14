package formulaIndicador;

import java.math.BigDecimal;

public class Suma implements Operacion {

	@Override
	public BigDecimal ejecutar(FormulaIndicador nodoIzquierdo,
			FormulaIndicador nodoDerecho) {
		return nodoIzquierdo.sumar(nodoDerecho);
	}
}
