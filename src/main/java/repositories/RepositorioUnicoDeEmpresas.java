package repositories;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Cuenta;
import model.Empresa;
import modelFilter.EmpresaDataFilter;

public class RepositorioUnicoDeEmpresas {
	
	private static RepositorioUnicoDeEmpresas repositorioEmpresas;
	
	private List<Empresa> empresasExistentes;
	
	private RepositorioUnicoDeEmpresas(){}
	
	public static RepositorioUnicoDeEmpresas getSingletonInstance(){
        if (repositorioEmpresas == null){
        	repositorioEmpresas = new RepositorioUnicoDeEmpresas();}
        else{
        	throw new RuntimeException("El repositorio de empresas ya ha sido creado");}
       
        return repositorioEmpresas;
	}

	public static RepositorioUnicoDeEmpresas getRepositorioEmpresas() {
		return repositorioEmpresas;
	}

	public static void setRepositorioEmpresas(
			RepositorioUnicoDeEmpresas repositorioEmpresas) {
		RepositorioUnicoDeEmpresas.repositorioEmpresas = repositorioEmpresas;
	}
	
// -------------- Metodos -------------
	
	public void cargarListaEmpresas(List<Empresa> empresas) {
		for(Empresa empresa : empresas){
			empresasExistentes.add(empresa);
		}
	}
	
	// METODO PARA FILTRAR UNA LISTA DE EMPRESAS
	public List<Empresa> filtrar(EmpresaDataFilter dataFilter) {
		return 	empresasExistentes.stream().filter(empresa -> filtroCuenta(dataFilter.getCuentaSeleccionada(), empresa)
						&& filtroNombre(dataFilter.getNombreSeleccionado(),empresa)
						&& filtroSemestre(dataFilter.getSemestreSeleccionado(),empresa)
						&& filtroAnio(dataFilter.getAnioSeleccionado(),empresa))
						.collect(Collectors.toList());
	}
	
	public boolean filtroCuenta(String cuentaSeleccionada, Empresa empresa) {
		return cuentaSeleccionada == null || empresa.getPeriodos().stream().anyMatch(periodo -> periodo.getCuentas()
				.stream().anyMatch(cuenta -> cuenta.getNombre().equalsIgnoreCase(cuentaSeleccionada)));
	}
	
	public boolean filtroNombre(String nombreSeleccionado, Empresa empresa) {
		return nombreSeleccionado == null || empresa.getNombre().equalsIgnoreCase(nombreSeleccionado);
	}
	
	public boolean filtroSemestre(Integer semestreSeleccionado, Empresa empresa) {
		return semestreSeleccionado == null || empresa.getPeriodos().stream().anyMatch(periodo -> periodo.getSemestre() == semestreSeleccionado);
	}
	
	public boolean filtroAnio(Year anioSeleccionado, Empresa empresa) {
		return anioSeleccionado == null || empresa.getPeriodos().stream().anyMatch(periodo -> periodo.getAnio().equals(anioSeleccionado));

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
	
	public ArrayList<String> todosLosNombresDeCuentas(List<Empresa> listaEmpresas) {
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
	
	public BigDecimal getValorCuenta(String nombreEmpresa, Year anio, int semestre, String nombreCuenta) {
		List<Cuenta> cuentaADevolver = this.obtenerCuenta(nombreEmpresa, anio, semestre, nombreCuenta);
		if (cuentaADevolver.isEmpty())
			throw new RuntimeException(
					"No pudimos obtener el valor de la variable: " + nombreCuenta);
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

	public List<Cuenta> obtenerCuenta(String nombreSeleccionado,Year anioSeleccionado, Integer semestreSeleccionado,String nombreCuenta) {
		List<Cuenta> cuentasADevolver = empresasExistentes.stream()
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
		List<Cuenta> todasLasCuentas = empresasExistentes.stream()
				.map(empresa -> empresa.getPeriodos())
				.flatMap(periodo -> periodo.stream())
				.map(periodo -> periodo.getCuentas())
				.flatMap(cuenta -> cuenta.stream()).distinct()
				.collect(Collectors.toList());

		return todasLasCuentas;
	}	

}
