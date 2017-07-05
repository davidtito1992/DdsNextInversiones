package viewmodel;

import java.util.ArrayList;
import java.util.List;
import model.RegistroIndicador;
import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;
import repositories.RepositorioIndicadores;

@Observable
public class IndicadorViewM {

	/********* ATRIBUTOS *********/

	private List<String> formulas = new ArrayList<String>();
	private List<String> nombres = new ArrayList<String>();
	private List<RegistroIndicador> indicadores;
	private RegistroIndicador indicadorSeleccionado;

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

	public List<RegistroIndicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<RegistroIndicador> indicadores) {
		this.indicadores = indicadores;
	}

	public RegistroIndicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(RegistroIndicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}

	/********* METODOS *********/

	public IndicadorViewM() {
		this.llenarTablas();
	}

	public void llenarTablas() {
		this.setIndicadores(null);
		this.setIndicadores(getRepoIndicadores().allInstances());
	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(
				RegistroIndicador.class);
	}

}
