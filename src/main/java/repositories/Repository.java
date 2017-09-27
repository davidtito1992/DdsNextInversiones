package repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import db.EntityManagerHelper;


public abstract class Repository<T>  {

	@PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "db")
	public EntityManager entityManager = EntityManagerHelper.entityManager();

	public void cargarListaDeElementos(List<T> lista) {
		EntityManagerHelper.beginTransaction();
		lista.forEach(elem->entityManager.persist(elem));
		EntityManagerHelper.commit();
		
	}
	
	public T findById(Class<T> typeParameterClass, Long id) {
		return entityManager.find(typeParameterClass, id);
	}
	
	public void persist(T object) {
		entityManager.persist(object);
	}
	
	public T merge(T object) { 
		return entityManager.merge(object);
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
	
	@Transactional
	public abstract T buscar(long id) ;
}
