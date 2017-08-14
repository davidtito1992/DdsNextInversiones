package condiciones;

import java.math.BigDecimal;
import model.RegistroIndicador;
import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

public class CondicionCuantitativaMayorOMenorA extends CondicionSumatoria {

	private BigDecimal peso;

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
