package viewmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import model.RepositorioMaestro;
import model.SnapshotEmpresa;

import org.uqbar.commons.utils.Observable;


@Observable
public class EmpresaViewM {
	
	private String cuentaSeleccionada;
	private Collection<String> cuentas = new ArrayList<String>();
	private Collection<String> nombres = new ArrayList<String>();	
	private String nombreSeleccionado;
	private Collection<Integer> años = new ArrayList<Integer>();	
	private Integer añoSeleccionado ;
    private Collection<Integer> semestre = new ArrayList<Integer>();	
	private Integer semestreSeleccionado ;
	private Collection<SnapshotEmpresa> snapshotEmpresas;
	private SnapshotEmpresa snapshotEmpresaSeleccionada;
	private SnapshotEmpresa filtro = new SnapshotEmpresa();
	
/***************Comentemos el codigo..please!***************************/
	
	public EmpresaViewM(){
//		generarPeridos();
//		generarCuentas();
		generarNombres();
	}	
	
	public boolean condicionFiltrado(SnapshotEmpresa snapshot){
		filtro.setCuenta((cuentaSeleccionada == null) ? snapshot.getCuenta() : cuentaSeleccionada); 
		filtro.setNombre((nombreSeleccionado == null) ? snapshot.getNombre() : nombreSeleccionado); 
		filtro.setSemestre((semestreSeleccionado == null) ? snapshot.getSemestre() : semestreSeleccionado); 
		filtro.setAño((añoSeleccionado == null) ? snapshot.getAño() : añoSeleccionado); 
		return snapshot.equals(filtro);
	}
	
	public void limpiarFiltros(){
		cuentaSeleccionada = null;
		nombreSeleccionado = null;
		semestreSeleccionado = null;
		añoSeleccionado = null;
		
	}
	
	public void filtrar(){
		this.llenarTablas();
		Collection<SnapshotEmpresa> snapshotFiltrado = this.snapshotEmpresas
			.stream()
			.filter( snapshot -> condicionFiltrado(snapshot))
			.collect( Collectors.toList());
		setSnapshotEmpresas(snapshotFiltrado);
	}
	
	public void llenarTablas(){
		this.setSnapshotEmpresas(RepositorioMaestro.dameSnapshotEmpresas());
	}


	public void reiniciar(){
		this.limpiarFiltros();
		this.llenarTablas();
		this.snapshotEmpresaSeleccionada= null ;
		this.años= null ;
		this.cuentas= null ;
		this.semestre=null ;
		this.generarNombres();
	}
	
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
		//Seleccionamos un nombre de una empresa y deberia buscar las cuentas de esa empresa
		//parametro :nombreempresa
		this.generarCuentas();
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

		//Seleccionamos un nombre de una empresa,cuenta y deberia buscar los anios 
		//parametros :nombreempresa :nombrecuenta
		this.generarAnios();
	}
	
	public Integer getAñoSeleccionado() {
		return añoSeleccionado;
	}

	public void setAñoSeleccionado(Integer añoSeleccionado) {
		this.añoSeleccionado = añoSeleccionado;
		
		//Seleccionamos un nombre de una empresa, cuenta,anio y deberia buscar los semestres  
		//parametros :nombreempresa :nombrecuenta :anio		
		semestre.add(1);
		semestre.add(2);
	}

	public Collection<Integer> getSemestre() {
		return semestre;
	}

	public void setSemestre(Collection<Integer> semestre) {
		this.semestre = semestre;
	}

	public Integer getSemestreSeleccionado() {
		return semestreSeleccionado;
	}

	public void setSemestreSeleccionado(Integer semestreSeleccionado) {
		this.semestreSeleccionado = semestreSeleccionado;
	}
	

	public void generarAnios() {
		
		this.años = RepositorioMaestro.dameAniosPeriodos();
	}
	
	public void generarCuentas(){
		this.cuentas= RepositorioMaestro.dameCuentasEmpresas();
	}
	 
	public void generarNombres(){
		this.nombres= RepositorioMaestro.dameNombresEmpresas(); 
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
	

}
