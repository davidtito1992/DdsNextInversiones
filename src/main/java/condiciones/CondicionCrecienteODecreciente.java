package condiciones;

import java.math.BigDecimal;
import java.util.List;

import model.Empresa;
import model.Periodo;
import model.RegistroIndicador;
import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;

public class CondicionCrecienteODecreciente extends Condicion {

	public enum Criterio {
		CRECIENTE, DECRECIENTE;
	}

	private RegistroIndicador indicador;
	private Criterio criterio;

	public CondicionCrecienteODecreciente(Criterio criterio,
			RegistroIndicador indicador, int ultimosAnios) {
		this.indicador = indicador;
		this.criterio = criterio;
		this.ultimosAnios = ultimosAnios;
	}
	
	@Override
	public String toString(){
		return "CondicionCreciente";
	}

	@Override
	public RankingEmpresa calcular(RankingEmpresa rEmpresa) throws ParseException {
		Empresa empresa = rEmpresa.getEmpresa();
		List<Periodo> periodos = super.periodosDesdexAnio(empresa);

		for (int i = 0; i < periodos.size() - 1; i++) {

			BigDecimal indicadorActual = aplicarIndicador(indicador,
					empresa.getNombre(), periodos.get(i).getAnio(), periodos
							.get(i).getSemestre());
			BigDecimal indicadorSiguiente = aplicarIndicador(indicador,
					empresa.getNombre(), periodos.get(i + 1).getAnio(),
					periodos.get(i + 1).getSemestre());

			if (criterio.equals(Criterio.CRECIENTE)) {
				switch (indicadorActual.compareTo(indicadorSiguiente)) {
				case -1:

				default:
					throw new RuntimeException (this.toString());
				}
			} else if (criterio.equals(Criterio.DECRECIENTE)) {
				switch (indicadorActual.compareTo(indicadorSiguiente)) {
				case 1:

				default:
					throw new RuntimeException (this.toString());
				}
			}
		}
		return rEmpresa;
	}
}
