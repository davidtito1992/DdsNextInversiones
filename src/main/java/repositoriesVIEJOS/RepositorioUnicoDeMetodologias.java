package repositoriesVIEJOS;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.Metodologia;

@Observable
public class RepositorioUnicoDeMetodologias extends Repositorio{
	
//	private List<Metodologia> metodologiasExistentes;
	private static RepositorioUnicoDeMetodologias repositorioMetodologias = null;
	
	public static RepositorioUnicoDeMetodologias getSingletonInstance(){
		
        if (repositorioMetodologias == null){
        	repositorioMetodologias = new RepositorioUnicoDeMetodologias();}

        return repositorioMetodologias;
	}
	
	private RepositorioUnicoDeMetodologias(){
	}
	
	/********* METODOS *********/

	@SuppressWarnings("unchecked")
	public void cargarListaMetodologias(List<Metodologia> registrosMetodologias) {
		for(Metodologia metodologia : registrosMetodologias){
			this.getElementos().add(metodologia);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void agregarMetodologiaNueva(Metodologia metodologia){
		this.getElementos().add(metodologia);
	}
	
	//@SuppressWarnings("unchecked")
	public void borrarElemento(Metodologia metodologia){
		this.getElementos().remove(metodologia);
	}

}
