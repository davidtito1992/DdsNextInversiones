package repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import db.EntityManagerHelper;

public abstract class Repository<T> {

	@PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "db")
	public EntityManager entityManager = EntityManagerHelper.entityManager();
	private Class<T> clazz;

	public void cargarListaDeElementos(List<T> lista) {
		EntityManagerHelper.beginTransaction();
		lista.forEach(elem -> entityManager.persist(elem));
		EntityManagerHelper.commit();

	}

	@Transactional
	public T buscar(long id) {
		return entityManager.find(this.clazz, id);
	}

	//
	// @Transactional
	// public T merge(T object) {
	// return entityManager.merge(object);
	// }

	@Transactional
	public void agregar(T elemento) {
		EntityManagerHelper.beginTransaction();
		entityManager.persist(elemento);
		EntityManagerHelper.commit();
	}

	@Transactional
	public void eliminar(long id) {
		EntityManagerHelper.beginTransaction();
		entityManager.remove(this.buscar(id));
		EntityManagerHelper.commit();
	}
}
