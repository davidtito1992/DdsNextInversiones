package condiciones;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

@Entity
@DiscriminatorValue("4")
public class CondicionTaxativaAntiguedad extends Condicion {

	public CondicionTaxativaAntiguedad() {

	}
	
	@Column(name = "nroAComparar")
	private BigDecimal nroAComparar;

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