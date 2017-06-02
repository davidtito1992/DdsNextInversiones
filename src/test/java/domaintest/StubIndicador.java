package domaintest;

import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

import calculator.Calculator;
import calculator.ParseException;
import model.Cuenta;
import model.Indicador;

public class StubIndicador extends Indicador{
	
	public StubIndicador(String nombre, String formula){
		super(nombre,formula);
	}
	
	public double analizarResultadoTest(List<Cuenta> cuentasUnaEmpresa,
			List<StubIndicador> repoIndicadores) throws ParseException {
		String formulaSinIndicadores = transformIndicadoresTest(getFormula(),
				repoIndicadores);
		String formulaACalcular = transformValores(formulaSinIndicadores,
				cuentasUnaEmpresa);
		double resultado = 0.0;
		Calculator calculator = new Calculator(new StringReader(
				formulaACalcular));
		try {
			resultado = calculator.calculate();

		} catch (ParseException e) {
			throw new ParseException(
					"Este indicador utiliza una cuenta que no esta disponible en este periodo");
		}
		return resultado;
	}

	public String transformIndicadoresTest(String formulaConIndicadores,
			List<StubIndicador> repoIndicadores) {
		String devolverEsto = formulaConIndicadores;
		if (contieneIndicadoresTest(formulaConIndicadores, repoIndicadores)) {
			String[] componentes = formulaConIndicadores.split(" ");

			for (int i = 0; i < componentes.length; i++) {

				if (esIndicadorTest(componentes[i], repoIndicadores)) {
					componentes[i] = "( "
							+ transformIndicadoresTest(
									getIndicadorTest(componentes[i],
											repoIndicadores).getFormula(),
									repoIndicadores) + " )";
				}

			}
			devolverEsto = String.join(" ", componentes);
		}
		return devolverEsto;
	}

	public Indicador getIndicadorTest(String nombre,
			List<StubIndicador> repoIndicadores) {
		List<StubIndicador> indicadoresConEseNombre = repoIndicadores.stream()
				.filter(indicador -> indicador.getNombre().equals(nombre))
				.collect(Collectors.toList());
		if (indicadoresConEseNombre.isEmpty()) {
			return null;
		}
		return indicadoresConEseNombre.get(0);
	}

	public boolean esIndicadorTest(String nombre,
			List<StubIndicador> repoIndicadores) {
		List<StubIndicador> indicadoresConEseNombre = repoIndicadores.stream()
				.filter(indicador -> indicador.getNombre().equals(nombre))
				.collect(Collectors.toList());
		if (indicadoresConEseNombre.isEmpty()) {
			return false;
		}
		return true;

	}

	public boolean contieneIndicadoresTest(String formula,
			List<StubIndicador> repoIndicadores) {
		boolean flag = false;
		String[] componentes = formula.split(" ");
		for (int i = 0; i < componentes.length; i++) {
			if (esIndicadorTest(componentes[i], repoIndicadores)) {
				flag = true;
			}
		}
		return flag;
	}

}
