package repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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

	// LO LLAMA EL INICIALIZADOR ??
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

}
