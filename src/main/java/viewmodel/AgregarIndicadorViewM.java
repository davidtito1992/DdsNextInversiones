package viewmodel;

import java.util.List;

import model.Empresa;
import model.RegistroIndicador;

import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;

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

		this.agregarCuenta = this.getRepoEmpresas().todosLosNombresDeCuentas(this.getRepoEmpresas().allInstances());

	}

	private void cargarIndicadoresDisponibles() {

		this.agregarIndicador = this.getRepoIndicadores().todosLosNombresDeIndicadores(this.getRepoIndicadores().allInstances());

	}

	public void guardarIndicador() throws Throwable {
		
		//validar que el string formula no contenga el caracter ';' 
		
		if (this.nombre == null || this.formula == null) {
			throw new Exception(
					"Debe ingresar nombre y formula para guardar correctamente. Intentelo nuevamente");
		}
		RegistroIndicador preIndicadorAGuardar = new RegistroIndicador(this.nombre, this.formula);
	//	AppData valida sintaxis y semantica...si esta todo guarda en el archivo y repo
		new AppData().guardarIndicador(preIndicadorAGuardar);
	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(RegistroIndicador.class);
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
