package viewmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import model.RepositorioMaestro;
import model.SnapshotEmpresa;
import app.Mixin;

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
/***************A modo de prueba***************************/
	
	public EmpresaViewM(){
		generarPeridos();
		generarCuentas();
		generarNombres();
		setCuentaSeleccionada((String) cuentas.toArray()[0]);
		setNombreSeleccionado((String) nombres.toArray()[0]);
		setAñoSeleccionado((Integer) años.toArray()[0]);
		setSemestreSeleccionado((Integer) semestre.toArray()[0]);
	}	
	
	public boolean condicionFiltrado(SnapshotEmpresa snapshot){
		SnapshotEmpresa filtro = new SnapshotEmpresa();
		filtro.setCuenta(cuentaSeleccionada);
		filtro.setNombre(nombreSeleccionado);
		filtro.setSemestre(semestreSeleccionado);
		filtro.setAño(añoSeleccionado);
		return snapshot.equals(filtro);
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
		this.setSnapshotEmpresas(Mixin.llenarTabla());
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
	}
	
	public Integer getAñoSeleccionado() {
		return añoSeleccionado;
	}

	public void setAñoSeleccionado(Integer añoSeleccionado) {
		this.añoSeleccionado = añoSeleccionado;
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
	

	public void generarPeridos() {
		semestre.add(1);
		semestre.add(2);
		for (Integer anio = 2010; anio <= 2017; anio++) {
			años.add(anio);
			// System.out.printf("fecha: " + fechas.get(i).toString()+ "\n");
			System.out.printf("año: " + anio + "\n");
		}
	}
	
	public void generarCuentas(){
		cuentas.add("EBITDA");
		cuentas.add("FDS");
		cuentas.add("FreeCashFlow");
		cuentas.add("Neto Discontinuas");
		cuentas.add("Neto Continuas");
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
	
	
	
	
//
//    private Collection<java.sql.Date> fechas ;	
//	private java.sql.Date fechaSeleccionada ;
//	
//	public java.sql.Date getFechaSeleccionada() {
//		return fechaSeleccionada;
//	}
//	public void setFechaSeleccionada(java.sql.Date fechaSeleccionada) {
//		this.fechaSeleccionada = fechaSeleccionada;
//	}
//	public Collection<java.sql.Date> getFechas() {
//		return fechas;
//	}
//	public void setFechas(Collection<java.sql.Date> fechas) {
//		this.fechas = fechas;
//	}
//

//
//	public void generarfechas(){
//		java.sql.Date ahora = new java.sql.Date(2016-00-01);
//				//Calendar.getInstance().getTimeInMillis());
//		 System.out.printf("fecha: " + ahora.toString()+ "\n");
//
//		 ArrayList<java.sql.Date> fechas2= new ArrayList<java.sql.Date>();
//		 fechas2.add(ahora);
//		 for (int i =1 ;i <= 5; i++){
//			 ahora= sumarFechasDias(ahora,180);
//			 fechas2.add(ahora);
////			 System.out.printf("fecha: " + fechas.get(i).toString()+ "\n");
//			 System.out.printf("fecha: " + ahora.toString()+ "\n");
//		 
//		 }
//		 
//		 this.fechas = fechas2 ;
//	}
//	
//	
//	/******hardcode fechas***********/
//	public static java.sql.Date sumarFechasDias(java.sql.Date fch, int dias) {
//        Calendar cal = new GregorianCalendar();
//        cal.setTimeInMillis(fch.getTime());
//        cal.add(Calendar.DATE, dias);
//        return new java.sql.Date(cal.getTimeInMillis());
//    }

	
}
