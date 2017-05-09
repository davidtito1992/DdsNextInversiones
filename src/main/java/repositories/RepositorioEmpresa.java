package repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import model.Empresa;
import model.Periodo;
import model.SnapshotEmpresa;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;
import org.uqbar.commons.utils.Observable;

@Observable
public class RepositorioEmpresa extends CollectionBasedRepo<Empresa> {


	/********* ATRIBUTOS *********/
	
	private static RepositorioEmpresa instance = new RepositorioEmpresa();
	
	/********* METODOS *********/
	
	public void cargarListaEmpresas (List<Empresa> empresas){
		for (int i = 0; i < empresas.size(); i++) {
			create(empresas.get(i));
		}
	}
	
	public static RepositorioEmpresa repositorioMaestro() {
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
	
	public List<Empresa> filtrar(String cuentaSeleccionada,
			String nombreSeleccionado, Integer semestreSeleccionado,
			Integer añoSeleccionado) {
		return this
				.allInstances()
				.stream()
				.filter(empresa -> filtroCuenta(cuentaSeleccionada, empresa)
						&& filtroNombre(nombreSeleccionado, empresa)
						&& filtroSemestre(semestreSeleccionado, empresa)
						&& filtroAnio(añoSeleccionado, empresa))
				.collect(Collectors.toList());
	}

	private boolean filtroAnio(Integer añoSeleccionado, Empresa empresa) {
		if (añoSeleccionado == null) {
			return true;
		} else {
			return empresa.getPeriodos().stream()
					.anyMatch(periodo -> periodo.getAño() == añoSeleccionado);
		}
	}

	private boolean filtroSemestre(Integer semestreSeleccionado, Empresa empresa) {
		if (semestreSeleccionado == null) {
			return true;
		} else {
			return empresa
					.getPeriodos()
					.stream()
					.anyMatch(
							periodo -> periodo.getSemestre() == semestreSeleccionado);
		}
	}
	
	private boolean filtroNombre(String nombreSeleccionado, Empresa empresa) {
		if(nombreSeleccionado == null){
			return true;
		} else {
			return empresa.getNombre().equals(nombreSeleccionado);
		}
	}

	private boolean filtroCuenta(String cuentaSeleccionada, Empresa empresa) {
		if (cuentaSeleccionada == null) {
			return true;
		} else {
			return empresa
					.getPeriodos()
					.stream()
					.anyMatch(
							periodo -> periodo
									.getCuentas()
									.stream()
									.anyMatch(
											cuenta -> cuenta.getNombre()
													.equals(cuentaSeleccionada)));
		}
	}
}