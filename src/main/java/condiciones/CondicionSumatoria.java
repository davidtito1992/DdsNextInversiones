package condiciones;

import java.math.BigDecimal;
import java.util.List;

import model.Empresa;
import model.Periodo;
import model.RegistroIndicador;
import parserIndicador.ParseException;

public abstract class CondicionSumatoria extends Condicion {
	
	public CondicionSumatoria(){
		
	}
	
	public enum MenorOMayor {
		mayorA, menorA;
	}

	protected MenorOMayor criterio;
	protected RegistroIndicador indicador;

	public CondicionSumatoria(MenorOMayor criterio, RegistroIndicador indicador,
			int ultimosAnios) {
		this.criterio = criterio;
		this.indicador = indicador;
		this.ultimosAnios = ultimosAnios;
	}

	public BigDecimal sumador(Empresa empresa) throws ParseException {
		List<Periodo> periodos = this.periodosDesdexAnio(empresa);
		BigDecimal acumulador = BigDecimal.ZERO;

		for (int i = 0; i < periodos.size(); i++) {
			acumulador = acumulador.add(this.aplicarIndicador(indicador,
					empresa.getNombre(), periodos.get(i).getAnio(), periodos
							.get(i).getSemestre()));
		}
		return acumulador;
	}

}
