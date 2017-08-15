package condiciones;

import java.math.BigDecimal;

import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

public class CondicionCuantitativaAntiguedad extends Condicion {

	public BigDecimal peso;

	public CondicionCuantitativaAntiguedad(BigDecimal peso) {
		this.peso = peso;
	}

	public RankingEmpresa calcular(RankingEmpresa rEmpresa)
			throws ParseException {
		BigDecimal nuevoValor = new BigDecimal(rEmpresa.dameAntiguedadEmpresa())
				.multiply(peso);
		rEmpresa.acumularValor(nuevoValor);
		return rEmpresa;
	}

}
