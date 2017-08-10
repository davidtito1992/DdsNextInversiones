package condiciones;

import java.math.BigDecimal;

import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

public class CondicionAntiguedadCuantitativa extends Condicion {

	public BigDecimal peso;

	public CondicionAntiguedadCuantitativa(BigDecimal peso) {
		this.peso = peso;
	}

	@Override
	public RankingEmpresa calcular(RankingEmpresa rEmpresa)
			throws ParseException {
		BigDecimal nuevoValor = new BigDecimal(rEmpresa.dameAntiguedadEmpresa())
				.multiply(peso);
		rEmpresa.acumularValor(nuevoValor);
		return rEmpresa;
	}

}
