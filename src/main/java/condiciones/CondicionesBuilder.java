package condiciones;

import java.math.BigDecimal;

import model.RegistroIndicador;
import app.AppData;
import condiciones.CondicionCrecienteODecreciente.Criterio;

public class CondicionesBuilder {

	public Condicion crear(String tipoCondicion, String condicion, String indicador, int ultimosAnios, int compararOPeso){
		RegistroIndicador registroIndicador = new AppData().getRepositorioIndicadores().getRegistroIndicador(indicador);
		if(tipoCondicion == "Taxativa"){
			switch (condicion) {
			case "Creciente":
				return new CondicionCrecienteODecreciente(Criterio.CRECIENTE, registroIndicador, ultimosAnios);
			case "Decreciente":
				return new CondicionCrecienteODecreciente(Criterio.DECRECIENTE, registroIndicador, ultimosAnios);
			case "<":
				return new CondicionTaxativaMayorOMenorA(condiciones.CondicionSumatoria.Criterio.menorA, registroIndicador, ultimosAnios, BigDecimal.valueOf(compararOPeso));
			case ">":
				return new CondicionTaxativaMayorOMenorA(condiciones.CondicionSumatoria.Criterio.mayorA, registroIndicador, ultimosAnios, BigDecimal.valueOf(compararOPeso));
			case "Antiguedad":
				
			default:
				throw new Error("Esto no tendria que pasar");
			}
		}else{
			switch (condicion){
			case "<":
				return new CondicionCuantitativaMayorOMenorA(condiciones.CondicionSumatoria.Criterio.menorA, registroIndicador, ultimosAnios, compararOPeso);
			case ">":
				return new CondicionCuantitativaMayorOMenorA(condiciones.CondicionSumatoria.Criterio.mayorA, registroIndicador, ultimosAnios, compararOPeso);
			case "Antiguedad":
				return new CondicionAntiguedad(compararOPeso);
			default:
				throw new Error("Esto no tendria que pasar");
			}
		}
	}
}
