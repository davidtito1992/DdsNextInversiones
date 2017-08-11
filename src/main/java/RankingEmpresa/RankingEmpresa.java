package RankingEmpresa;

import java.math.BigDecimal;

import model.Empresa;

import org.uqbar.commons.utils.Observable;

@Observable
public class RankingEmpresa {

	private BigDecimal ranking;
	private Empresa empresa;
	private String observaciones;
	public Boolean errorTaxativa;

	public RankingEmpresa(Empresa empresa) {
		this.ranking = BigDecimal.ZERO;
		this.empresa = empresa;
		this.errorTaxativa = false;
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

	public Boolean getErrorTaxativa() {
		return this.errorTaxativa;
	}

	public void setErrorTaxativa(Boolean errorTaxativa) {
		this.errorTaxativa = errorTaxativa;
	}

	public void acumularValor(BigDecimal nuevoValor) {
		this.ranking = ranking.add(nuevoValor);

	}

}