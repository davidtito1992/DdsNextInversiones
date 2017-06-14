package formulaIndicador;

import java.math.BigDecimal;

public class Division implements Operacion {

	@Override
	public BigDecimal ejecutar(FormulaIndicador nodoIzquierdo,
			FormulaIndicador nodoDerecho) {
		
		return nodoIzquierdo.divididoPor(nodoDerecho);
	}

}
