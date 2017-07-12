package condiciones;

import java.math.BigDecimal;
import java.util.List;

import parserIndicador.ParseException;
import model.Empresa;
import model.Periodo;
import model.RegistroIndicador;

public class CondicionSumatoria extends CondicionAntiguedad {

	private RegistroIndicador indicador;

	public CondicionSumatoria(RegistroIndicador indicador, int ultimosAnios,
			double peso) {
		super(peso);
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
	public double calcularCuantitativa(Empresa empresa) throws ParseException {

		return sumador(empresa).doubleValue() * peso;

	}

}
