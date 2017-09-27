package repositories;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.uqbar.commons.utils.Observable;
import model.Metodologia;

@SuppressWarnings("rawtypes")
@Observable
public class RepositorioMetodologia extends Repository {

	private static RepositorioMetodologia repositorioMetodologia = null;

	public static RepositorioMetodologia getSingletonInstance() {

		if (repositorioMetodologia == null) {
			repositorioMetodologia = new RepositorioMetodologia();
		}

		return repositorioMetodologia;
	}

	private RepositorioMetodologia() {

	}

	/********* METODOS *********/

	@SuppressWarnings("unchecked")
	@Transactional
	public Metodologia buscar(long id) {
		return (Metodologia) findById(Metodologia.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Metodologia> allInstances() {
		return entityManager.unwrap(Session.class)
				.createCriteria(Metodologia.class).list();
	}

//	public void cargarListaMetodologias(List<Metodologia> registrosMetodologias) {
//		registrosMetodologias.stream().forEach(
//				metodologia -> agregar(metodologia));
//	}

//	@Transactional
//	public void agregarMetodologia(Metodologia metodologia) {
//		EntityManagerHelper.beginTransaction();
//		entityManager.merge(metodologia);
//		EntityManagerHelper.commit();
//	}
//
//	@Transactional
//	public void eliminarMetodologia(long id) {
//		EntityManagerHelper.beginTransaction();
//		entityManager.remove(this.buscar(id));
//		EntityManagerHelper.commit();
//	}

}
