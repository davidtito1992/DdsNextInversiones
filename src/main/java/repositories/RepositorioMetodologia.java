package repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.Metodologia;

@Observable
public class RepositorioMetodologia extends Repository<Metodologia> {

	private RepositorioMetodologia() {
		super(Metodologia.class);
	}
	
	private static RepositorioMetodologia repositorioMetodologia = null;
	
	public static RepositorioMetodologia getSingletonInstance() {

		if (repositorioMetodologia == null) {
			repositorioMetodologia = new RepositorioMetodologia();
		}

		return repositorioMetodologia;
	}

	/********* METODOS *********/

//	@SuppressWarnings("unchecked")
//	@Transactional
//	public List<Metodologia> allInstances() {
//		return entityManager.unwrap(Session.class)
//				.createCriteria(Metodologia.class).list();
//	}

}
