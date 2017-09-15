package viewmodel;

import java.util.List;

import model.RegistroIndicador;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicador;

import org.uqbar.commons.utils.Observable;

import app.AplicacionContexto;
import app.DslIndicador;

@Observable
public class AgregarIndicadorViewM {

	/********* ATRIBUTOS *********/

	private String formula = "";
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

	@SuppressWarnings("unchecked")
	private void cargarCuentasDisponibles() {

		this.agregarCuenta = this.getRepositorioEmpresas().todosLosNombresDeCuentas(
				this.getRepositorioEmpresas().allInstances());

	}
	
	@SuppressWarnings("unchecked")
	private void cargarIndicadoresDisponibles() {

		this.agregarIndicador = this.getRepositorioIndicadores()
				.todosLosNombresDeIndicadores(
						this.getRepositorioIndicadores().allInstances());

	}

	public void guardarIndicador() throws Exception {

		if (this.nombre == null || this.formula == "") {
			throw new RuntimeException(
					"Debe ingresar nombre y formula para guardar correctamente. Intentelo nuevamente");
		}

		new DslIndicador().añadirIndicador(new RegistroIndicador(this.nombre,
				this.formula));
	}
	
	public RepositorioIndicador getRepositorioIndicadores(){
		return AplicacionContexto.getInstance().getInstanceRepoIndicadores();
	}

	public void agregarCuentaALaFormula() {
		if (this.getAgregarCuentaSeleccionado() != null)
			this.setFormula(this.getFormula() + " "
					+ this.getAgregarCuentaSeleccionado());

	}

	public void agregarIndicadorALaFormula() {
		if (this.getAgregarIndicadorSeleccionado() != null)
			this.setFormula(this.getFormula() + " "
					+ this.getAgregarIndicadorSeleccionado());
	}

//	public RepositorioEmpresa getRepoEmpresas() {
//		return ApplicationContext.getInstance().getSingleton(Empresa.class);
//	}
	
	public RepositorioEmpresa getRepositorioEmpresas(){
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}

}
