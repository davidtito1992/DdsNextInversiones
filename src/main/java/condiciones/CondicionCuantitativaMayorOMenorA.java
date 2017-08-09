package condiciones;

import java.math.BigDecimal;
import java.util.List;

import condiciones.CondicionTaxativaMayorOMenorA.Criterio;
import model.Empresa;
import model.Periodo;
import model.RegistroIndicador;
import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

public class CondicionCuantitativaMayorOMenorA extends Condicion {

	public enum Criterio {
		mayorA, menorA;
	}

	private Criterio criterio;
	private RegistroIndicador indicador;
	private double peso;
	
	public CondicionCuantitativaMayorOMenorA(RegistroIndicador indicador, int ultimosAnios,
			double peso) {
		this.peso = peso;
		this.indicador = indicador;
		this.ultimosAnios = ultimosAnios;
	}

	public BigDecimal sumador(Empresa empresa) throws ParseException {
		List<Periodo> periodos = super.periodosDesdexAnio(empresa);
		BigDecimal acumulador = BigDecimal.ZERO;

		for (int i = 0; i < periodos.size(); i++) {
			acumulador = acumulador.add(super.aplicarIndicador(indicador,
					empresa.getNombre(), periodos.get(i).getAnio(), periodos
							.get(i).getSemestre()));
		}
		return acumulador;
	}

	@Override
	public RankingEmpresa calcular(RankingEmpresa rEmpresa) throws ParseException {
		BigDecimal nuevoValor = sumador(rEmpresa.getEmpresa()).multiply(new BigDecimal(peso)).multiply(multiplicador());
		rEmpresa.acumularValor(nuevoValor);
		return rEmpresa;

	}
	
	private BigDecimal multiplicador(){
		if(criterio.equals(Criterio.menorA)){
			return BigDecimal.valueOf(-1);
		}else{
			return BigDecimal.valueOf(1);
		}
	}

}
