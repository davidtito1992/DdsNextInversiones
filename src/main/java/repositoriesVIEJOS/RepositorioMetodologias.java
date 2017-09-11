package repositoriesVIEJOS;

import java.util.List;
import java.util.stream.Collectors;
import model.Metodologia;
import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;
import org.uqbar.commons.utils.Observable;

@Observable
public class RepositorioMetodologias extends CollectionBasedRepo<Metodologia> {

	/********* ATRIBUTOS *********/

	private static RepositorioMetodologias instance = new RepositorioMetodologias();

	/********* METODOS *********/

	public void cargarListaMetodologias(List<Metodologia> registrosMetodologias) {

		registrosMetodologias.forEach(registroMetodologia -> this
				.create(registroMetodologia));

	}

	public static RepositorioMetodologias repositorioMaestro() {
		return instance;
	}

	@Override
	public Class<Metodologia> getEntityType() {
		return Metodologia.class;
	}

	// ESTO NO SE SI ESTA BIEN
	// @Override
	// public Metodologia createExample() {
	// List<CondicionTaxativa> lista = new ArrayList<CondicionTaxativa>();
	// List<CondicionCualitativa> lista2 = new
	// ArrayList<CondicionCualitativa>();
	// return new Metodologia("", lista, lista2);
	// }
	//
	@Override
	protected Predicate getCriterio(Metodologia example) {
		return null;
	}

	@Override
	public Metodologia createExample() {
		// TODO Auto-generated method stub
		return null;
	}

	public Metodologia getMetodologia(String nombreMetodologia) {
		List<Metodologia> metodologias = this
				.allInstances()
				.stream()
				.filter(metodologia -> metodologia.getNombre()
						.equalsIgnoreCase(nombreMetodologia))
				.collect(Collectors.toList());
		return metodologias.get(0);
	}
}