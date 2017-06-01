package model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

import app.AppData;
import repositories.RepositorioEmpresa;
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

	public double analizarResultado(List<Cuenta> cuentasUnaEmpresa) throws Exception {
		String formulaSinIndicadores = transformIndicadores(getFormula());
		String formulaACalcular = transformValores(formulaSinIndicadores,
				cuentasUnaEmpresa);

		double resultado = 0;
		Calculator calculator = new Calculator(new StringReader(
				formulaACalcular));
		try {
			resultado = calculator.calculate();

		} catch (ParseException e) {
			throw new Exception("Este indicador utiliza una cuenta que no esta disponible en este periodo");
			// e.printStackTrace();

		}
		return resultado;

	}

	public String transformIndicadores(String formulaConIndicadores) {
		String devolverEsto = formulaConIndicadores;
		if (contieneIndicadores(formulaConIndicadores)) {
			String[] componentes = formulaConIndicadores.split(" ");

			for (int i = 0; i < componentes.length; i++) {

				if (esIndicador(componentes[i])) {
					componentes[i] = "( "
							+ transformIndicadores(getIndicador(componentes[i])
									.getFormula()) + " )";
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
			if (esCuenta(componentes[i], cuentasUnaEmpresa)) {
				componentes[i] = String.valueOf(getValorCuenta(componentes[i],
						cuentasUnaEmpresa));
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

	public boolean esCuenta(String componente, List<Cuenta> cuentasUnaEmpresa) {
		return cuentasUnaEmpresa.stream().map(cuenta -> cuenta.getNombre())
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

	/********* METODOS TEST *********/

	public double analizarResultadoTest(List<Cuenta> cuentasUnaEmpresa,
			List<Indicador> repoIndicadores) {
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
			e.printStackTrace();
		}
		return resultado;
	}

	public String transformIndicadoresTest(String formulaConIndicadores,
			List<Indicador> repoIndicadores) {
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
			List<Indicador> repoIndicadores) {
		List<Indicador> indicadoresConEseNombre = repoIndicadores.stream()
				.filter(indicador -> indicador.getNombre().equals(nombre))
				.collect(Collectors.toList());
		if (indicadoresConEseNombre.isEmpty()) {
			return null;
		}
		return indicadoresConEseNombre.get(0);
	}

	public boolean esIndicadorTest(String nombre,
			List<Indicador> repoIndicadores) {
		List<Indicador> indicadoresConEseNombre = repoIndicadores.stream()
				.filter(indicador -> indicador.getNombre().equals(nombre))
				.collect(Collectors.toList());
		if (indicadoresConEseNombre.isEmpty()) {
			return false;
		}
		return true;

	}

	public boolean contieneIndicadoresTest(String formula,
			List<Indicador> repoIndicadores) {
		boolean flag = false;
		String[] componentes = formula.split(" ");
		for (int i = 0; i < componentes.length; i++) {
			if (esIndicadorTest(componentes[i], repoIndicadores)) {
				flag = true;
			}
		}
		return flag;
	}
	
	public void guardarIndicador() throws Exception {

		if (this.nombre == null || this.formula == null) {
			throw new Exception(
					"Debe ingresar nombre y formula para guardar correctamente. Intentelo nuevamente");
		}
		
		if (esNumero(this.formula.replace(" ", ""))){
			throw new Exception(
					"La formula no puede estar compuesta por numeros unicamente");
		}

		if (this.nombre.contains(" ")) {
			throw new Exception(
					"Favor de ingresar un nombre que no contenga espacios");
		}

		if (formulaContieneNombre(this.nombre, this.formula)) {
			throw new Exception(
					"No puede usar ese nombre porque se encuentra dentro de la formula del mismo");
		}

		if (this.getRepoIndicadores().filtrar(nombre).size() > 0) {
			throw new Exception(
					"Un indicador con ese nombre ya se encuentra cargado en el sistema, Intentelo nuevamente");		
		} 
		
		List<Indicador> list = new ArrayList<Indicador>();
		Indicador nuevoIndicador = new Indicador(nombre,formula);
		revisarSintaxisYSemantica(nuevoIndicador);
		
		new AppData().guardarIndicador(nuevoIndicador) ;
		
		list.add(nuevoIndicador);
		this.getRepoIndicadores().cargarListaIndicadores(list);
	}
	
	public boolean formulaContieneNombre(String nombre, String formula){
		String[] componentes = formula.split(" ");
		for (int i = 0; i < componentes.length; i++) {
				if (nombre == componentes[i]){
					return true;
			}
		}
		return false;
	}
	
	public List<Cuenta> todasLasCuentas(){
		List<Empresa> empresas = this.getRepoEmpresas().filtrar(null, null,null, null);
		HashSet<Cuenta> cuentas = new HashSet<Cuenta>();
		empresas.forEach(empresa -> {
			empresa.getPeriodos().forEach(
					periodo -> {
						periodo.getCuentas()
								.forEach(
										cuenta -> cuentas.add(cuenta));
					});
		});

		return new ArrayList<Cuenta>(cuentas);
	}
	
	public void revisarSintaxisYSemantica(Indicador indicador) throws Exception{
			
		boolean cuentaOIndicador= false;
		String[] componentes = indicador.getFormula().split(" ");
		for (int i = 0; i < componentes.length; i++) {
			if (indicador.esIndicador(componentes[i]) || indicador.esCuenta(componentes[i], todasLasCuentas())) {
				componentes[i] = "2";
				cuentaOIndicador = true;
			}
		}
		if (cuentaOIndicador == false){
			throw new Exception("Debe ingresar una formula que contenga al menos una cuenta o un indicador existente.");
		}
		String formulaReemplazada = String.join(" ", componentes);
		Calculator calculator = new Calculator(new StringReader(
				formulaReemplazada));
		try {
			calculator.calculate();
		} catch (ParseException e) {
			throw new Exception("La sintaxis es incorrecta");
		} catch (Error e){
			throw new Exception("Ingresó una cuenta o un indicador inexistente");
		}
		
	}
	
	public boolean esNumero(String num){
			try{
			Double.parseDouble(num);
			return true;
			}catch (NumberFormatException e){
				return false;
			}	
	}
}
