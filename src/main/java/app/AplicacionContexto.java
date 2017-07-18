package app;

import repositories.RepositorioUnicoDeEmpresas;
import repositories.RepositorioUnicoDeIndicadores;
import repositories.RepositorioUnicoDeMetodologias;

public class AplicacionContexto {
	
	private RepositorioUnicoDeEmpresas repositorioEmpresas;
	private RepositorioUnicoDeIndicadores repositorioIndicadores;
	private RepositorioUnicoDeMetodologias repositorioMetodologias;
	
	private static AplicacionContexto aplicacionContexto = null;
	
	public static AplicacionContexto getSingletonInstance(){
        if (aplicacionContexto == null){
        	aplicacionContexto = new AplicacionContexto();}
        else{
        	throw new RuntimeException("AplicacionContexto ya ha sido creado");}
       
        return aplicacionContexto;
	}
	


}
