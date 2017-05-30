package viewmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import model.Empresa;
import model.Indicador;
import model.SnapshotIndicador;
import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;
import repositories.RepositorioEmpresa;

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

	public ConsultarIndicadorViewM() {

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

	}

	public void reiniciar() {
		this.generarTodosLosCBox(null, null, null);
		this.snapshotIndicadorSeleccionado = null;
		this.limpiarFiltros();
		this.llenarTablas();
	}

	public void limpiarFiltros() {
		nombreSeleccionado = null;
		semestreSeleccionado = null;
		añoSeleccionado = null;
	}

	public void llenarTablas() {
		this.setSnapshotIndicadores(this
				.dameSnapshotIndicadores(getRepoEmpresas().allInstances()));
	}

	// CREA FILAS DE LA TABLA
	private List<SnapshotIndicador> dameSnapshotIndicadores(
			List<Empresa> empresasASnap) {
		ArrayList<SnapshotIndicador> listSnapshot = new ArrayList<SnapshotIndicador>();
		empresasASnap.forEach(empresa -> {
			empresa.getPeriodos().forEach(periodo -> {
				SnapshotIndicador snapshotIndicador = new SnapshotIndicador();
					snapshotIndicador.setNombre(empresa.getNombre());
					snapshotIndicador.setSemestre(periodo.getSemestre());
					snapshotIndicador.setAño(periodo.getAño());
					snapshotIndicador.setResultado(snapshotIndicador
							.analizarResultado(getIndicadorElegido(), periodo.getCuentas()));

					listSnapshot.add(snapshotIndicador);

				});
		});
		return listSnapshot;
	}
}
