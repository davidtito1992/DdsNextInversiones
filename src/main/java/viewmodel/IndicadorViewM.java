package viewmodel;

import java.util.ArrayList;
import java.util.List;
import model.Indicador;
import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;
import repositories.RepositorioIndicadores;

@Observable
public class IndicadorViewM {

	/********* ATRIBUTOS *********/

	private List<String> formulas = new ArrayList<String>();
	private List<String> nombres = new ArrayList<String>();
	private List<Indicador> indicadores;
	private Indicador indicadorSeleccionado;

	/********* GETTERS/SETTERS *********/

	public List<String> getformulas() {
		return formulas;
	}

	public void setformulas(List<String> formulas) {
		this.formulas = formulas;
	}

	public List<String> getNombres() {
		return nombres;
	}

	public void setNombres(List<String> nombres) {
		this.nombres = nombres;
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}

	/********* METODOS *********/

	public IndicadorViewM() {

	}

	public void llenarTablas() {
		
	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(Indicador.class);
	}

}
