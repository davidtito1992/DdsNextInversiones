package main.formulaIndicador;

import java.math.BigDecimal;

public interface Operacion {
	BigDecimal ejecutar(BigDecimal nodoIzquierdo, BigDecimal nodoDerecho);

	public final int SCALE = 2;
}
