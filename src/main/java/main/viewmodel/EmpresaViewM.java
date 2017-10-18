package main.viewmodel;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import main.app.AplicacionContexto;
import main.repositories.RepositorioEmpresa;
import model.Cuenta;
import model.Empresa;
import model.Periodo;
import model.SnapshotEmpresa;

import org.uqbar.commons.utils.Observable;

@Observable
public class EmpresaViewM {

	/********* ATRIBUTOS *********/

	private String cuentaSeleccionada;
	private List<String> cuentas = new ArrayList<String>();
	private List<String> nombres = new ArrayList<String>();
	private String nombreSeleccionado;
	private List<Year> anios = new ArrayList<>();
	private Year anioSeleccionado;
	private List<Integer> semestre = new ArrayList<Integer>();
	private Integer semestreSeleccionado;
	private List<SnapshotEmpresa> snapshotEmpresas;
	private SnapshotEmpresa snapshotEmpresaSeleccionada;

	/********* GETTERS/SETTERS *********/

	public List<Year> getAnios() {
		return anios;
	}

	public void setAnios(List<Year> anios) {
		this.anios = anios;
	}

	public String getNombreSeleccionado() {
		return nombreSeleccionado;
	}

	public void setNombreSeleccionado(String nombreSeleccionado) {

		this.nombreSeleccionado = nombreSeleccionado;
		this.generarTodosLosCBox(this.nombreSeleccionado,
				this.cuentaSeleccionada, this.anioSeleccionado,
				this.semestreSeleccionado);

	}

	public String getCuentaSeleccionada() {

		return cuentaSeleccionada;
	}

	public List<String> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<String> cuentas) {

		this.cuentas = cuentas;
	}

	public List<String> getNombres() {
		return nombres;
	}

	public void setNombres(List<String> nombres) {
		this.nombres = nombres;
	}

	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
		this.generarTodosLosCBox(this.nombreSeleccionado,
				this.cuentaSeleccionada, this.anioSeleccionado,
				this.semestreSeleccionado);
	}

	public Year getAnioSeleccionado() {
		return anioSeleccionado;
	}

	public void setAnioSeleccionado(Year anioSeleccionado) {
		this.anioSeleccionado = anioSeleccionado;

		this.generarTodosLosCBox(this.nombreSeleccionado,
				this.cuentaSeleccionada, this.anioSeleccionado,
				this.semestreSeleccionado);

	}

	public void setSemestreSeleccionado(Integer semestreSeleccionado) {
		this.semestreSeleccionado = semestreSeleccionado;

		this.generarTodosLosCBox(this.nombreSeleccionado,
				this.cuentaSeleccionada, this.anioSeleccionado,
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

	public List<SnapshotEmpresa> getSnapshotEmpresas() {
		return snapshotEmpresas;
	}

	public void setSnapshotEmpresas(List<SnapshotEmpresa> snapshotEmpresas) {
		this.snapshotEmpresas = snapshotEmpresas;
	}

	public SnapshotEmpresa getSnapshotEmpresaSeleccionada() {
		return snapshotEmpresaSeleccionada;
	}

	public void setSnapshotEmpresaSeleccionada(
			SnapshotEmpresa snapshotEmpresaSeleccionada) {
		this.snapshotEmpresaSeleccionada = snapshotEmpresaSeleccionada;
	}

	/********* METODOS *********/

	public EmpresaViewM() {

		this.generarTodosLosCBox(null, null, null, null);
		this.llenarTablas();
	}
	
	public EmpresaViewM(String empresa, String cuenta, Year anio, Integer periodo) {

		this.nombreSeleccionado = empresa;
		this.cuentaSeleccionada = cuenta;
		this.anioSeleccionado = anio;
		this.semestreSeleccionado = periodo;
		this.generarTodosLosCBox(null, null, null, null);
		this.llenarTablas();
	}

	public void llenarTablas() {
		this.setSnapshotEmpresas(this
				.dameSnapshotEmpresas(getRepositorioEmpresas().allInstances()));
	}

	public void reiniciar() {
		this.limpiarFiltros();
		this.generarTodosLosCBox(null, null, null, null);
		this.llenarTablas();
		this.snapshotEmpresaSeleccionada = null;
	}

	// Genera todos los combobox en base a la seleccion de cada uno de ellos
	public void generarTodosLosCBox(String empresa, String cuenta, Year anio,
			Integer semestre) {

		List<Empresa> repoEmpresa2 = new ArrayList<Empresa>();
		repoEmpresa2 = this.getRepositorioEmpresas().filtrar(cuenta, empresa,
				semestre, anio, null);

		generarCBoxNombresEmpresas(repoEmpresa2);
		generarCBoxCuentas(repoEmpresa2);
		generarCBoxAnios(repoEmpresa2);
		generarCBoxSemestre(repoEmpresa2);

	}

	public void generarCBoxAnios(List<Empresa> empresas) {
		this.anios = this.getRepositorioEmpresas().todosLosAnios(empresas);
	}

	public void generarCBoxSemestre(List<Empresa> empresas) {
		this.semestre = this.getRepositorioEmpresas()
				.todosLosPeriodos(empresas);
	}

	public void generarCBoxCuentas(List<Empresa> empresas) {
		this.cuentas = this.getRepositorioEmpresas().todosLosNombresDeCuentas(
				empresas);
	}

	public void generarCBoxNombresEmpresas(List<Empresa> empresas) {
		this.nombres = this.getRepositorioEmpresas().todosLosNombresDeEmpresas(
				empresas);
	}

	public RepositorioEmpresa getRepositorioEmpresas() {
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}

	public void limpiarFiltros() {
		cuentaSeleccionada = null;
		nombreSeleccionado = null;
		semestreSeleccionado = null;
		anioSeleccionado = null;
	}

	public void filtrar() {
		ArrayList<SnapshotEmpresa> empresitas = (this
				.dameSnapshotEmpresas(getRepositorioEmpresas().filtrar(
						cuentaSeleccionada, nombreSeleccionado,
						semestreSeleccionado, anioSeleccionado, null)));
		this.setSnapshotEmpresas(empresitas);
	}

	// TRANSFORMA EMPRESA EN SNAPSHOT, MOSTRANDO SOLO SNAPSHOTS QUE INTERESAN
	public ArrayList<SnapshotEmpresa> dameSnapshotEmpresas(
			List<Empresa> empresasASnap) {
		ArrayList<SnapshotEmpresa> listSnapshot = new ArrayList<SnapshotEmpresa>();
		empresasASnap.forEach(empresa -> {
			empresa.getPeriodos().forEach(periodo -> {
				periodo.getCuentas().forEach(cuenta -> {
					SnapshotEmpresa snapshotempresa = new SnapshotEmpresa();
					snapshotempresa.setCuenta(cuenta.getNombre());
					snapshotempresa.setValor(cuenta.getValor());
					snapshotempresa.setNombre(empresa.getNombre());
					snapshotempresa.setSemestre(periodo.getSemestre());
					snapshotempresa.setAnio(periodo.getAnio());

					if (agregarALista(empresa, periodo, cuenta)) {
						listSnapshot.add(snapshotempresa);
					}
				});
			});
		});
		return listSnapshot;
	}

	// FILTRA EMPRESA PARA NO MOSTRAR TODOS SUS DATOS SEGUN FILTROS
	private boolean agregarALista(Empresa empresa, Periodo periodo,
			Cuenta cuenta) {
		boolean agregarALista = true;

		if (nombreSeleccionado == null) {
			agregarALista = agregarALista && true;
		} else {
			agregarALista = agregarALista
					&& empresa.getNombre().equals(nombreSeleccionado);
		}

		if (anioSeleccionado == null) {
			agregarALista = agregarALista && true;
		} else {
			agregarALista = agregarALista
					&& periodo.getAnio() == anioSeleccionado;
		}

		if (semestreSeleccionado == null) {
			agregarALista = agregarALista && true;
		} else {
			agregarALista = agregarALista
					&& periodo.getSemestre() == semestreSeleccionado;
		}

		if (cuentaSeleccionada == null) {
			agregarALista = agregarALista && true;
		} else {
			agregarALista = agregarALista
					&& cuenta.getNombre().equals(cuentaSeleccionada);
		}

		return agregarALista;
	}

}
