package repositories;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Cuenta;
import model.Empresa;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;
import org.uqbar.commons.utils.Observable;

import java.math.BigDecimal;

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
			Year anioSeleccionado) {
		return this
				.allInstances()
				.stream()
				.filter(empresa -> filtroCuenta(cuentaSeleccionada, empresa)
						&& filtroNombre(nombreSeleccionado, empresa)
						&& filtroSemestre(semestreSeleccionado, empresa)
						&& filtroAnio(anioSeleccionado, empresa))
				.collect(Collectors.toList());
	}

	public boolean filtroAnio(Year anioSeleccionado, Empresa empresa) {
		if (anioSeleccionado == null) {
			return true;
		} else {
			return empresa.getPeriodos().stream()
					.anyMatch(periodo -> periodo.getAnio().equals(anioSeleccionado));
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

	public ArrayList<Year> todosLosAnios(List<Empresa> listaEmpresas) {

		ArrayList<Year> todosLosAnios = listaEmpresas.stream()
				.map(empresa -> empresa.getPeriodos())
				.flatMap(periodos -> periodos.stream())
				.map(periodo -> periodo.getAnio()).distinct().sorted()
				.collect(Collectors.toCollection(ArrayList::new));

		return todosLosAnios;
	}

	public ArrayList<Integer> todosLosPeriodos(List<Empresa> listaEmpresa) {

		ArrayList<Integer> todosLosPeriodos = listaEmpresa.stream()
				.map(empresa -> empresa.getPeriodos())
				.flatMap(periodos -> periodos.stream())
				.map(periodo -> periodo.getSemestre()).distinct().sorted()
				.collect(Collectors.toCollection(ArrayList::new));

		return todosLosPeriodos;
	}

	public ArrayList<String> todosLosNombresDeCuentas(
			List<Empresa> listaEmpresas) {

		ArrayList<String> nombresDeTodasLasCuentas = listaEmpresas.stream()
				.map(empresa -> empresa.getPeriodos())
				.flatMap(periodos -> periodos.stream())
				.map(periodo -> periodo.getCuentas())
				.flatMap(cuentas -> cuentas.stream())
				.map(cuenta -> cuenta.getNombre()).distinct().sorted()
				.collect(Collectors.toCollection(ArrayList::new));

		return nombresDeTodasLasCuentas;

	}

	public ArrayList<String> todosLosNombresDeEmpresas(List<Empresa> empresas) {

		ArrayList<String> nombresDeTodasLasEmpresas = empresas.stream()
				.map(empresa -> empresa.getNombre()).distinct().sorted()
				.collect(Collectors.toCollection(ArrayList::new));

		return nombresDeTodasLasEmpresas;
	}
//
//	public String transformValores(String formulaConCuentas,
//			List<Cuenta> cuentasUnaEmpresa) {
//		String[] componentes = formulaConCuentas.split(" ");
//		for (int i = 0; i < componentes.length; i++) {
//			if (esCuenta(componentes[i], cuentasUnaEmpresa)) {
//				componentes[i] = String.valueOf(getValorCuenta(componentes[i],
//						cuentasUnaEmpresa));
//			}
//		}
//		return String.join(" ", componentes);
//	}

	private BigDecimal getValorCuenta(String nombre,
			List<Cuenta> cuentasUnaEmpresa) {
		List<Cuenta> cuentaADevolver = cuentasUnaEmpresa.stream()
				.filter(cuenta -> cuenta.getNombre().equals(nombre))
				.collect(Collectors.toList());
		return cuentaADevolver.get(0).getValor();
	}

	public boolean esCuenta(String componente, List<Cuenta> cuentasUnaEmpresa) {
		return cuentasUnaEmpresa.stream().map(cuenta -> cuenta.getNombre())
				.anyMatch(nombreCuenta -> nombreCuenta.equalsIgnoreCase(componente));
	}

	public List<Cuenta> obtenerCuentas(String nombreSeleccionado,
			Integer semestreSeleccionado, Year anioSeleccionado) {
		List<Empresa> empresas = filtrar(null, nombreSeleccionado,
				semestreSeleccionado, anioSeleccionado);

		return empresas.get(0).getPeriodos().get(0).getCuentas();
	}

	public List<Cuenta> todasLasCuentas() {
		List<Empresa> empresas = this.allInstances();

		List<Cuenta> todasLasCuentas = empresas.stream()
				.map(empresa -> empresa.getPeriodos())
				.flatMap(periodo -> periodo.stream())
				.map(periodo -> periodo.getCuentas())
				.flatMap(cuenta -> cuenta.stream()).distinct()
				.collect(Collectors.toList());

		return todasLasCuentas;
	}
}