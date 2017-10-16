package main.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;

import main.db.EntityManagerHelper;

import org.hibernate.Criteria;
import org.hibernate.Session;

public abstract class Repository<T> {

	public Repository(Class<T> clazz) {
		this.clazz = clazz;
	}

	@PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "db")
	public EntityManager entityManager = EntityManagerHelper.entityManager();
	private Class<T> clazz;

	public void cargarListaDeElementos(List<T> lista) {
		EntityManagerHelper.beginTransaction();
		lista.forEach(elem -> entityManager.persist(elem));
		EntityManagerHelper.commit();

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> allInstances() {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(this.clazz);
		return criteria.list();
	}

	@Transactional
	public T buscar(long id) {
		return entityManager.find(this.clazz, id);
	}

	@Transactional
	public void agregar(T elemento) {
		EntityManagerHelper.beginTransaction();
		entityManager.merge(elemento);
		EntityManagerHelper.commit();
	}

	@Transactional
	public void eliminar(long id) {
		EntityManagerHelper.beginTransaction();
		entityManager.remove(this.buscar(id));
		EntityManagerHelper.commit();
	}
}
