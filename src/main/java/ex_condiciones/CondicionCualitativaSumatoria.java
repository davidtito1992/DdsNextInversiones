package ex_condiciones;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import app.DslIndicador;
import model.Empresa;
import model.Periodo;
import model.RegistroIndicador;
import parserIndicador.ParseException;

public class CondicionCualitativaSumatoria implements CondicionCualitativa {
	private RegistroIndicador indicador;
	private int ultimosAnios;
	private double peso;

	public CondicionCualitativaSumatoria(RegistroIndicador indicador,
			int ultimosAnios,double peso) {
		this.indicador = indicador;
		this.ultimosAnios = ultimosAnios;
		this.peso = peso;
	}

	public double calcular(Empresa empresa) throws ParseException {
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

		return acumulador.doubleValue() * peso;
	}

	private BigDecimal aplicarIndicador(RegistroIndicador indicador,
			String nombreEmpresa, Year anio, int semestre)
			throws ParseException {
		return new DslIndicador().prepararFormula(indicador, nombreEmpresa,
				anio, semestre).calcular();
	}

}
