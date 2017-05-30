package model;

import java.util.List;
import java.util.stream.Collectors;

import javaccCalculator.ArithmeticParser;
import javaccCalculator.ParseException;
import model.Indicador;

import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;

import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import calculator.Value;

@Observable
public class SnapshotIndicador {

	/********* ATRIBUTOS *********/

	private String nombre;
	private int año;
	private int semestre;
	private Value resultado;

	/********* GETTERS/SETTERS *********/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public Value getResultado() {
		return resultado;
	}

	public void setResultado(Value resultado) {
		this.resultado = resultado;
	}

	/********* METODOS *********/

	public Value analizarResultado(Indicador elIndicador,
			List<Cuenta> cuentasUnaEmpresa) {

		String formulaSinIndicadores = transformIndicadores(elIndicador
				.getFormula());
		String formulaACalcular = transformValores(formulaSinIndicadores,
				cuentasUnaEmpresa);

		ArithmeticParser parser = new ArithmeticParser(formulaACalcular);
		try {
			parser.parse();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parser.evaluate();

	}

	public String transformIndicadores(String formulaConIndicadores) {
		String devolverEsto = formulaConIndicadores;
		if (contieneIndicadores(formulaConIndicadores)) {
			String[] componentes = formulaConIndicadores.split(" ");

			for (int i = 1; i <= componentes.length; i++) {

				if (esIndicador(componentes[i])) {
					componentes[i] = transformIndicadores(getIndicador(
							componentes[i]).getFormula());
				}

			}
			devolverEsto = String.join(" ", componentes);
		}
		return devolverEsto;
	}

	public String transformValores(String formulaConCuentas,
			List<Cuenta> cuentasUnaEmpresa) {
		String[] componentes = formulaConCuentas.split(" ");
		for (int i = 1; i <= componentes.length; i++) {
			if (esCuenta(componentes[i])) {
//				componentes[i] = getValorCuenta(componentes[i],
//						cuentasUnaEmpresa); CASTEAR A STRING
			}
		}
		return String.join(" ", componentes);
	}

	private int getValorCuenta(String string, List<Cuenta> cuentasUnaEmpresa) {
		List<Cuenta> cuentaADevolver = cuentasUnaEmpresa.stream()
				.filter(cuenta -> cuenta.getNombre().equals(nombre))
				.collect(Collectors.toList());
		return cuentaADevolver.get(0).getValor();
	}

	private boolean esCuenta(String string) {
		// TODO Auto-generated method stub
		return false;
	}

	public Indicador getIndicador(String nombre) {
		List<Indicador> indicadoresConEseNombre = getRepoIndicadores().filtrar(
				nombre);
		if (indicadoresConEseNombre.isEmpty()) {
			return null;
		}
		return indicadoresConEseNombre.get(0);
	}

	public boolean esIndicador(String nombre) {

		List<Indicador> indicadoresConEseNombre = getRepoIndicadores().filtrar(
				nombre);
		if (indicadoresConEseNombre.isEmpty()) {
			return false;
		}
		return true;

	}

	public boolean contieneIndicadores(String formula) {
		boolean flag = false;
		String[] componentes = formula.split(" ");
		for (int i = 1; i <= componentes.length; i++) {
			if (esIndicador(componentes[i])) {
				flag = true;
			}
		}
		return flag;
	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(Indicador.class);
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}

}
