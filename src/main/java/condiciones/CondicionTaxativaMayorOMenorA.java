package condiciones;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.RegistroIndicador;
import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

@Entity
public class CondicionTaxativaMayorOMenorA extends CondicionSumatoria {

	public CondicionTaxativaMayorOMenorA() {
	}
	
	public CondicionTaxativaMayorOMenorA(MenorOMayor criterio,
			RegistroIndicador indicador, int ultimosAnios,
			BigDecimal nroAComparar) {
		super(criterio, indicador, ultimosAnios);
		this.nroAComparar = nroAComparar;
	}
	
	private BigDecimal nroAComparar;


	public String textoError() {
		if (criterio.equals(MenorOMayor.menorA)) {
			return "La condicion menor a " + nroAComparar.toString()
					+ " no se cumple para el indicador "
					+ indicador.getNombre();
		} else {
			return "La condicion mayor a " + nroAComparar.toString()
					+ " no se cumple para el indicador "
					+ indicador.getNombre();
		}
	}

	public RankingEmpresa calcular(RankingEmpresa rEmpresa)
			throws ParseException {
		BigDecimal acumulador = sumador(rEmpresa.getEmpresa());
		if (this.comparar(acumulador, nroAComparar)) {
			throw new RuntimeException(textoError());
		}
		;
		return rEmpresa;

	}

	private boolean comparar(BigDecimal acumulador, BigDecimal nroAComparar) {
		if (criterio.equals(MenorOMayor.menorA)) {
			return acumulador.compareTo(nroAComparar) > 0;
		} else {
			return acumulador.compareTo(nroAComparar) < 0;
		}
	}
}
