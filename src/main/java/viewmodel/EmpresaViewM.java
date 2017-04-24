package viewmodel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.uqbar.commons.utils.Observable;

@Observable
public class EmpresaViewM {
	
    private Collection<java.sql.Date> fechas ;	
	private java.sql.Date fechaSeleccionada ;
	private String nombre ;
	private String cuenta ;
	

/***************A modo de prueba***************************/
	
	public EmpresaViewM(){
		generarfechas();
	}	
	
	public java.sql.Date getFechaSeleccionada() {
		return fechaSeleccionada;
	}
	public void setFechaSeleccionada(java.sql.Date fechaSeleccionada) {
		this.fechaSeleccionada = fechaSeleccionada;
	}
	public Collection<java.sql.Date> getFechas() {
		return fechas;
	}
	public void setFechas(Collection<java.sql.Date> fechas) {
		this.fechas = fechas;
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

	public void generarfechas(){
		java.sql.Date ahora = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		 System.out.printf("fecha: " + ahora.toString()+ "\n");

		 ArrayList<java.sql.Date> fechas2= new ArrayList<java.sql.Date>();
		 fechas2.add(ahora);
		 for (int i =1 ;i <= 5; i++){
			 java.sql.Date otrafecha= sumarFechasDias(ahora,i);
			 fechas2.add(otrafecha);
//			 System.out.printf("fecha: " + fechas.get(i).toString()+ "\n");
			 System.out.printf("fecha: " + otrafecha.toString()+ "\n");
		 
		 }
		 
		 this.fechas = fechas2 ;
	}
	
	
	/******hardcode fechas***********/
	public static java.sql.Date sumarFechasDias(java.sql.Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }

	
}
