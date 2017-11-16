package main.viewmodel;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import main.app.AplicacionContexto;
import main.app.DslIndicador;
import main.repositories.RepositorioEmpresa;
import main.repositories.RepositorioIndicador;
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

	public List<Year> getAnios() {
		return anios;
	}

	public void setAnios(List<Year> anios) {
		this.anios = anios;
	}

	public Year getAnioSeleccionado() {
		return anioSeleccionado;
	}

	/********* METODOS *********/

	public RepositorioEmpresa getRepositorioEmpresas() {
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}

	public List<SnapshotIndicador> allSnapshotIndicadores(Long idUser) {

		List<Empresa> empresas = getRepositorioEmpresas().allInstancesUser(idUser);
		List<RegistroIndicador> indicadores = getRepositorioIndicadores()
				.allInstancesUser(idUser);
		List<SnapshotIndicador> snapshots = new ArrayList<SnapshotIndicador>();

		indicadores.forEach(indicador -> snapshots
				.addAll(resultadosIndicadores(indicador, empresas)));

		return snapshots.stream().distinct().collect(Collectors.toList());
	}

	public RepositorioIndicador getRepositorioIndicadores() {
		return AplicacionContexto.getInstance().getInstanceRepoIndicadores();
	}

	public List<SnapshotIndicador> resultadosIndicadores(
			RegistroIndicador indicador, List<Empresa> listaDeEmpresas) {

		List<SnapshotIndicador> listSnapshot = listaDeEmpresas
				.stream()
				.map(empresa -> empresa
						.getPeriodos()
						.stream()
						.map(periodo -> {
							return crearSnapshotIndicador(indicador,
									empresa.getNombre(), periodo.getAnio(),
									periodo.getSemestre());
						}).collect(Collectors.toList()))
				.flatMap(listaSnap -> listaSnap.stream())
				.collect(Collectors.toList());

		return listSnapshot;
	}

	public SnapshotIndicador crearSnapshotIndicador(
			RegistroIndicador indicador, String nombreEmpresa, Year anio,
			int semestre) {

		String resultado;
		try {
			resultado = new DslIndicador()
					.prepararFormula(indicador, nombreEmpresa, anio, semestre)
					.calcular().toPlainString();
		} catch (Exception e) {
			resultado = e.getMessage();
		}

		SnapshotIndicador snapshotIndicador = new SnapshotIndicador(indicador,
				nombreEmpresa, anio.getValue(), semestre, resultado);
		return snapshotIndicador;
	}
}
