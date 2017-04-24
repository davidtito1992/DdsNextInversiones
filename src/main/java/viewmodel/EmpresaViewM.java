package viewmodel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.uqbar.commons.utils.Observable;

@Observable
public class EmpresaViewM {
	
	private String nombre ;
	private String cuenta ;

    private Collection<Integer> años = new ArrayList<Integer>();	
	private Integer añoSeleccionado ;

    private Collection<Integer> semestre = new ArrayList<Integer>();	
	private Integer semestreSeleccionado ;
/***************A modo de prueba***************************/
	
	public EmpresaViewM(){
		generarPeridos();
	}	
	

	public Collection<Integer> getAños() {
		return años;
	}

	public void setAños(Collection<Integer> años) {
		this.años = años;
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
   
	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public void generarPeridos() {
		semestre.add(1);
		semestre.add(2);
 for (Integer anio=2010;anio <= 2017; anio++){
	 años.add(anio);
//	 System.out.printf("fecha: " + fechas.get(i).toString()+ "\n");
	 System.out.printf("año: " + anio + "\n"); 
 }
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
