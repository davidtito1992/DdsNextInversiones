package viewmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import model.Cuenta;
import model.Empresa;
import model.Periodo;
import model.SnapshotEmpresa;
import org.uqbar.commons.utils.Observable;
import repositories.RepositorioEmpresa;

@Observable
public class EmpresaViewM {

	/********* ATRIBUTOS *********/

	private String cuentaSeleccionada;
	private List<String> cuentas = new ArrayList<String>();
	private List<String> nombres = new ArrayList<String>();
	private String nombreSeleccionado;
	private List<Integer> años = new ArrayList<Integer>();
	private Integer añoSeleccionado;
	private List<Integer> semestre = new ArrayList<Integer>();
	private Integer semestreSeleccionado;
	private List<SnapshotEmpresa> snapshotEmpresas;
	private SnapshotEmpresa snapshotEmpresaSeleccionada;
	private RepositorioEmpresa repositorioEmpresa;

	/********* GETTERS/SETTERS *********/

	public List<Integer> getAños() {
		return años;
	}

	public void setAños(List<Integer> años) {
		this.años = años;
	}

	public String getNombreSeleccionado() {
		return nombreSeleccionado;
	}

	// *********** Seleccionamos un nombreempresa y si la cuenta==null cargar en
	// base a lo
	// demas seleccionado
	// sino lo dejamos como esta
	// si la anio==null cargar en base a lo demas seleccionado
	// sino lo dejamos como esta
	// si la semestre==null cargar en base a lo demas seleccionado
	// sino lo dejamos como esta

	public void setNombreSeleccionado(String nombreSeleccionado) {

		this.nombreSeleccionado = nombreSeleccionado;

		this.generarTodosLosCBox(this.nombreSeleccionado,
				this.cuentaSeleccionada, this.añoSeleccionado,
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

	// Seleccionamos un nombredecuenta y si la empresa==null cargar en base a lo
	// demas seleccionado
	// sino lo dejamos como esta
	// si la anio==null cargar en base a lo demas seleccionado
	// sino lo dejamos como esta
	// si la semestre==null cargar en base a lo demas seleccionado
	// sino lo dejamos como esta
	public void setCuentaSeleccionada(String cuentaSeleccionada) {

		this.cuentaSeleccionada = cuentaSeleccionada;

		this.generarTodosLosCBox(this.nombreSeleccionado,
				this.cuentaSeleccionada, this.añoSeleccionado,
				this.semestreSeleccionado);

	}

	public Integer getAñoSeleccionado() {
		return añoSeleccionado;
	}

	// Seleccionamos un anio y si la empresa==null cargar en base a lo demas
	// seleccionado
	// sino lo dejamos como esta
	// si la cuenta==null cargar en base a lo demas seleccionado
	// sino lo dejamos como esta
	// si la semestre==null cargar en base a lo demas seleccionado
	// sino lo dejamos como esta

	public void setAñoSeleccionado(Integer añoSeleccionado) {
		this.añoSeleccionado = añoSeleccionado;

		this.generarTodosLosCBox(this.nombreSeleccionado,
				this.cuentaSeleccionada, this.añoSeleccionado,
				this.semestreSeleccionado);

	}

	public void setSemestreSeleccionado(Integer semestreSeleccionado) {
		this.semestreSeleccionado = semestreSeleccionado;

		this.generarTodosLosCBox(this.nombreSeleccionado,
				this.cuentaSeleccionada, this.añoSeleccionado,
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

	public EmpresaViewM(RepositorioEmpresa repoEmpresa) {

		this.repositorioEmpresa = repoEmpresa;
		this.generarTodosLosCBox(null, null, null, null);

	}

	public void llenarTablas() {
		this.setSnapshotEmpresas(this.dameSnapshotEmpresas(getRepoEmpresas()
				.allInstances()));
	}

	public void reiniciar() {
		this.limpiarFiltros();
		this.generarTodosLosCBox(null, null, null, null);
		this.llenarTablas();
		this.snapshotEmpresaSeleccionada = null;
	}

	// Genera todos los combobox en base a la seleccion de cada uno de ellos
	public void generarTodosLosCBox(String empresa, String cuenta,
			Integer anio, Integer semestre) {

		List<Empresa> repoEmpresa2 = new ArrayList<Empresa>();
		repoEmpresa2 = this.repositorioEmpresa.filtrar(cuenta, empresa,
				semestre, anio);

		generarCBoxNombresEmpresas(repoEmpresa2);
		generarCBoxCuentas(repoEmpresa2);
		generarCBoxAnios(repoEmpresa2);
		generarCBoxSemestre(repoEmpresa2);

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

		// CantidadesAniosFinal.forEach(name ->(System.out.println(name)));
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

		// cantidadSemestresFinal.forEach(name ->(System.out.println(name)));

	}

	public void generarCBoxCuentas(List<Empresa> empresas) {

		HashSet<String> nombreCuentas = new HashSet<String>();
		empresas.forEach(empresa -> {
			empresa.getPeriodos().forEach(
					periodo -> {
						periodo.getCuentas()
								.forEach(
										cuenta -> nombreCuentas.add(cuenta
												.getNombre()));
					});
		});

		ArrayList<String> nombreCuentasfinal = new ArrayList<String>(
				nombreCuentas);
		Collections.sort(nombreCuentasfinal);

		this.cuentas = nombreCuentasfinal;

		// nombreCuentasfinal.forEach(name ->(System.out.println(name)));

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

	public RepositorioEmpresa getRepoEmpresas() {
		return this.repositorioEmpresa;
	}

	public void limpiarFiltros() {
		cuentaSeleccionada = null;
		nombreSeleccionado = null;
		semestreSeleccionado = null;
		añoSeleccionado = null;
	}

	public void filtrar() {
		ArrayList<SnapshotEmpresa> empresitas = (this
				.dameSnapshotEmpresas(getRepoEmpresas().filtrar(
						cuentaSeleccionada, nombreSeleccionado,
						semestreSeleccionado, añoSeleccionado)));
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
					snapshotempresa.setAño(periodo.getAño());

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

		if (añoSeleccionado == null) {
			agregarALista = agregarALista && true;
		} else {
			agregarALista = agregarALista
					&& periodo.getAño() == añoSeleccionado;
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
