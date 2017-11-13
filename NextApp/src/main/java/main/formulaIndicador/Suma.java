package main.formulaIndicador;

import java.math.BigDecimal;

public class Suma implements Operacion {

	@Override
	public BigDecimal ejecutar(BigDecimal nodoIzquierdo, BigDecimal nodoDerecho) {
		return nodoIzquierdo.add(nodoDerecho).setScale(SCALE);
	}
}
