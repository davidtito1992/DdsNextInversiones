package model;

import java.util.List;
import java.util.stream.Collectors;

import javaccCalculator.ArithmeticParser;
import javaccCalculator.ParseException;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import calculator.Value;

@SuppressWarnings("serial") 
@Transactional
@Observable
public class Indicador extends Entity{

	/********* ATRIBUTOS *********/

	private String nombre;
	private String formula;
	
	public Indicador(String nombre, String formula){
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
	
	public Value analizarResultado(List<Cuenta> cuentasUnaEmpresa) {

		String formulaSinIndicadores = transformIndicadores(getFormula());
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

			for (int i = 0; i < componentes.length; i++) {

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
		for (int i = 0; i < componentes.length; i++) {
			if (esCuenta(componentes[i],cuentasUnaEmpresa)) {				
				componentes[i] = String.valueOf(getValorCuenta(componentes[i], cuentasUnaEmpresa)); 
			}
		}
		return String.join(" ", componentes);
	}

	private int getValorCuenta(String nombre, List<Cuenta> cuentasUnaEmpresa) {
		List<Cuenta> cuentaADevolver = cuentasUnaEmpresa.stream()
				.filter(cuenta -> cuenta.getNombre().equals(nombre))
				.collect(Collectors.toList());
		return cuentaADevolver.get(0).getValor();
	}

	private boolean esCuenta(String componente, List<Cuenta> cuentasUnaEmpresa) {
		return cuentasUnaEmpresa.stream()
				.map(cuenta -> cuenta.getNombre())
				.anyMatch(cuenta -> cuenta.equals(componente));
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
		return false;
		/*List<Indicador> indicadoresConEseNombre = getRepoIndicadores().filtrar(
				nombre);
		if (indicadoresConEseNombre.isEmpty()) {
			return false;
		}
		return true;*/

	}

	public boolean contieneIndicadores(String formula) {
		boolean flag = false;
		String[] componentes = formula.split(" ");
		for (int i = 0; i < componentes.length; i++) {
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
