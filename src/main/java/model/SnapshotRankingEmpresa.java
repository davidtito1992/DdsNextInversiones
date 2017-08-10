package model;
import org.uqbar.commons.utils.Observable;

@Observable
public class SnapshotRankingEmpresa {
	
	/********* ATRIBUTOS *********/
	
	private String nombreEmpresa;
	private String observacion;
	
	/********* GETTERS/SETTERS *********/
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
}
