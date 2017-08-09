package condiciones;

import java.math.BigDecimal;

import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

public class CondicionAntiguedad extends Condicion {

	public double peso;

	public CondicionAntiguedad(double peso) {
		this.peso = peso;
	}

	@Override
	public RankingEmpresa calcular(RankingEmpresa rEmpresa) throws ParseException {
		
		BigDecimal valorAnterior = rEmpresa.getRanking();
		BigDecimal valorNuevo = new BigDecimal(rEmpresa.dameAntiguedadEmpresa() * peso);
		rEmpresa.setRanking(valorAnterior.add(valorNuevo));
		return rEmpresa;
	}

}
