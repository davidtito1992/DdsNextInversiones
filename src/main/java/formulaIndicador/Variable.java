package formulaIndicador;

import java.math.BigDecimal;

public class Variable extends FormulaIndicador {

	private String nombre;

	public Variable(String nombreVariable) {
		this.nombre = nombreVariable;

	}

	@Override
	public BigDecimal calcular(String empresa, int anio, int semestre) throws RuntimeException{
		// aca debemos calcular el valor de una variable cuenta o indicador
		// llegados aqui ya validamos que el nombre de la variable existe
		// tenemos que chequear si es una cuenta o indicador con la ayuda del
		// repo
        
		//HARDCODE PARA PROBAR si la variables implicadas no existen para las cuentas
		//e indicadores seleccionados
		//no deberia ser asi..es para probar el RuntimeException
		//se debe validar por nombre de empresa, anio y semestre
		if (this.nombre.equalsIgnoreCase("EBITDA")){
			throw new RuntimeException("Variable: "+this.nombre + " inexistente.") ;
		}
		
		
		// HARDCODE
		return new BigDecimal(10.0);
	}

}
