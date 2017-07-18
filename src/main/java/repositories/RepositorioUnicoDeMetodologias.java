package repositories;

import java.util.ArrayList;
import java.util.List;

import model.Metodologia;

public class RepositorioUnicoDeMetodologias {
	
	private List<Metodologia> metodologiasExistentes;
	private static RepositorioUnicoDeMetodologias repositorioMetodologias = null;
	
	public static RepositorioUnicoDeMetodologias getSingletonInstance(){
        if (repositorioMetodologias == null){
        	repositorioMetodologias = new RepositorioUnicoDeMetodologias();}
        else{
        	throw new RuntimeException("El repositorio de indicadores ya ha sido creado");}
       
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

}
