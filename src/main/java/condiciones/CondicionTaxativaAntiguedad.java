package condiciones;

import java.math.BigDecimal;

import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

public class CondicionTaxativaAntiguedad extends Condicion {

	public BigDecimal nroAComparar;

	public CondicionTaxativaAntiguedad(BigDecimal nroAComparar) {
		this.nroAComparar = nroAComparar;
	}

	public String textoError() {
		return "La condicion taxativa antiguedad no supera los "
				+ nroAComparar.toString() + " anios ";
	}

	public RankingEmpresa calcular(RankingEmpresa rEmpresa)
			throws ParseException {
		if (rEmpresa.dameAntiguedadEmpresa() < nroAComparar.intValue()) {
			throw new RuntimeException(textoError());
		}
		;
		return rEmpresa;
	}

}
