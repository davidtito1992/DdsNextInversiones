package viewmodel;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.SnapshotIndicador;

import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;

import calculator.Calculator;
import calculator.ParseException;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;

@Observable
public class ConsultarIndicadorViewM {

	/********* ATRIBUTOS *********/

	private List<String> nombres = new ArrayList<String>();
	private String nombreSeleccionado;
	private List<Integer> años = new ArrayList<Integer>();
	private Integer añoSeleccionado;
	private List<Integer> semestre = new ArrayList<Integer>();
	private Integer semestreSeleccionado;
	private List<SnapshotIndicador> snapshotIndicadores;
	private SnapshotIndicador snapshotIndicadorSeleccionado;
	private double resultado;
	private Indicador indicadorElegido;

	/********* GETTERS/SETTERS *********/

	public List<String> getNombres() {
		return nombres;
	}

	public Indicador getIndicadorElegido() {
		return indicadorElegido;
	}

	public void setIndicadorElegido(Indicador indicadorElegido) {
		this.indicadorElegido = indicadorElegido;
	}

	public double getResultado() {
		return resultado;
	}

	public void setResultado(double resultado) {
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
		this.generarTodosLosCBox(this.nombreSeleccionado, this.añoSeleccionado,
				this.semestreSeleccionado);
	}

	public List<Integer> getAños() {
		return años;
	}

	public void setAños(List<Integer> años) {
		this.años = años;
	}

	public Integer getAñoSeleccionado() {
		return añoSeleccionado;
	}

	public void setAñoSeleccionado(Integer añoSeleccionado) {
		this.añoSeleccionado = añoSeleccionado;
		this.generarTodosLosCBox(this.nombreSeleccionado, this.añoSeleccionado,
				this.semestreSeleccionado);
	}

	public List<Integer> getSemestre() {
		return semestre;
	}

	public void setSemestre(List<Integer> semestre) {
		this.semestre = semestre;
	}

	public Integer getSemestreSeleccionado() {
		return semestreSeleccionado;
	}

	public void setSemestreSeleccionado(Integer semestreSeleccionado) {
		this.semestreSeleccionado = semestreSeleccionado;
		this.generarTodosLosCBox(this.nombreSeleccionado, this.añoSeleccionado,
				this.semestreSeleccionado);
	}

	/********* METODOS *********/

	public ConsultarIndicadorViewM(Indicador unIndicador) {

		this.indicadorElegido = unIndicador;
		this.generarTodosLosCBox(null, null, null);

	}

	public void generarTodosLosCBox(String empresa, Integer anio,
			Integer semestre) {

		List<Empresa> repoEmpresa2 = new ArrayList<Empresa>();
		repoEmpresa2 = this.getRepoEmpresas().filtrar(null, empresa, semestre,
				anio);

		generarCBoxNombresEmpresas(repoEmpresa2);
		generarCBoxAnios(repoEmpresa2);
		generarCBoxSemestre(repoEmpresa2);

	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}

	public void generarCBoxAnios(List<Empresa> empresas) {

		HashSet<Integer> cantidadAnios = new HashSet<Integer>();
		empresas.forEach(empresa -> {
			empresa.getPeriodos().forEach(periodo -> {
				cantidadAnios.add(periodo.getAño());
			});
		});
		ArrayList<Integer> CantidadesAniosFinal = new ArrayList<Integer>(
				cantidadAnios);
		Collections.sort(CantidadesAniosFinal);

		this.años = CantidadesAniosFinal;
	}

	public void generarCBoxSemestre(List<Empresa> empresas) {

		HashSet<Integer> cantidadSemestres = new HashSet<Integer>();
		empresas.forEach(empresa -> {
			empresa.getPeriodos().forEach(periodo -> {
				cantidadSemestres.add(periodo.getSemestre());
			});
		});
		ArrayList<Integer> cantidadSemestresFinal = new ArrayList<Integer>(
				cantidadSemestres);
		Collections.sort(cantidadSemestresFinal);

		this.semestre = cantidadSemestresFinal;
	}

	public void generarCBoxNombresEmpresas(List<Empresa> empresas) {

		HashSet<String> nombreEmpresas = new HashSet<String>();
		empresas.forEach(name -> (nombreEmpresas.add(name.getNombre())));
		ArrayList<String> nombreEmpresasFinal = new ArrayList<String>(
				nombreEmpresas);
		Collections.sort(nombreEmpresasFinal);
		this.nombres = nombreEmpresasFinal;

		// nombreEmpresasFinal.forEach(name ->(System.out.println(name)));

	}

	public void consultar() throws Exception {
		List<Empresa> empresas = this.getRepoEmpresas().filtrar(null,
				nombreSeleccionado, semestreSeleccionado, añoSeleccionado);

		List<Cuenta> cuentas = empresas.get(0).getPeriodos().get(0)
				.getCuentas();

		this.resultado = getIndicadorElegido().analizarResultado(cuentas);
	}

	public void reiniciar() {
		this.generarTodosLosCBox(null, null, null);
		this.snapshotIndicadorSeleccionado = null;
		this.limpiarFiltros();
		this.resultado = 0;
	}

	public void limpiarFiltros() {
		nombreSeleccionado = null;
		semestreSeleccionado = null;
		añoSeleccionado = null;
	}
}
