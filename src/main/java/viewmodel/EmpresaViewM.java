package viewmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import model.Empresa;
import model.SnapshotEmpresa;

import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;

import repositories.RepositorioEmpresa;

@Observable
public class EmpresaViewM {

	/********* ATRIBUTOS *********/

	private String cuentaSeleccionada;
	private Collection<String> cuentas = new ArrayList<String>();
	private Collection<String> nombres = new ArrayList<String>();
	private String nombreSeleccionado;
	private Collection<Integer> años = new ArrayList<Integer>();
	private Integer añoSeleccionado;
	private Collection<Integer> semestre = new ArrayList<Integer>();
	private Integer semestreSeleccionado;
	private Collection<SnapshotEmpresa> snapshotEmpresas;
	private SnapshotEmpresa snapshotEmpresaSeleccionada;
	private SnapshotEmpresa filtro = new SnapshotEmpresa();

	/********* GETTERS/SETTERS *********/

	public Collection<Integer> getAños() {
		return años;
	}

	public void setAños(Collection<Integer> años) {
		this.años = años;
	}

	public String getNombreSeleccionado() {
		return nombreSeleccionado;
	}

	public void setNombreSeleccionado(String nombreSeleccionado) {
		this.nombreSeleccionado = nombreSeleccionado;
		// Seleccionamos un nombreempresa y si la cuenta==null cargar en base a lo demas seleccionado 
		//sino lo dejamos como esta
		//si la anio==null cargar en base a lo demas seleccionado 
		// sino lo dejamos como esta
		//si la semestre==null cargar en base a lo demas seleccionado 
		// sino lo dejamos como esta
	}

	public String getCuentaSeleccionada() {

		return cuentaSeleccionada;
	}

	public Collection<String> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Collection<String> cuentas) {

		this.cuentas = cuentas;
	}

	public Collection<String> getNombres() {
		return nombres;
	}

	public void setNombres(Collection<String> nombres) {
		this.nombres = nombres;
	}

	public void setCuentaSeleccionada(String cuentaSeleccionada) {

		this.cuentaSeleccionada = cuentaSeleccionada;

		// Seleccionamos un nombredecuenta y si la empresa==null cargar en base a lo demas seleccionado 
		//sino lo dejamos como esta
		//si la anio==null cargar en base a lo demas seleccionado 
		// sino lo dejamos como esta
		//si la semestre==null cargar en base a lo demas seleccionado 
		// sino lo dejamos como esta
	}

	public Integer getAñoSeleccionado() {
		return añoSeleccionado;
	}

	public void setAñoSeleccionado(Integer añoSeleccionado) {
		this.añoSeleccionado = añoSeleccionado;

		// Seleccionamos un anio y si la empresa==null cargar en base a lo demas seleccionado 
		//sino lo dejamos como esta
		//si la cuenta==null cargar en base a lo demas seleccionado 
		// sino lo dejamos como esta
		//si la semestre==null cargar en base a lo demas seleccionado 
		// sino lo dejamos como esta
		
		// semestre.add(1);
		// semestre.add(2);
	}

	public Collection<Integer> getSemestre() {
		return semestre;
	}

	public void setSemestre(Collection<Integer> semestre) {
		this.semestre = semestre;
	}

	public Integer getSemestreSeleccionado() {
		return semestreSeleccionado;
		// Seleccionamos un semestre y si la empresa==null cargar en base a lo demas seleccionado 
		//sino lo dejamos como esta
		//si la anio==null cargar en base a lo demas seleccionado 
		// sino lo dejamos como esta
		//si la cuenta==null cargar en base a lo demas seleccionado 
		// sino lo dejamos como esta
	}

	public void setSemestreSeleccionado(Integer semestreSeleccionado) {
		this.semestreSeleccionado = semestreSeleccionado;
	}

	public Collection<SnapshotEmpresa> getSnapshotEmpresas() {
		return snapshotEmpresas;
	}

	public void setSnapshotEmpresas(Collection<SnapshotEmpresa> snapshotEmpresas) {
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
		generarAnios();
		generarCuentas();
		generarNombres();
	}

	public boolean condicionFiltrado(SnapshotEmpresa snapshot) {
		filtro.setCuenta((cuentaSeleccionada == null) ? snapshot.getCuenta()
				: cuentaSeleccionada);
		filtro.setNombre((nombreSeleccionado == null) ? snapshot.getNombre()
				: nombreSeleccionado);
		filtro.setSemestre((semestreSeleccionado == null) ? snapshot
				.getSemestre() : semestreSeleccionado);
		filtro.setAño((añoSeleccionado == null) ? snapshot.getAño()
				: añoSeleccionado);
		return snapshot.equals(filtro);
	}

	public void limpiarFiltros() {
		cuentaSeleccionada = null;
		nombreSeleccionado = null;
		semestreSeleccionado = null;
		añoSeleccionado = null;
	}

	public void filtrar() {
		this.llenarTablas();
		Collection<SnapshotEmpresa> snapshotFiltrado = this.snapshotEmpresas
				.stream().filter(snapshot -> condicionFiltrado(snapshot))
				.collect(Collectors.toList());
		setSnapshotEmpresas(snapshotFiltrado);
	}

	public void llenarTablas() {
		this.setSnapshotEmpresas(this.dameSnapshotEmpresas());
	}

	public void reiniciar() {
		this.limpiarFiltros();
		this.llenarTablas();
		this.snapshotEmpresaSeleccionada = null;
		// this.años= null ;
		// this.cuentas= null ;
		// this.semestre=null ;
		// this.generarNombres();
	}

	public void generarAnios() {
		this.años = getRepoEmpresas().dameAniosPeriodos();
		semestre.add(1);
		semestre.add(2);
	}

	public void generarCuentas() {
		this.cuentas = getRepoEmpresas().dameCuentasEmpresas();
	}

	public void generarNombres() {
		this.nombres = getRepoEmpresas().dameNombresEmpresas();
	}
	

	public RepositorioEmpresa getRepoEmpresas() {
		return (RepositorioEmpresa) ApplicationContext.getInstance().getSingleton(Empresa.class);
	}
	
	public ArrayList<SnapshotEmpresa> dameSnapshotEmpresas() {
		ArrayList<SnapshotEmpresa> listSnapshot = new ArrayList<SnapshotEmpresa>();
		getRepoEmpresas().allInstances().forEach(empresa -> {
			empresa.getPeriodos().forEach(periodo -> {
				periodo.getCuentas().forEach(cuenta -> {
					SnapshotEmpresa snapshotempresa = new SnapshotEmpresa();
					snapshotempresa.setCuenta(cuenta.getNombre());
					snapshotempresa.setValor(cuenta.getValor());
					snapshotempresa.setNombre(empresa.getNombre());
					snapshotempresa.setSemestre(periodo.getSemestre());
					snapshotempresa.setAño(periodo.getAño());
					listSnapshot.add(snapshotempresa);
				});
			});
		});
		return listSnapshot;
	}

}
