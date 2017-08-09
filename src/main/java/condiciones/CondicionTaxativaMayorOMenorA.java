package condiciones;

import java.math.BigDecimal;

import model.RegistroIndicador;
import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

public class CondicionTaxativaMayorOMenorA extends CondicionSumatoria {


	private BigDecimal numeroAComparar;

	public CondicionTaxativaMayorOMenorA(Criterio criterio,
			RegistroIndicador indicador, int ultimosAnios,
			BigDecimal nroAComparar) {
		super(criterio, indicador, ultimosAnios);
		this.numeroAComparar = nroAComparar;
	}
	
	
	public String textoError(){
		if(criterio.equals(Criterio.menorA)){
			return "La condicion menor a "+numeroAComparar.toString()+" no se cumple para el indicador "+indicador.getNombre();
		}else {
			return "La condicion mayor a "+numeroAComparar.toString()+" no se cumple para el indicador "+indicador.getNombre();
		}
	}

	@Override
	public RankingEmpresa calcular(RankingEmpresa rEmpresa) throws ParseException {
		BigDecimal acumulador = sumador(rEmpresa.getEmpresa());
		if(comparar(acumulador,numeroAComparar)){
			throw new RuntimeException(textoError());
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
