package formulaIndicador;

import java.math.BigDecimal;

public class Division implements Operacion {

	@Override
	public BigDecimal ejecutar(BigDecimal nodoIzquierdo,
			BigDecimal nodoDerecho) {

		return nodoIzquierdo.divide(nodoDerecho);
	}

}
