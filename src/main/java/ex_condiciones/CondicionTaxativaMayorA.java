package ex_condiciones;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import parserIndicador.ParseException;
import app.DslIndicador;
import model.Empresa;
import model.Periodo;
import model.RegistroIndicador;

public class CondicionTaxativaMayorA implements CondicionTaxativa {
	private RegistroIndicador indicador;
	private BigDecimal numeroAComparar;
	private int ultimosAnios;

	public CondicionTaxativaMayorA(RegistroIndicador indicador,
			BigDecimal numeroAComparar, int ultimosAnios) {
		this.indicador = indicador;
		this.numeroAComparar = numeroAComparar;
		this.ultimosAnios = ultimosAnios;
	}

	public boolean calcular(Empresa empresa) throws ParseException {
		BigDecimal acumulador = BigDecimal.ZERO;
		List<Periodo> periodos = empresa
				.getPeriodos()
				.stream()
				.filter(periodo -> periodo.getAnio().getValue() > 2017 - ultimosAnios)
				.collect(Collectors.toList());
		for (int i = 0; i < periodos.size(); i++) {
			acumulador = acumulador.add(aplicarIndicador(indicador, empresa
					.getNombre(), periodos.get(i).getAnio(), periodos.get(i)
					.getSemestre()));
		}

		return acumulador.compareTo(numeroAComparar) > 0;
	}

	private BigDecimal aplicarIndicador(RegistroIndicador indicador,
			String nombreEmpresa, Year anio, int semestre)
			throws ParseException {
		return new DslIndicador().prepararFormula(indicador, nombreEmpresa,
				anio, semestre).calcular();
	}
}
