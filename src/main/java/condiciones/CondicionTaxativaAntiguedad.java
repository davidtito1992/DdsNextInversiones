package condiciones;

import java.time.Year;
import java.util.ArrayList;
import java.util.stream.Collectors;

import parserIndicador.ParseException;
import model.Empresa;

public class CondicionTaxativaAntiguedad {
	private int limiteAnios;
	
	public CondicionTaxativaAntiguedad(int limiteAnios){
		this.limiteAnios = limiteAnios;
	}
	
	public boolean calcular(Empresa empresa) throws ParseException{
		ArrayList<Year> aniosPeriodos = (ArrayList<Year>) empresa.getPeriodos().stream()
		.map(periodo -> periodo.getAnio()).distinct().sorted().collect(Collectors.toCollection(ArrayList::new));
		
		return aniosPeriodos.get(0).getValue() < (2017 - limiteAnios);
	}
}
