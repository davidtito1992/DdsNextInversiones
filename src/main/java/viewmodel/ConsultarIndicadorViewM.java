package viewmodel;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Empresa;
import model.RegistroIndicador;
import model.SnapshotIndicador;
import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;
import repositories.RepositorioEmpresa;
import app.DslIndicador;

@Observable
public class ConsultarIndicadorViewM {

	/********* ATRIBUTOS *********/

	private List<String> nombres = new ArrayList<String>();
	private String nombreSeleccionado;
	private List<Year> anios = new ArrayList<>();
	private Year anioSeleccionado;
	// private List<Integer> semestre = new ArrayList<Integer>();
	// private Integer semestreSeleccionado;
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

	// public List<Integer> getSemestre() {
	// return semestre;
	// }
	//
	// public void setSemestre(List<Integer> semestre) {
	// this.semestre = semestre;
	// }
	//
	// public Integer getSemestreSeleccionado() {
	// return semestreSeleccionado;
	// }
	//
	// public void setSemestreSeleccionado(Integer semestreSeleccionado) {
	// this.semestreSeleccionado = semestreSeleccionado;
	// this.generarTodosLosCBox(this.nombreSeleccionado,
	// this.anioSeleccionado, this.semestreSeleccionado);
	// }

	/********* METODOS *********/

	public void generarTodosLosCBox(String empresa, Year anio) {// , Integer
																// semestre) {

		List<Empresa> repoEmpresaFiltrado = new ArrayList<Empresa>();
		repoEmpresaFiltrado = this.getRepoEmpresas().filtrar(null, empresa,
				null, anio); // semestre, anio);

		generarCBoxNombresEmpresas(repoEmpresaFiltrado);
		generarCBoxAnios(repoEmpresaFiltrado);
		// generarCBoxSemestre(repoEmpresaFiltrado);

	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}

	public void generarCBoxAnios(List<Empresa> empresas) {

		this.anios = this.getRepoEmpresas().todosLosAnios(empresas);

	}

	// public void generarCBoxSemestre(List<Empresa> empresas) {
	//
	// this.semestre = this.getRepoEmpresas().todosLosPeriodos(empresas);
	//
	// }

	public void generarCBoxNombresEmpresas(List<Empresa> empresas) {

		this.nombres = this.getRepoEmpresas().todosLosNombresDeEmpresas(
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
				.resultadosIndicadores(getRepoEmpresas().allInstances()));
	}

	public List<SnapshotIndicador> resultadosIndicadores(
			List<Empresa> listaDeEmpresas) {

		List<SnapshotIndicador> listSnapshot = listaDeEmpresas
				.stream()
				.map(empresa -> empresa
						.getPeriodos()
						.stream()
						.map(periodo ->

						{
							return crearSnapshotIndicador(empresa.getNombre(),
									periodo.getAnio(), periodo.getSemestre());
						}).collect(Collectors.toList()))
				.flatMap(listaSnap -> listaSnap.stream())
				.collect(Collectors.toList());

		return listSnapshot;
	}

	public void buscar() {
		List<SnapshotIndicador> listSnapshot = (this
				.resultadosIndicadores(getRepoEmpresas().filtrar(null,
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
