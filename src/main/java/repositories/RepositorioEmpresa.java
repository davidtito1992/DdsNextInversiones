package repositories;

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