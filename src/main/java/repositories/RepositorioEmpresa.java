package repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import model.Empresa;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;
import org.uqbar.commons.utils.Observable;

@Observable
public class RepositorioEmpresa extends CollectionBasedRepo<Empresa> {

	/********* ATRIBUTOS *********/

	private static RepositorioEmpresa instance = new RepositorioEmpresa();

	/********* METODOS *********/

	// LO LLAMA EL INICIALIZADOR
	public void cargarListaEmpresas(List<Empresa> empresas) {
		for (int i = 0; i < empresas.size(); i++) {
			create(empresas.get(i));
		}
	}

	public static RepositorioEmpresa repositorioMaestro() {
		return instance;
	}

	@Override
	public Class<Empresa> getEntityType() {
		return Empresa.class;
	}

	@Override
	public Empresa createExample() {
		return new Empresa();
	}

	@Override
	protected Predicate getCriterio(Empresa example) {
		return null;
	}

	// METODO PARA FILTRAR UNA LISTA DE EMPRESAS
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

	public boolean filtroAnio(Integer añoSeleccionado, Empresa empresa) {
		if (añoSeleccionado == null) {
			return true;
		} else {
			return empresa.getPeriodos().stream()
					.anyMatch(periodo -> periodo.getAño() == añoSeleccionado);
		}
	}

	public boolean filtroSemestre(Integer semestreSeleccionado, Empresa empresa) {
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

	public boolean filtroNombre(String nombreSeleccionado, Empresa empresa) {
		if (nombreSeleccionado == null) {
			return true;
		} else {
			return empresa.getNombre().equals(nombreSeleccionado);
		}
	}

	public boolean filtroCuenta(String cuentaSeleccionada, Empresa empresa) {
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
	
	public ArrayList<Integer> todosLosAnios(List<Empresa> listaEmpresas){
		HashSet<Integer> cantidadAnios = new HashSet<Integer>();
		listaEmpresas.forEach(empresa -> {
			empresa.getPeriodos().forEach(periodo -> {
				cantidadAnios.add(periodo.getAño());
			});
		});
		ArrayList<Integer> CantidadesAniosFinal = new ArrayList<Integer>(
				cantidadAnios);
		Collections.sort(CantidadesAniosFinal);
        return CantidadesAniosFinal;
	}
	
	public ArrayList<Integer> todosLosPeriodos(List<Empresa> listaEmpresa){

		HashSet<Integer> cantidadSemestres = new HashSet<Integer>();
		listaEmpresa.forEach(empresa -> {
			empresa.getPeriodos().forEach(periodo -> {
				cantidadSemestres.add(periodo.getSemestre());
			});
		});
		ArrayList<Integer> cantidadSemestresFinal = new ArrayList<Integer>(
				cantidadSemestres);
		Collections.sort(cantidadSemestresFinal);
		return cantidadSemestresFinal;
	}
	
	public ArrayList<String> todosLosNombresDeCuentas(List<Empresa> listaEmpresas){
		
		HashSet<String> nombreCuentas = new HashSet<String>();
		listaEmpresas.forEach(empresa -> {
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
	return nombreCuentasfinal ;
	}
	
	public ArrayList<String> todosLosNombresDeEmpresas(List<Empresa> empresas){

		HashSet<String> nombreEmpresas = new HashSet<String>();
		empresas.forEach(name -> (nombreEmpresas.add(name.getNombre())));
		ArrayList<String> nombreEmpresasFinal = new ArrayList<String>(
				nombreEmpresas);
		Collections.sort(nombreEmpresasFinal);
		return nombreEmpresasFinal ;
	}
	
}