package repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.uqbar.commons.utils.Observable;

import model.Metodologia;

@SuppressWarnings("rawtypes")
@Observable
public class RepositorioMetodologia extends Repository{
	
//	private List<Metodologia> metodologiasExistentes;
	private static RepositorioMetodologia repositorioMetodologia = null;
	
	public static RepositorioMetodologia getSingletonInstance(){
		
        if (repositorioMetodologia == null){
        	repositorioMetodologia = new RepositorioMetodologia();}

        return repositorioMetodologia;
	}
	
	private RepositorioMetodologia(){
		
	}
	
	/********* METODOS *********/

	@Transactional
	public void agregarMetodologia(Metodologia metodologia) {
		entityManager.merge(metodologia);
	}

	@Transactional
	public void eliminarMetodologia(long id) {
		entityManager.remove(this.buscar(id));
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Metodologia buscar(long id) {
		return (Metodologia) findById(Metodologia.class, id);
	}

	@Transactional
	public List<Metodologia> allInstances() {
		return entityManager.createQuery("from Metodologia", Metodologia.class).getResultList();
	}
	
	
	public void cargarListaMetodologias(List<Metodologia> registrosMetodologias) {
		for(Metodologia metodologia : registrosMetodologias){
			this.agregarMetodologia(metodologia);
		}
	}


}