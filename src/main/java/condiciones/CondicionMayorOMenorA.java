package condiciones;

import java.math.BigDecimal;

import model.RegistroIndicador;
import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

public class CondicionMayorOMenorA extends CondicionSumatoria {

	public enum Criterio {
		mayorA, menorA;
	}

	private Criterio criterio;
	private BigDecimal numeroAComparar;

	public CondicionMayorOMenorA(Criterio criterio,
			RegistroIndicador indicador, int ultimosAnios,
			BigDecimal nroAComparar) {
		super(indicador, ultimosAnios, 0);
		this.criterio = criterio;
		this.numeroAComparar = nroAComparar;
	}
	
	@Override
	public String toString(){
		return "Condicion " + criterio;
	}

	@Override
	public RankingEmpresa calcular(RankingEmpresa rEmpresa) throws ParseException {
		BigDecimal acumulador = sumador(rEmpresa.getEmpresa());
		if(comparar(acumulador,numeroAComparar)){
			throw new RuntimeException(this.toString());
		};
		return rEmpresa;

	}
	
	private boolean comparar(BigDecimal acumulador, BigDecimal numeroAComparar){
		if(criterio.equals(Criterio.menorA)){
			return acumulador.compareTo(numeroAComparar) > 0;
		}else {
			return acumulador.compareTo(numeroAComparar) < 0;
		}
	}
}
