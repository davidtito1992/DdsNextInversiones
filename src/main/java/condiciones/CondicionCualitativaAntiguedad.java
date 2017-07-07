package condiciones;

import model.Empresa;
import parserIndicador.ParseException;

public class CondicionCualitativaAntiguedad implements CondicionCualitativa {

	private double peso;

	public CondicionCualitativaAntiguedad(double peso) {
		this.peso = peso;
	}

	public double calcular(Empresa empresa) throws ParseException {

		return empresa.getAntiguedadEmpresa() * peso;
	}
}
