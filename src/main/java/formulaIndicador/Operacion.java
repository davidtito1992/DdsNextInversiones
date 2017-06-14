package formulaIndicador;

import java.math.BigDecimal;

public interface Operacion {
	BigDecimal ejecutar(FormulaIndicador nodoIzquierdo, FormulaIndicador nodoDerecho);
}
