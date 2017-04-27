package model;

import java.util.ArrayList;
import java.util.Collection;

public class RepositorioMaestro {

	public static Collection<Empresa> empresas = new ArrayList<Empresa>() ;
	
	static ArrayList<String> nombreEmpresas = new ArrayList<String>()  ;
	
	public static ArrayList<String> dameNombresEmpresas(){
		
		empresas.forEach(name-> (nombreEmpresas.add(name.getNombre()))) ;
    
		return nombreEmpresas;
}

}
