package condiciones;

import parserIndicador.ParseException;
import model.Empresa;

public class CondicionTaxativaAntiguedad implements CondicionTaxativa {
	private int limiteAnios;

	public CondicionTaxativaAntiguedad(int limiteAnios) {
		this.limiteAnios = limiteAnios;
	}

	public boolean calcular(Empresa empresa) throws ParseException {

		return empresa.getAntiguedadEmpresa() < limiteAnios;
	}
}
