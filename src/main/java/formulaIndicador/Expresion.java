package formulaIndicador;

import java.math.BigDecimal;

public class Expresion extends FormulaIndicador {

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
	public BigDecimal calcular(String empresa, int anio, int semestre) {
		           
		return operacion.ejecutar(nodoIzquierdo, nodoDerecho);
	}
}
