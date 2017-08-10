package condiciones;

import java.math.BigDecimal;

import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

public class CondicionAntiguedadTaxativa extends Condicion {

	public BigDecimal nroAComparar;

	public CondicionAntiguedadTaxativa(BigDecimal nroAComparar) {
		this.nroAComparar = nroAComparar;
	}

	public String textoError() {
		return "La condicion taxativa antiguedad no supera los "
				+ nroAComparar.toString() + " anios ";
	}

	@Override
	public RankingEmpresa calcular(RankingEmpresa rEmpresa)
			throws ParseException {
		if (rEmpresa.dameAntiguedadEmpresa() < nroAComparar.intValue()) {
			throw new RuntimeException(textoError());
		}
		;
		return rEmpresa;
	}

}
