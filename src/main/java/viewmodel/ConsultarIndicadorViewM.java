package viewmodel;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import model.Cuenta;
import model.Empresa;
import model.Indicador;
import model.SnapshotIndicador;

import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;

import java.math.BigDecimal;

import formulaTeam.CalculoFormula;
import repositories.RepositorioEmpresa;

@Observable
public class ConsultarIndicadorViewM {

	/********* ATRIBUTOS *********/

	private List<String> nombres = new ArrayList<String>();
	private String nombreSeleccionado;
	private List<Year> anios = new ArrayList<>();
	private Year anioSeleccionado;
	private List<Integer> semestre = new ArrayList<Integer>();
	private Integer semestreSeleccionado;
	private List<SnapshotIndicador> snapshotIndicadores;
	private SnapshotIndicador snapshotIndicadorSeleccionado;
	private BigDecimal resultado;
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

	public BigDecimal getResultado() {
		return resultado;
	}

	public void setResultado(BigDecimal resultado) {
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
		this.generarTodosLosCBox(this.nombreSeleccionado, this.anioSeleccionado,
				this.semestreSeleccionado);
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
		this.generarTodosLosCBox(this.nombreSeleccionado, this.anioSeleccionado,
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
		this.generarTodosLosCBox(this.nombreSeleccionado, this.anioSeleccionado,
				this.semestreSeleccionado);
	}

	/********* METODOS *********/

	public ConsultarIndicadorViewM(Indicador unIndicador) {

		this.indicadorElegido = unIndicador;
		this.generarTodosLosCBox(null, null, null);

	}

	public void generarTodosLosCBox(String empresa, Year anio,
			Integer semestre) {

		List<Empresa> repoEmpresaFiltrado = new ArrayList<Empresa>();
		repoEmpresaFiltrado = this.getRepoEmpresas().filtrar(null, empresa,
				semestre, anio);

		generarCBoxNombresEmpresas(repoEmpresaFiltrado);
		generarCBoxAnios(repoEmpresaFiltrado);
		generarCBoxSemestre(repoEmpresaFiltrado);

	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}

	public void generarCBoxAnios(List<Empresa> empresas) {

		this.anios = this.getRepoEmpresas().todosLosAnios(empresas);

	}

	public void generarCBoxSemestre(List<Empresa> empresas) {

		this.semestre = this.getRepoEmpresas().todosLosPeriodos(empresas);

	}

	public void generarCBoxNombresEmpresas(List<Empresa> empresas) {

		this.nombres = this.getRepoEmpresas().todosLosNombresDeEmpresas(
				empresas);

	}

	public void consultar() throws Exception {

		List<Cuenta> cuentas = getRepoEmpresas().obtenerCuentas(
				nombreSeleccionado, semestreSeleccionado, anioSeleccionado);

		CalculoFormula calculoFormula = new CalculoFormula();
		this.resultado = calculoFormula.analizarResultado(
				getIndicadorElegido(), cuentas);
	}

	public void reiniciar() {
		this.generarTodosLosCBox(null, null, null);
		this.snapshotIndicadorSeleccionado = null;
		this.limpiarFiltros();
		this.resultado = new BigDecimal(0);
	}

	public void limpiarFiltros() {
		nombreSeleccionado = null;
		semestreSeleccionado = null;
		anioSeleccionado = null;
	}
}
