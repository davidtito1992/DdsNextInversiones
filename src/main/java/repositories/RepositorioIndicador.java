package repositories;

import java.util.List;

import javax.transaction.Transactional;

import model.RegistroIndicador;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.uqbar.commons.utils.Observable;

import db.EntityManagerHelper;

@SuppressWarnings("rawtypes")
@Observable
public class RepositorioIndicador extends Repository {

	private static RepositorioIndicador repositorioIndicador;

	private RepositorioIndicador() {
		super();
	}

	public static RepositorioIndicador getSingletonInstance() {
		if (repositorioIndicador == null) {
			repositorioIndicador = new RepositorioIndicador();
		}
		return repositorioIndicador;
	}

	/********* METODOS *********/

	@Transactional
	public void agregarIndicador(RegistroIndicador registroIndicador) {
		EntityManagerHelper.beginTransaction();
		entityManager.merge(registroIndicador);
		EntityManagerHelper.commit();
	}

	@Transactional
	public void eliminarIndicador(long id) {
		EntityManagerHelper.beginTransaction();
		entityManager.remove(this.buscar(id));
		EntityManagerHelper.commit();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public RegistroIndicador buscar(long id) {
		return (RegistroIndicador) findById(RegistroIndicador.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<RegistroIndicador> allInstances() {
		return entityManager.unwrap(Session.class)
				.createCriteria(RegistroIndicador.class).list();
	}

	public void cargarListaIndicadores(
			List<RegistroIndicador> registrosIndicadores) {
		registrosIndicadores.stream().forEach(
				indicador -> agregarIndicador(indicador));
	}

	@SuppressWarnings("unchecked")
	public List<String> todosLosNombresDeIndicadores(
			List<RegistroIndicador> listaIndicadores) {
		Criteria criteria = entityManager.unwrap(Session.class)
				.createCriteria(RegistroIndicador.class)
				.setProjection(Projections.property("nombre"));
		return (List<String>) criteria.list();
	}

	public boolean esIndicador(String nombre) {
		if (getRegistroIndicador(nombre) == null) {
			return false;
		}
		return true;
	}

	public RegistroIndicador getRegistroIndicador(String nombreIndicador) {
		Criteria criteria = entityManager.unwrap(Session.class)
				.createCriteria(RegistroIndicador.class)
				.add(Restrictions.eq("nombre", nombreIndicador));
		return (RegistroIndicador) criteria.uniqueResult();
	}
}
