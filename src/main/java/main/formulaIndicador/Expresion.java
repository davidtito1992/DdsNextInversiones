package main.formulaIndicador;

import java.math.BigDecimal;

public class Expresion implements FormulaIndicador {

	private final FormulaIndicador nodoIzquierdo;
	private final FormulaIndicador nodoDerecho;
	private final Operacion operacion;

	public Expresion(FormulaIndicador nodoIzquierdo,
			FormulaIndicador nodoDerecho, Operacion operacion) {

		this.nodoIzquierdo = nodoIzquierdo;
		this.nodoDerecho = nodoDerecho;
		this.operacion = operacion;
	}

	@Override
	public BigDecimal calcular() {
		return operacion.ejecutar(nodoIzquierdo.calcular(),
				nodoDerecho.calcular()).setScale(SCALE);
	}

}
