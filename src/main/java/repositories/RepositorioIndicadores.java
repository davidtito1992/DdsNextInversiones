package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Indicador;
import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;
import org.uqbar.commons.utils.Observable;

@Observable
public class RepositorioIndicadores extends CollectionBasedRepo<Indicador> {

	/********* ATRIBUTOS *********/

	private static RepositorioIndicadores instance = new RepositorioIndicadores();

	/********* METODOS *********/

	public void cargarListaIndicadores(List<Indicador> Indicadores) {
		for (int i = 0; i < Indicadores.size(); i++) {
			create(Indicadores.get(i));
		}
	}

	public List<Indicador> filtrar(String nombreSeleccionado) {
		return this
				.allInstances()
				.stream()
				.filter(indicador -> filtroNombre(nombreSeleccionado, indicador))
				.collect(Collectors.toList());
	}

	public boolean filtroNombre(String nombreSeleccionado, Indicador indicador) {
		if (nombreSeleccionado == null) {
			return true;
		} else {
			return indicador.getNombre().equals(nombreSeleccionado);
		}
	}

	public static RepositorioIndicadores repositorioMaestro() {
		return instance;
	}

	@Override
	public Class<Indicador> getEntityType() {
		return Indicador.class;
	}

	@Override
	public Indicador createExample() {
		return new Indicador("", "");
	}

	@Override
	protected Predicate getCriterio(Indicador example) {
		return null;
	}

	public ArrayList<String> todosLosNombresDeIndicadores(
			List<Indicador> listaIndicadores) {

		ArrayList<String> nombresDeTodosLosIndicadores = listaIndicadores
				.stream().map(indicador -> indicador.getNombre()).distinct()
				.sorted().collect(Collectors.toCollection(ArrayList::new));
		return nombresDeTodosLosIndicadores;
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

	public Indicador getIndicador(String nombre) {
		List<Indicador> indicadoresConEseNombre = this.filtrar(nombre);
		if (indicadoresConEseNombre.isEmpty()) {
			return null;
		}
		return indicadoresConEseNombre.get(0);
	}

	public boolean esIndicador(String nombre) {
		List<Indicador> indicadoresConEseNombre = this.filtrar(nombre);
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

	public boolean indicadorYaExistente(String nombre) {

		return allInstances().stream().anyMatch(
				indicador -> indicador.getNombre().equals(nombre));

	}

}
