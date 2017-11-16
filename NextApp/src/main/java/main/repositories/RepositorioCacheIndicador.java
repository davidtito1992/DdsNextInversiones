package main.repositories;

import java.time.Year;

import main.cache.CacheIndicador;
import model.RegistroIndicador;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Query;

public class RepositorioCacheIndicador extends Repository<CacheIndicador> {

	private static RepositorioCacheIndicador repositorioCacheIndicador;

	private RepositorioCacheIndicador() {
		super(CacheIndicador.class);
	}

	public static RepositorioCacheIndicador getSingletonInstance() {
		if (repositorioCacheIndicador == null) {
			repositorioCacheIndicador = new RepositorioCacheIndicador();
		}
		return repositorioCacheIndicador;
	}

	/********* METODOS *********/

	public boolean existeCacheIndicador(long idUsuario, String indicador,
			String nombreEmpresa, Year Year, int semestre) {
	/*	Criteria criteria = entityManager.unwrap(Session.class)
				.createCriteria(RegistroIndicador.class)
				.add(Restrictions.eq("idUser", idUsuario))
				.add(Restrictions.eq("registroIndicador", indicador))
				.add(Restrictions.eq("nombreEmpresa", nombreEmpresa))
				.add(Restrictions.eq("anio", Year.getValue()))
				.add(Restrictions.eq("semestre", semestre));

		return criteria.uniqueResult().equals(null);*/
		int anioCalc = Year.getValue();
		String query = "SELECT c FROM CacheIndicador c "
				+ "WHERE c.idUser = :idUsuario AND c.registroIndicador= :indicador AND c.nombreEmpresa= :nombreEmpresa "
				+ "AND c.anio= :anioCalc AND c.semestre= :semestre";

		Query q2 = entityManager.createQuery(query)
				.setParameter("idUsuario", idUsuario)
				.setParameter("indicador", indicador)
				.setParameter("nombreEmpresa", nombreEmpresa)
				.setParameter("anioCalc", anioCalc)
				.setParameter("semestre", semestre);

		return !q2.getResultList().isEmpty();
	/*	return false;*/
	}

	public String getValorCacheIndicador(long idUsuario, String indicador,
			String nombreEmpresa, Year Year, int semestre) {

		int anioCalc = Year.getValue();
		String query = "SELECT c.calculo FROM CacheIndicador c "
				+ "WHERE c.idUser = :idUsuario AND c.registroIndicador = :indicador AND c.nombreEmpresa= :nombreEmpresa "
				+ "AND c.anio= :anioCalc AND c.semestre= :semestre";

		Query q2 = entityManager.createQuery(query)
				.setParameter("idUsuario", idUsuario)
				.setParameter("indicador", indicador)
				.setParameter("nombreEmpresa", nombreEmpresa)
				.setParameter("anioCalc", anioCalc)
				.setParameter("semestre", semestre);

		return q2.getSingleResult().toString();
	}

}