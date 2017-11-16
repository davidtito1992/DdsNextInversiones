package main.repositories;

import main.condiciones.Condicion;
import model.RegistroIndicador;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.uqbar.commons.utils.Observable;

import java.util.List;

import javax.persistence.Query;

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
	public List<String> todosLosNombresDeIndicadores(
			List<RegistroIndicador> listaIndicadores) {
		Criteria criteria = entityManager.unwrap(Session.class)
				.createCriteria(RegistroIndicador.class)
				.setProjection(Projections.property("nombre"));
		return (List<String>) criteria.list();
	}

	public boolean esIndicador(String nombre) {
		if (this.getRegistroIndicador(nombre) == null) {
			return false;
		}
		return true;
		

//		String query = "SELECT i.nombre FROM Indicadores i WHERE i.nombre = :nombreIndicador";
//
//		Query q2 = entityManager.createQuery(query).setParameter(
//				"nombreIndicador", nombre);
//
//		if (!q2.getResultList().isEmpty()) {
//			return true;
//		}
//		return false;
		
	}

	public RegistroIndicador getRegistroIndicador(String nombreIndicador) {
		Criteria criteria = entityManager.unwrap(Session.class)
				.createCriteria(RegistroIndicador.class)
				.add(Restrictions.eq("nombre", nombreIndicador));
		return (RegistroIndicador) criteria.uniqueResult();
	}

	public Boolean existCondicionWith(Long registroIndicadorId) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(
				Condicion.class);
		criteria.add(Restrictions.eq("indicador.registroIndicadorId",
				registroIndicadorId));
		return criteria.list().size() > 0;
	}
}
