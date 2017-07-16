package modelFilter;

import java.time.Year;

public class EmpresaDataFilter {
	
	private String cuentaSeleccionada;
	private String nombreSeleccionado;
	private Integer semestreSeleccionado;
	private Year anioSeleccionado;
	
	public EmpresaDataFilter(String cuentaSeleccionada, String nombreSeleccionado,Integer semestreSeleccionado, Year anioSeleccionado){
		this.cuentaSeleccionada = cuentaSeleccionada;
		this.nombreSeleccionado = nombreSeleccionado;
		this.semestreSeleccionado = semestreSeleccionado;
		this.anioSeleccionado = anioSeleccionado;
	}

	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	public String getNombreSeleccionado() {
		return nombreSeleccionado;
	}

	public void setNombreSeleccionado(String nombreSeleccionado) {
		this.nombreSeleccionado = nombreSeleccionado;
	}

	public Integer getSemestreSeleccionado() {
		return semestreSeleccionado;
	}

	public void setSemestreSeleccionado(Integer semestreSeleccionado) {
		this.semestreSeleccionado = semestreSeleccionado;
	}

	public Year getAnioSeleccionado() {
		return anioSeleccionado;
	}

	public void setAnioSeleccionado(Year anioSeleccionado) {
		this.anioSeleccionado = anioSeleccionado;
	}
	
	

}
