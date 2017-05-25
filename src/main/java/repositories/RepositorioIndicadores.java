package repositories;

import java.util.List;
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

	public static RepositorioIndicadores repositorioMaestro() {
		return instance;
	}

	@Override
	public Class<Indicador> getEntityType() {
		// TODO Auto-generated method stub
		return Indicador.class;
	}

	@Override
	public Indicador createExample() {
		// TODO Auto-generated method stub
		return new Indicador();
	}

	@Override
	protected Predicate getCriterio(Indicador example) {
		// TODO Auto-generated method stub
		return null;
	}

}
