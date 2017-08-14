package condiciones;

import java.math.BigDecimal;
import model.RegistroIndicador;
import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

public class CondicionTaxativaMayorOMenorA extends CondicionSumatoria {

	private BigDecimal numeroAComparar;

	public CondicionTaxativaMayorOMenorA(MenorOMayor criterio,
			RegistroIndicador indicador, int ultimosAnios,
			BigDecimal nroAComparar) {
		super(criterio, indicador, ultimosAnios);
		this.numeroAComparar = nroAComparar;
	}

	public String textoError() {
		if (criterio.equals(MenorOMayor.menorA)) {
			return "La condicion menor a " + numeroAComparar.toString()
					+ " no se cumple para el indicador "
					+ indicador.getNombre();
		} else {
			return "La condicion mayor a " + numeroAComparar.toString()
					+ " no se cumple para el indicador "
					+ indicador.getNombre();
		}
	}

	public RankingEmpresa calcular(RankingEmpresa rEmpresa)
			throws ParseException {
		BigDecimal acumulador = sumador(rEmpresa.getEmpresa());
		if (this.comparar(acumulador, numeroAComparar)) {
			throw new RuntimeException(textoError());
		}
		;
		return rEmpresa;

	}

	private boolean comparar(BigDecimal acumulador, BigDecimal numeroAComparar) {
		if (criterio.equals(MenorOMayor.menorA)) {
			return acumulador.compareTo(numeroAComparar) > 0;
		} else {
			return acumulador.compareTo(numeroAComparar) < 0;
		}
	}
}
