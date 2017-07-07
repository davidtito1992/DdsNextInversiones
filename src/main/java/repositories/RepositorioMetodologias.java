package repositories;

import java.util.ArrayList;
import java.util.List;

import model.Metodologia;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;
import org.uqbar.commons.utils.Observable;

import condiciones.CondicionCualitativa;
import condiciones.CondicionTaxativa;

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
	@Override
	public Metodologia createExample() {
		List<CondicionTaxativa> lista = new ArrayList<CondicionTaxativa>();
		List<CondicionCualitativa> lista2 = new ArrayList<CondicionCualitativa>();
		return new Metodologia("", lista, lista2);
	}

	@Override
	protected Predicate getCriterio(Metodologia example) {
		return null;
	}

}
