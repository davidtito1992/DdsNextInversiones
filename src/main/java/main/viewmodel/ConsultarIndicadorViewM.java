package main.viewmodel;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import main.app.AplicacionContexto;
import main.app.DslIndicador;
import main.repositories.RepositorioEmpresa;
import model.Empresa;
import model.RegistroIndicador;
import model.SnapshotIndicador;

import org.uqbar.commons.utils.Observable;

@Observable
public class ConsultarIndicadorViewM {

	/********* ATRIBUTOS *********/

	private List<String> nombres = new ArrayList<String>();
	private String nombreSeleccionado;
	private List<Year> anios = new ArrayList<>();
	private Year anioSeleccionado;
	private List<SnapshotIndicador> snapshotIndicadores;
	private SnapshotIndicador snapshotIndicadorSeleccionado;
	private String resultado;
	private RegistroIndicador registroIndicadorElegido;

	public ConsultarIndicadorViewM(RegistroIndicador unIndicador) {

		this.registroIndicadorElegido = unIndicador;
		this.generarTodosLosCBox(null, null);
		this.llenarTablas();
	}

	/********* GETTERS/SETTERS *********/

	public List<String> getNombres() {
		return nombres;
	}

	public RegistroIndicador getRegistroIndicadorElegido() {
		return registroIndicadorElegido;
	}

	public void setRegistroIndicadorElegido(
			RegistroIndicador registroIndicadorElegido) {
		this.registroIndicadorElegido = registroIndicadorElegido;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public List<SnapshotIndicador> getSnapshotIndicadores() {
		return snapshotIndicadores;
	}

	public void setSnapshotIndicadores(
			List<SnapshotIndicador> snapshotIndicadores) {
		this.snapshotIndicadores = snapshotIndicadores;
	}

	public SnapshotIndicador getSnapshotIndicadorSeleccionado() {
		return snapshotIndicadorSeleccionado;
	}

	public void setSnapshotIndicadorSeleccionado(
			SnapshotIndicador snapshotIndicadorSeleccionado) {
		this.snapshotIndicadorSeleccionado = snapshotIndicadorSeleccionado;
	}

	public void setNombres(List<String> nombres) {
		this.nombres = nombres;
	}

	public String getNombreSeleccionado() {
		return nombreSeleccionado;
	}

	public void setNombreSeleccionado(String nombreSeleccionado) {
		this.nombreSeleccionado = nombreSeleccionado;
		this.generarTodosLosCBox(this.nombreSeleccionado, this.anioSeleccionado);// ,
																					// this.semestreSeleccionado);
	}

	public List<Year> getAnios() {
		return anios;
	}

	public void setAnios(List<Year> anios) {
		this.anios = anios;
	}

	public Year getAnioSeleccionado() {
		return anioSeleccionado;
	}

	public void setAnioSeleccionado(Year anioSeleccionado) {
		this.anioSeleccionado = anioSeleccionado;
		this.generarTodosLosCBox(this.nombreSeleccionado, this.anioSeleccionado);// ,
																					// this.semestreSeleccionado);
	}

	/********* METODOS *********/

	public void generarTodosLosCBox(String empresa, Year anio) {

		List<Empresa> repoEmpresaFiltrado = new ArrayList<Empresa>();
		repoEmpresaFiltrado = this.getRepositorioEmpresas().filtrar(null,
				empresa, null, anio);

		generarCBoxNombresEmpresas(repoEmpresaFiltrado);
		generarCBoxAnios(repoEmpresaFiltrado);

	}

	public RepositorioEmpresa getRepositorioEmpresas() {
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}

	public void generarCBoxAnios(List<Empresa> empresas) {
		this.anios = this.getRepositorioEmpresas().todosLosAnios(empresas);
	}

	public void generarCBoxNombresEmpresas(List<Empresa> empresas) {

		this.nombres = this.getRepositorioEmpresas().todosLosNombresDeEmpresas(
				empresas);

	}

	public void reiniciar() {
		this.generarTodosLosCBox(null, null);
		this.snapshotIndicadorSeleccionado = null;
		this.limpiarFiltros();
		this.llenarTablas();
	}

	public void limpiarFiltros() {
		nombreSeleccionado = null;
		// semestreSeleccionado = null;
		anioSeleccionado = null;
	}

	public void llenarTablas() {
		this.setSnapshotIndicadores(this
				.resultadosIndicadores(getRepositorioEmpresas().allInstances()));
	}

	public List<SnapshotIndicador> resultadosIndicadores(
			List<Empresa> listaDeEmpresas) {

		List<SnapshotIndicador> listSnapshot = listaDeEmpresas
				.stream()
				.map(empresa -> empresa
						.getPeriodos()
						.stream()
						.map(periodo -> {
							return crearSnapshotIndicador(empresa.getNombre(),
									periodo.getAnio(), periodo.getSemestre());
						}).collect(Collectors.toList()))
				.flatMap(listaSnap -> listaSnap.stream())
				.collect(Collectors.toList());

		return listSnapshot;
	}

	public void buscar() {
		List<SnapshotIndicador> listSnapshot = (this
				.resultadosIndicadores(getRepositorioEmpresas().filtrar(null,
						nombreSeleccionado, null, anioSeleccionado)));
		this.setSnapshotIndicadores(listSnapshot);
	}

	public SnapshotIndicador crearSnapshotIndicador(String nombreEmpresa,
			Year anio, int semestre) {
		SnapshotIndicador snapshotIndicador = new SnapshotIndicador();
		snapshotIndicador.setNombreEmpresa(nombreEmpresa);
		snapshotIndicador.setAnio(anio);
		snapshotIndicador.setSemestre(semestre);
		String resultado;
		try {
			resultado = new DslIndicador()
					.prepararFormula(this.getRegistroIndicadorElegido(),
							nombreEmpresa, anio, semestre).calcular()
					.toPlainString();
		} catch (Exception e) {
			resultado = e.getMessage();
		}
		snapshotIndicador.setResultado(resultado);
		return snapshotIndicador;
	}
}