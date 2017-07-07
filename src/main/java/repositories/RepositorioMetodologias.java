package repositories;

import java.util.List;
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

	@Override
	public Metodologia createExample() {
		return new Metodologia();
	}

	@Override
	protected Predicate getCriterio(Metodologia example) {
		return null;
	}

}
