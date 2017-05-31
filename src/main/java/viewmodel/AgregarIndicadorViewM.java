package viewmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import model.Empresa;
import model.Indicador;

import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;

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
		// if (this.getNombre().isEmpty() || this.getFormula().isEmpty()){
		// throw new Exception("No puede dejar campos vacios");
		// }
		if (this.nombre == null || this.formula == null) {
			throw new Exception(
					"Debe ingresar los campos obligatorios para guardar correctamente, Intentelo nuevamente");
		}

		if (this.getRepoIndicadores().filtrar(nombre).size() == 0) {
			List<Indicador> list = new ArrayList<Indicador>();
			list.add(new Indicador(nombre, formula));
			this.getRepoIndicadores().cargarListaIndicadores(list);
			// Resta guardarlo en el archivo
		} else {
			throw new Exception(
					"Un indicador con ese nombre ya se encuentra cargado en el sistema, Intentelo nuevamente");
		}
	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(Indicador.class);
	}

	public void agregarCuentaALaFormula() {
		this.setFormula(this.getFormula() + " "
				+ this.getAgregarCuentaSeleccionado());
	}

	public void agregarIndicadorALaFormula() {
		this.setFormula(this.getFormula() + " "
				+ this.getAgregarIndicadorSeleccionado());
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}

}
