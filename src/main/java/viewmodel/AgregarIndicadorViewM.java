package viewmodel;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import model.Cuenta;
import model.Empresa;
import model.Indicador;

import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;

import calculator.Calculator;
import calculator.ParseException;
import app.AppData;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;

@Observable
public class AgregarIndicadorViewM {

	/********* ATRIBUTOS *********/

	private String formula;
	private String nombre;
	private List<String> agregarIndicador;
	private String agregarIndicadorSeleccionado;
	private List<String> agregarCuenta;
	private String agregarCuentaSeleccionado;

	/********* GETTERS/SETTERS *********/

	public String getFormula() {
		return formula;
	}

	public List<String> getAgregarIndicador() {
		return agregarIndicador;
	}

	public void setAgregarIndicador(List<String> agregarIndicador) {
		this.agregarIndicador = agregarIndicador;
	}

	public String getAgregarIndicadorSeleccionado() {
		return agregarIndicadorSeleccionado;
	}

	public void setAgregarIndicadorSeleccionado(
			String agregarIndicadorSeleccionado) {
		this.agregarIndicadorSeleccionado = agregarIndicadorSeleccionado;
	}

	public List<String> getAgregarCuenta() {
		return agregarCuenta;
	}

	public void setAgregarCuenta(List<String> agregarCuenta) {
		this.agregarCuenta = agregarCuenta;
	}

	public String getAgregarCuentaSeleccionado() {
		return agregarCuentaSeleccionado;
	}

	public void setAgregarCuentaSeleccionado(String agregarCuentaSeleccionado) {
		this.agregarCuentaSeleccionado = agregarCuentaSeleccionado;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/********* METODOS *********/

	public AgregarIndicadorViewM() {
		this.cargarIndicadoresDisponibles();
		this.cargarCuentasDisponibles();
	}

	private void cargarCuentasDisponibles() {

		List<Empresa> empresas = getRepoEmpresas().allInstances();
		HashSet<String> nombreCuentas = new HashSet<String>();
		empresas.forEach(empresa -> {
			empresa.getPeriodos().forEach(
					periodo -> {
						periodo.getCuentas()
								.forEach(
										cuenta -> nombreCuentas.add(cuenta
												.getNombre()));
					});
		});

		ArrayList<String> nombreCuentasfinal = new ArrayList<String>(
				nombreCuentas);
		Collections.sort(nombreCuentasfinal);

		this.agregarCuenta = nombreCuentasfinal;

	}

	private void cargarIndicadoresDisponibles() {

		List<Indicador> listaIndicadores = getRepoIndicadores().allInstances();
		HashSet<String> nombreIndicadores = new HashSet<String>();
		listaIndicadores.forEach(indicador -> (nombreIndicadores.add(indicador
				.getNombre())));
		ArrayList<String> nombreIndicadoresFinal = new ArrayList<String>(
				nombreIndicadores);
		Collections.sort(nombreIndicadoresFinal);
		this.agregarIndicador = nombreIndicadoresFinal;

	}

	public void guardarIndicador() throws Exception {
		Indicador ind = new Indicador(this.nombre, this.formula);
		ind.guardarIndicador();
	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(Indicador.class);
	}

	public void agregarCuentaALaFormula() {
		if (this.getFormula() != null) {
			this.setFormula(this.getFormula() + " "
					+ this.getAgregarCuentaSeleccionado());
		} else {
			this.setFormula(this.getAgregarCuentaSeleccionado());
		}
	}

	public void agregarIndicadorALaFormula() {
		if (this.getFormula() != null) {
			this.setFormula(this.getFormula() + " "
					+ this.getAgregarIndicadorSeleccionado());
		} else {
			this.setFormula(this.getAgregarIndicadorSeleccionado());
		}
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}

}
