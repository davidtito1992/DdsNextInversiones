package condiciones;

import model.Empresa;
import parserIndicador.ParseException;

public class CondicionCualitativaAntiguedad implements CondicionCualitativa{

	private double peso;
	
	public CondicionCualitativaAntiguedad(){
		this.peso = 0.3;
	}
	
	public double calcular(Empresa empresa) throws ParseException{
		
		return empresa.getAntiguedadEmpresa() * peso;
	}
}
