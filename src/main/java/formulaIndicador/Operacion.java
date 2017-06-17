package formulaIndicador;

import java.math.BigDecimal;

public interface Operacion {
	BigDecimal ejecutar(BigDecimal nodoIzquierdo,
			BigDecimal nodoDerecho);
}
