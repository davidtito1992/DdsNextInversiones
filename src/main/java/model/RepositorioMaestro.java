package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;
import org.uqbar.commons.utils.Observable;

@Observable
public class RepositorioMaestro extends CollectionBasedRepo<Empresa> {


	/********* ATRIBUTOS *********/
	
	private static RepositorioMaestro instance = new RepositorioMaestro();

	
	/********* METODOS *********/
	
	public static RepositorioMaestro repositorioMaestro() {
		return instance;
	}
	
	@Override
	public Class<Empresa> getEntityType() {
		// TODO Auto-generated method stub
		return Empresa.class;
	}

	@Override
	public Empresa createExample() {
		// TODO Auto-generated method stub
		return new Empresa();
	}

	@Override
	protected Predicate getCriterio(Empresa example) {
		// TODO Auto-generated method stub
		return null;
	}

	// **********Aca deberia ir un parametro para filtrar?*************/
    //Estos metodos son los que ya estaban...el framework ya tiene metodos 
	//para filtrar..despues ver..
	public ArrayList<String> dameNombresEmpresas() {

		HashSet<String> nombreEmpresas = new HashSet<String>();
		this.allInstances().forEach(name -> (nombreEmpresas.add(name.getNombre())));
		ArrayList<String> nombreEmpresasFinal = new ArrayList<String>(nombreEmpresas);
		Collections.sort(nombreEmpresasFinal);
		return nombreEmpresasFinal;
	}

	// **********Aca deberia ir un parametro para filtrar?*************/
	 //Estos metodos son los que ya estaban...el framework ya tiene metodos 
		//para filtrar..despues ver..
	public ArrayList<String> dameCuentasEmpresas() {

		HashSet<String> nombreCuentas = new HashSet<String>();
		this.allInstances().forEach(empresa -> {
			empresa.getPeriodos().forEach(
					periodo -> {
						periodo.getCuentas()
								.forEach(
										cuenta -> nombreCuentas.add(cuenta
												.getNombre()));
					});
		});

		ArrayList<String> nombreCuentasfinal = new ArrayList<String>(
				nombreCuentas);
		Collections.sort(nombreCuentasfinal);
		return nombreCuentasfinal;
	}

	// **********Aca deberia ir un parametro para filtrar?*************/
	 //Estos metodos son los que ya estaban...el framework ya tiene metodos 
		//para filtrar..despues ver..
	public  ArrayList<Integer> dameAniosPeriodos() {

		HashSet<Integer> cantidadAnios = new HashSet<Integer>();
		this.allInstances().forEach(empresa -> {
			empresa.getPeriodos().forEach(periodo -> {
				cantidadAnios.add(periodo.getAño());
			});
		});
		ArrayList<Integer> nombreCuentasfinal = new ArrayList<Integer>(
				cantidadAnios);
		Collections.sort(nombreCuentasfinal);
		return nombreCuentasfinal;
	}

}