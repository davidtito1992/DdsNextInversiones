package main.repositories;

import java.util.List;

import model.RegistroIndicador;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.uqbar.commons.utils.Observable;

@Observable
public class RepositorioIndicador extends Repository<RegistroIndicador> {

	private static RepositorioIndicador repositorioIndicador;

	private RepositorioIndicador() {
		super(RegistroIndicador.class);
	}

	public static RepositorioIndicador getSingletonInstance() {
		if (repositorioIndicador == null) {
			repositorioIndicador = new RepositorioIndicador();
		}
		return repositorioIndicador;
	}

	/********* METODOS *********/

	@SuppressWarnings("unchecked")
	public List<String> todosLosNombresDeIndicadores(List<RegistroIndicador> listaIndicadores) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(RegistroIndicador.class)
				.setProjection(Projections.property("nombre"));
		return (List<String>) criteria.list();
	}

	public boolean esIndicador(String nombre) {
		if (this.getRegistroIndicador(nombre) == null) {
			return false;
		}
		return true;
	}

	public RegistroIndicador getRegistroIndicador(String nombreIndicador) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(RegistroIndicador.class)
				.add(Restrictions.eq("nombre", nombreIndicador));
		return (RegistroIndicador) criteria.uniqueResult();
	}

}
