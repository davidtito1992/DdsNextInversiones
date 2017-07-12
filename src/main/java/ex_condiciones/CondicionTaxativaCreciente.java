package ex_condiciones;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import app.DslIndicador;
import model.Empresa;
import model.Periodo;
import model.PeriodoComparator;
import model.RegistroIndicador;
import parserIndicador.ParseException;

public class CondicionTaxativaCreciente implements CondicionTaxativa {

	public RegistroIndicador indicador;
	public int ultimosAnios;

	public CondicionTaxativaCreciente(RegistroIndicador indicador,
			int ultimosAnios) {
		this.indicador = indicador;
		this.ultimosAnios = ultimosAnios;
	}

	public boolean calcular(Empresa empresa) throws ParseException {
		List<Periodo> periodos = empresa
				.getPeriodos()
				.stream()
				.filter(periodo -> periodo.getAnio().getValue() > 2017 - ultimosAnios)
				.sorted(new PeriodoComparator()).collect(Collectors.toList());
		for (int i = 0; i < periodos.size() - 1; i++) {
			if (aplicarIndicador(indicador, empresa.getNombre(),
					periodos.get(i).getAnio(), periodos.get(i).getSemestre())
					.doubleValue() > aplicarIndicador(indicador,
					empresa.getNombre(), periodos.get(i + 1).getAnio(),
					periodos.get(i + 1).getSemestre()).doubleValue()) {
				return false;
			}
		}
		return true;
	}

	private BigDecimal aplicarIndicador(RegistroIndicador indicador,
			String nombreEmpresa, Year anio, int semestre)
			throws ParseException {
		return new DslIndicador().prepararFormula(indicador, nombreEmpresa,
				anio, semestre).calcular();
	}
}
