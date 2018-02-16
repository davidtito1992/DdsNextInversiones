package main.condiciones;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import main.parserIndicador.ParseException;
import main.rankingEmpresa.RankingEmpresa;
import model.RegistroIndicador;

@Entity
@DiscriminatorValue("CuantitativaMayorOMenor")
public class CondicionCuantitativaMayorOMenorA extends CondicionSumatoria {

	private BigDecimal peso;

	public CondicionCuantitativaMayorOMenorA() {
	}

	public CondicionCuantitativaMayorOMenorA(MenorOMayor criterio,
			RegistroIndicador indicador, int ultimosAnios, BigDecimal peso) {
		super(criterio, indicador, ultimosAnios);
		this.peso = peso;
	}

	public RankingEmpresa calcular(RankingEmpresa rEmpresa)
			throws ParseException {
		BigDecimal nuevoValor = sumador(rEmpresa.getEmpresa()).multiply(peso)
				.multiply(multiplicador());
		rEmpresa.acumularValor(nuevoValor);
		return rEmpresa;

	}

	private BigDecimal multiplicador() {
		if (criterio.equals(MenorOMayor.menorA)) {
			return BigDecimal.valueOf(-1);
		} else {
			return BigDecimal.valueOf(1);
		}
	}

}
