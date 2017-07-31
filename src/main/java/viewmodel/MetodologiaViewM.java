package viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;
import repositories.RepositorioMetodologias;
import model.Metodologia;


@Observable
public class MetodologiaViewM {

	/********* ATRIBUTOS *********/

	private List<String> nombres = new ArrayList<String>();
	private List<Metodologia> metodologias;
	private Metodologia metodologiaSeleccionada;

	/********* GETTERS/SETTERS *********/

	public List<String> getNombres() {
		return nombres;
	}

	public void setNombres(List<String> nombres) {
		this.nombres = nombres;
	}

	public List<Metodologia> getMetodologias() {
		return metodologias;
	}

	public void setMetodologias(List<Metodologia> metodologias) {
		this.metodologias = metodologias;
	}

	public Metodologia getMetodologiaSeleccionada() {
		return metodologiaSeleccionada;
	}

	public void setMetodologiaSeleccionada(
			Metodologia metodologiaSeleccionada) {
		this.metodologiaSeleccionada = metodologiaSeleccionada;
	}

	/********* METODOS *********/

	public MetodologiaViewM() {
		
		this.llenarTablas();
	}

	public void llenarTablas() {
		this.setMetodologias(null);
		this.setMetodologias(getRepoMetodologias().allInstances());
	}

	public RepositorioMetodologias getRepoMetodologias() {
		return ApplicationContext.getInstance().getSingleton(Metodologia.class);
	}

}
