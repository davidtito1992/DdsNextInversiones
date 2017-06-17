package formulaIndicador;

import java.math.BigDecimal;

public class Resta implements Operacion {

	@Override
	public BigDecimal ejecutar(BigDecimal nodoIzquierdo,
			BigDecimal nodoDerecho) {

		return nodoIzquierdo.subtract(nodoDerecho);
	}
}
