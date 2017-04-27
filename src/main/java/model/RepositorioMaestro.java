package model;

import java.util.ArrayList;
import java.util.Collection;

public class RepositorioMaestro {

	public static Collection<Empresa> empresas = new ArrayList<Empresa>() ;
	
	static ArrayList<String> nombreEmpresas = new ArrayList<String>()  ;
	
	public static ArrayList<String> dameNombresEmpresas(){
		///esto no tiene q ser asi
   empresas.add(new Empresa("Facebook")) ;
   empresas.add(new Empresa("Next")) ;
   empresas.add(new Empresa("Apple")) ;
   empresas.add(new Empresa("Google")) ;
		
    empresas.forEach(name-> (nombreEmpresas.add(name.getNombre()))) ;
    nombreEmpresas.forEach(empresa -> System.out.printf(empresa + '\n'));
    
    //probando
		return nombreEmpresas;
}

}