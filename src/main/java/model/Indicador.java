package model;

import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;
import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;
import repositories.RepositorioIndicadores;
import calculator.Calculator;
import calculator.ParseException;

@SuppressWarnings("serial")
@Transactional
@Observable
public class Indicador extends Entity {

	/********* ATRIBUTOS *********/

	private String nombre;
	private String formula;

	public Indicador(String nombre, String formula) {
		this.nombre = nombre;
		this.formula = formula;
	}

	/********* GETTERS/SETTERS *********/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	/********* METODOS *********/

	public double analizarResultado(List<Cuenta> cuentasUnaEmpresa)
			throws ParseException {
		
		String formulaSinIndicadores = getRepoIndicadores()
				.transformIndicadores(getFormula());
		
		String formulaACalcular = getRepoIndicadores().transformValores(
				formulaSinIndicadores, cuentasUnaEmpresa);
		
		double resultado = 0;
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

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(Indicador.class);
	}

}
