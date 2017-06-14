package formulaIndicador;

import java.math.BigDecimal;

public class Variable extends FormulaIndicador {

	private String nombre;

	public Variable(String nombreVariable) {
		this.nombre = nombreVariable;

	}

	@Override
	public BigDecimal calcular(String empresa, int anio, int semestre) {
		//aca debemos calcular el valor de una variable cuenta o indicador
		//llegados aqui ya validamos que el nombre de la variable existe
		//tenemos que chequear si es una cuenta o indicador con la ayuda del repo
		
		//HARDCODE
		return new BigDecimal(10.0);
	}

}
