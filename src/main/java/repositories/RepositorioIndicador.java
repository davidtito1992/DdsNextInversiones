package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import model.Empresa;
import model.RegistroIndicador;

import org.uqbar.commons.utils.Observable;

import repositoriesVIEJOS.Repositorio;

@SuppressWarnings("rawtypes")
@Observable
public class RepositorioIndicador extends Repository{
	
	private static RepositorioIndicador repositorioIndicador; 
	
	private RepositorioIndicador(){
		super();
	}
	
	public static RepositorioIndicador getSingletonInstance(){
		
        if (repositorioIndicador == null){
        	repositorioIndicador = new RepositorioIndicador();}
       
        return repositorioIndicador;
	}
	
//	---------- Metodos ---------

	@Transactional
	public void agregarIndicador(RegistroIndicador registroIndicador) {
		entityManager.merge(registroIndicador);
	}

	@Transactional
	public void eliminarIndicador(long id) {
		entityManager.remove(this.buscar(id));
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public RegistroIndicador buscar(long id) {
		return (RegistroIndicador) findById(RegistroIndicador.class, id);
	}

	@Transactional
	public List<RegistroIndicador> allInstances() {
		return entityManager.createQuery("from RegistroIndicador", RegistroIndicador.class).getResultList();
	}
	
	public void cargarListaIndicadores(List<RegistroIndicador> registrosIndicadores) {
		for(RegistroIndicador registroIndicador : registrosIndicadores){
			this.agregarIndicador(registroIndicador);
		}
	}
	
	public ArrayList<String> todosLosNombresDeIndicadores(List<RegistroIndicador> listaIndicadores) {
		ArrayList<String> nombresDeTodosLosIndicadores = listaIndicadores
				.stream().map(indicador -> indicador.getNombre()).distinct()
				.sorted().collect(Collectors.toCollection(ArrayList::new));
		return nombresDeTodosLosIndicadores;
	}
	
	public boolean esIndicador(String componente) {
		List<RegistroIndicador> indicadoresExistentes = new ArrayList<RegistroIndicador>();
		indicadoresExistentes = this.allInstances();
		return indicadoresExistentes.stream().map(regIndicador -> regIndicador.getNombre())
				.anyMatch(NombreregIndicador -> NombreregIndicador.equalsIgnoreCase(componente));
	}

	public RegistroIndicador getRegistroIndicador(String nombreIndicador) {
		return entityManager.createQuery("from RegistroIndicador RI where RI.nombre = "+nombreIndicador, RegistroIndicador.class).getResultList().get(0);
	}
}
