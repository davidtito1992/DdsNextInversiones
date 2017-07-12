package condiciones;

import model.Empresa;
import parserIndicador.ParseException;

public class CondicionAntiguedad extends Condicion {

	public double peso;

	public CondicionAntiguedad(double peso) {
		this.peso = peso;
	}

	@Override
	public double calcularCuantitativa(Empresa empresa) throws ParseException {
		return empresa.getAntiguedadEmpresa() * peso;
	}

}
