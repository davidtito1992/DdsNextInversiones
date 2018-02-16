package main.condiciones;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import main.parserIndicador.ParseException;
import model.Empresa;
import model.Periodo;
import model.RegistroIndicador;

@Entity
public abstract class CondicionSumatoria extends Condicion {

	@ManyToOne
	protected RegistroIndicador indicador;
	protected MenorOMayor criterio;

	public CondicionSumatoria() {
	}

	public CondicionSumatoria(MenorOMayor criterio,
			RegistroIndicador indicador, int ultimosAnios) {
		this.criterio = criterio;
		this.indicador = indicador;
		this.ultimosAnios = ultimosAnios;
	}

	public enum MenorOMayor {
		mayorA, menorA;
	}

	public BigDecimal sumador(Empresa empresa) throws ParseException {
		List<Periodo> periodos = this.periodosDesdexAnio(empresa);
		BigDecimal acumulador = BigDecimal.ZERO;

		for (int i = 0; i < periodos.size(); i++) {
			acumulador = acumulador.add(this.aplicarIndicador(empresa.getUser()
					.getUserId(), indicador, empresa.getNombre(),
					periodos.get(i).getAnio(), periodos.get(i).getSemestre()));
		}
		return acumulador;
	}

}
