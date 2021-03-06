package main.condiciones;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import main.parserIndicador.ParseException;
import main.rankingEmpresa.RankingEmpresa;
import model.Empresa;
import model.Periodo;
import model.RegistroIndicador;

@Entity
@DiscriminatorValue("CreceODecrece")
public class CondicionCrecienteODecreciente extends Condicion {

	@ManyToOne
	private RegistroIndicador indicador;
	private CreceODecrece criterio;

	public CondicionCrecienteODecreciente() {
	}

	public CondicionCrecienteODecreciente(CreceODecrece criterio,
			RegistroIndicador indicador, int ultimosAnios) {
		this.indicador = indicador;
		this.criterio = criterio;
		this.ultimosAnios = ultimosAnios;
	}

	public enum CreceODecrece {
		CRECIENTE, DECRECIENTE;
	}

	public String stringError() {
		if (criterio.equals(CreceODecrece.CRECIENTE)) {
			return "La condicion creciente no se cumple para el indicador "
					+ indicador.getNombre();
		} else {
			return "La condicion decreciente no se cumple para el indicador "
					+ indicador.getNombre();
		}
	}

	public RankingEmpresa calcular(RankingEmpresa rEmpresa)
			throws ParseException {
		Empresa empresa = rEmpresa.getEmpresa();
		List<Periodo> periodos = this.periodosDesdexAnio(empresa);

		for (int i = 0; i < periodos.size() - 1; i++) {

			BigDecimal indicadorActual = this.aplicarIndicador(rEmpresa
					.getEmpresa().getUser().getUserId(), indicador, empresa
					.getNombre(), periodos.get(i).getAnio(), periodos.get(i)
					.getSemestre());
			BigDecimal indicadorSiguiente = this.aplicarIndicador(rEmpresa
					.getEmpresa().getUser().getUserId(), indicador,
					empresa.getNombre(), periodos.get(i + 1).getAnio(),
					periodos.get(i + 1).getSemestre());

			if (comparar(indicadorActual, indicadorSiguiente)) {
				throw new RuntimeException(this.stringError());
			}
		}
		return rEmpresa;
	}

	private boolean comparar(BigDecimal indicadorActual,
			BigDecimal indicadorSiguiente) {
		if (criterio.equals(CreceODecrece.CRECIENTE)) {
			return indicadorActual.compareTo(indicadorSiguiente) >= 0;
		} else {
			return indicadorActual.compareTo(indicadorSiguiente) <= 0;
		}
	}

}
