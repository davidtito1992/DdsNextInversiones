package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.RegistroIndicador;
import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;
import org.uqbar.commons.utils.Observable;

@Observable
public class RepositorioIndicadores extends
		CollectionBasedRepo<RegistroIndicador> {

	/********* ATRIBUTOS *********/

	private static RepositorioIndicadores instance = new RepositorioIndicadores();

	/********* METODOS *********/

	public void cargarListaIndicadores(
			List<RegistroIndicador> registrosIndicadores) {

		registrosIndicadores.forEach(registroIndicador -> this
				.create(registroIndicador));

	}

	public static RepositorioIndicadores repositorioMaestro() {
		return instance;
	}

	@Override
	public Class<RegistroIndicador> getEntityType() {
		return RegistroIndicador.class;
	}

	@Override
	public RegistroIndicador createExample() {
		return new RegistroIndicador("", "");
	}

	@Override
	protected Predicate getCriterio(RegistroIndicador example) {
		return null;
	}

	public ArrayList<String> todosLosNombresDeIndicadores(
			List<RegistroIndicador> listaIndicadores) {

		ArrayList<String> nombresDeTodosLosIndicadores = listaIndicadores
				.stream().map(indicador -> indicador.getNombre()).distinct()
				.sorted().collect(Collectors.toCollection(ArrayList::new));
		return nombresDeTodosLosIndicadores;
	}

	public boolean esIndicador(String componente) {
		return this
				.allInstances()
				.stream()
				.map(regIndicador -> regIndicador.getNombre())
				.anyMatch(
						NombreregIndicador -> NombreregIndicador
								.equalsIgnoreCase(componente));
	}

	public RegistroIndicador getRegistroIndicador(String nombreIndicador) {
		List<RegistroIndicador> registroIndicadores = this
				.allInstances()
				.stream()
				.filter(registroIndicador -> registroIndicador.getNombre()
						.equalsIgnoreCase(nombreIndicador))
				.collect(Collectors.toList());
		return registroIndicadores.get(0);
	}
}
