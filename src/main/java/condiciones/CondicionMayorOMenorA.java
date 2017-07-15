package condiciones;

import java.math.BigDecimal;
import parserIndicador.ParseException;
import model.Empresa;
import model.RegistroIndicador;

public class CondicionMayorOMenorA extends CondicionSumatoria {

	public enum Criterio {
		mayorA, menorA;
	}

	private Criterio criterio;
	private BigDecimal numeroAComparar;

	public CondicionMayorOMenorA(Criterio criterio,
			RegistroIndicador indicador, int ultimosAnios,
			BigDecimal nroAComparar) {
		super(indicador, ultimosAnios, 0);
		this.criterio = criterio;
		this.numeroAComparar = nroAComparar;
	}

	@Override
	public boolean calcularTaxativa(Empresa empresa) throws ParseException {
		BigDecimal acumulador = sumador(empresa);

		if (criterio.equals(Criterio.mayorA)) {
			return acumulador.compareTo(numeroAComparar) > 0;
		} else {
			return acumulador.compareTo(numeroAComparar) < 0;
		}

	}
}
