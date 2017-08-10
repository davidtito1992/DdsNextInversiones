package formulaIndicador;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Division implements Operacion {

	@Override
	public BigDecimal ejecutar(BigDecimal nodoIzquierdo, BigDecimal nodoDerecho) {

		return nodoIzquierdo.divide(nodoDerecho,2, RoundingMode.HALF_EVEN);
	}

}
