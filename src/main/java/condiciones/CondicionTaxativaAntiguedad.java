package condiciones;

import java.math.BigDecimal;

import javax.persistence.Entity;

import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

@Entity
public class CondicionTaxativaAntiguedad extends Condicion {

	public CondicionTaxativaAntiguedad() {
	}

	public CondicionTaxativaAntiguedad(BigDecimal nroAComparar) {
		this.nroAComparar = nroAComparar;
	}

	private BigDecimal nroAComparar;

	public String textoError() {
		return "La condicion taxativa antiguedad no supera los " + nroAComparar.toString() + " anios ";
	}

	public RankingEmpresa calcular(RankingEmpresa rEmpresa) throws ParseException {
		if (rEmpresa.dameAntiguedadEmpresa() < nroAComparar.intValue()) {
			throw new RuntimeException(textoError());
		}
		;
		return rEmpresa;
	}

}