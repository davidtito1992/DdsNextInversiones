package viewmodel;

import java.util.ArrayList;
import java.util.List;

import model.Indicador;

import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;

import repositories.RepositorioIndicadores;

@Observable
public class AgregarIndicadorViewM {

	/********* ATRIBUTOS *********/

	private String formula;
	private String nombre;

	/********* GETTERS/SETTERS *********/

	public String getFormula() {
		return formula;
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

	}

	public void guardarIndicador() throws Exception {
		// if (this.getNombre().isEmpty() || this.getFormula().isEmpty()){
		// throw new Exception("No puede dejar campos vacios");
		// }
		if (this.nombre==null || this.formula==null){ 
			throw new Exception("Debe ingresar los campos obligatorios para guardar correctamente, Intentelo nuevamente");
			}
		
		if (this.getRepoIndicadores().filtrar(nombre).size() == 0) {
			List<Indicador> list = new ArrayList<Indicador>();
			list.add(new Indicador(nombre, formula));
			this.getRepoIndicadores().cargarListaIndicadores(list);
			// Resta guardarlo en el archivo y llenar las tablas del ABM
			// indicadores
		} else {
			throw new Exception(
					"Un indicador con ese nombre ya se encuentra cargado en el sistema, Intentelo nuevamente");
		}
	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(Indicador.class);
	}

}
