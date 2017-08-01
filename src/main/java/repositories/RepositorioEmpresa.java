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

		empresas.forEach(empresa -> this.create(empresa));
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
		return anioSeleccionado == null
				|| empresa
						.getPeriodos()
						.stream()
						.anyMatch(
								periodo -> periodo.getAnio().equals(
										anioSeleccionado));

	}

	public boolean filtroSemestre(Integer semestreSeleccionado, Empresa empresa) {
		return semestreSeleccionado == null
				|| empresa
						.getPeriodos()
						.stream()
						.anyMatch(
								periodo -> periodo.getSemestre() == semestreSeleccionado);

	}

	public boolean filtroNombre(String nombreSeleccionado, Empresa empresa) {
		return nombreSeleccionado == null ||

		empresa.getNombre().equalsIgnoreCase(nombreSeleccionado);

	}

	public boolean filtroCuenta(String cuentaSeleccionada, Empresa empresa) {
		return cuentaSeleccionada == null
				|| empresa
						.getPeriodos()
						.stream()
						.anyMatch(
								periodo -> periodo
										.getCuentas()
										.stream()
										.anyMatch(
												cuenta -> cuenta
														.getNombre()
														.equalsIgnoreCase(
																cuentaSeleccionada)));

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

	public BigDecimal getValorCuenta(String nombreEmpresa, Year anio,
			int semestre, String nombreCuenta) {

		List<Cuenta> cuentaADevolver = this.obtenerCuenta(nombreEmpresa, anio,
				semestre, nombreCuenta);

		if (cuentaADevolver.isEmpty())

			throw new RuntimeException(
					"No pudimos obtener el valor de la variable: "
							+ nombreCuenta);
		else
			return cuentaADevolver.get(0).getValor();

	}

	public boolean esCuenta(String componente) {
		return this
				.todasLasCuentas()
				.stream()
				.map(cuenta -> cuenta.getNombre())
				.anyMatch(
						nombreCuenta -> nombreCuenta
								.equalsIgnoreCase(componente));
	}

	public List<Cuenta> obtenerCuenta(String nombreSeleccionado,
			Year anioSeleccionado, Integer semestreSeleccionado,
			String nombreCuenta) {

		List<Cuenta> cuentasADevolver = this
				.allInstances()
				.stream()
				.filter(empresa -> empresa.getNombre().equalsIgnoreCase(
						nombreSeleccionado))
				.map(empresa -> empresa.getPeriodos())
				.flatMap(periodo -> periodo.stream())
				.filter(periodo -> periodo.getAnio().equals(anioSeleccionado)
						&& periodo.getSemestre() == semestreSeleccionado)
				.map(periodo -> periodo.getCuentas())
				.flatMap(cuentas -> cuentas.stream())
				.filter(cuenta -> cuenta.getNombre().equalsIgnoreCase(
						nombreCuenta)).collect(Collectors.toList());

		return cuentasADevolver;

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