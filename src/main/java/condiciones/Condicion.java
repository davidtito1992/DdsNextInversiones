package condiciones;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import app.DslIndicador;
import model.Empresa;
import model.Periodo;
import model.PeriodoComparator;
import model.RegistroIndicador;
import parserIndicador.ParseException;

public abstract class Condicion {

	protected int ultimosAnios;
	protected tipoCondicion tipoCondicion;

	public enum tipoCondicion {
		CUANTITATIVA, TAXATIVA;
	}

	public double calcularCuantitativa(Empresa empresa) throws ParseException {
		return 0;
	}

	public boolean calcularTaxativa(Empresa empresa) throws ParseException {
		return false;
	}

	protected List<Periodo> periodosDesdexAnio(Empresa empresa) {
		Year anioLimite = Year.of(LocalDate.now().getYear() - ultimosAnios);

		return empresa.getPeriodos().stream()
				.filter(periodo -> periodo.getAnio().isAfter(anioLimite))
				.sorted(new PeriodoComparator()).collect(Collectors.toList());

	}

	protected BigDecimal aplicarIndicador(RegistroIndicador indicador,
			String nombreEmpresa, Year anio, int semestre)
			throws ParseException {
		return new DslIndicador().prepararFormula(indicador, nombreEmpresa,
				anio, semestre).calcular();
	}
}
