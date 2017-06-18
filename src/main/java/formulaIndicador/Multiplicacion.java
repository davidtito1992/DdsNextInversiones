package formulaIndicador;

import java.math.BigDecimal;

public class Multiplicacion implements Operacion {

	@Override
	public BigDecimal ejecutar(BigDecimal nodoIzquierdo, BigDecimal nodoDerecho) {
		return nodoIzquierdo.multiply(nodoDerecho);
	}

}
