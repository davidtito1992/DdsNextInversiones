package repositories;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.Metodologia;

@Observable
public class RepositorioUnicoDeMetodologias extends Repositorio{
	
	private List<Metodologia> metodologiasExistentes;
	private static RepositorioUnicoDeMetodologias repositorioMetodologias = null;
	
	public static RepositorioUnicoDeMetodologias getSingletonInstance(){
		
        if (repositorioMetodologias == null){
        	repositorioMetodologias = new RepositorioUnicoDeMetodologias();}

        return repositorioMetodologias;
	}
	
	private RepositorioUnicoDeMetodologias(){
		metodologiasExistentes = new ArrayList<>();
	}
	
	
	public List<Metodologia> getMetodologiasExistentes() {
		return metodologiasExistentes;
	}

	public void setMetodologiasExistentes(List<Metodologia> metodologiasExistentes) {
		this.metodologiasExistentes = metodologiasExistentes;
	}

	/********* METODOS *********/

	public void cargarListaMetodologias(List<Metodologia> registrosMetodologias) {
		for(Metodologia metodologia : registrosMetodologias){
			metodologiasExistentes.add(metodologia);
		}
	}
	
	public void agregarMetodologiaNueva(Metodologia metodologia){
		metodologiasExistentes.add(metodologia);
	}

}
