package RankingEmpresa;

import java.math.BigDecimal;

import model.Empresa;

import org.uqbar.commons.utils.Observable;

@Observable
public class RankingEmpresa {

	private BigDecimal ranking;
	private Empresa empresa;
	private String observaciones ;

	public RankingEmpresa(BigDecimal ranking, Empresa empresa) {

		this.ranking = ranking;
		this.empresa = empresa;
	}

	public BigDecimal getRanking() {
		return ranking;
	}

	public void setRanking(BigDecimal ranking) {
		this.ranking = ranking;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int dameAntiguedadEmpresa() {
		return this.empresa.getAntiguedadEmpresa();
	}

	public void acumularValor(BigDecimal nuevoValor){
		this.ranking = ranking.add(nuevoValor);
		
	}
	
	
	
}