package main.condiciones;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import main.parserIndicador.ParseException;
import main.rankingEmpresa.RankingEmpresa;

@Entity
@DiscriminatorValue("CuantitativaAntiguedad")
public class CondicionCuantitativaAntiguedad extends Condicion {

	public CondicionCuantitativaAntiguedad() {
	}

	public CondicionCuantitativaAntiguedad(BigDecimal peso) {
		this.peso = peso;
	}

	private BigDecimal peso;

	public RankingEmpresa calcular(RankingEmpresa rEmpresa)
			throws ParseException {
		BigDecimal nuevoValor = new BigDecimal(rEmpresa.dameAntiguedadEmpresa())
				.multiply(peso);
		rEmpresa.acumularValor(nuevoValor);
		return rEmpresa;
	}

}
