package repositories;

import java.util.List;

import model.Metodologia;

public class RepositorioUnicoDeMetodologias {
	
	private static RepositorioUnicoDeMetodologias repositorioMetodologias;
	private List<Metodologia> metodologiasExistentes;
	
	private RepositorioUnicoDeMetodologias(){}
	
	/********* METODOS *********/

	public void cargarListaMetodologias(List<Metodologia> registrosMetodologias) {
		for(Metodologia metodologia : registrosMetodologias){
			metodologiasExistentes.add(metodologia);
		}
	}

}
