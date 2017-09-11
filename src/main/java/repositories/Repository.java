package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;


public abstract class Repository<T>  {

	@PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "db")
	public EntityManager entityManager;

	public void cargarListaDeElementos(List<T> lista) {
		for (T element : lista) {
			entityManager.merge(element);
		}
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

}
