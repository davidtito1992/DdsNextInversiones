package model;

import java.time.Year;

import org.uqbar.commons.utils.Observable;

import com.ibm.icu.math.BigDecimal;

@Observable
public class SnapshotEmpresa {

	/********* ATRIBUTOS *********/

	private String nombre;
	private Year año;
	private int semestre;
	private String cuenta;
	private BigDecimal valor;

	/********* GETTERS/SETTERS *********/

	public Year getAño() {
		return año;
	}

	public void setAño(Year año) {
		this.año = año;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/********* METODOS *********/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((año == null) ? 0 : año.hashCode());
		result = prime * result + ((cuenta == null) ? 0 : cuenta.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + semestre;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SnapshotEmpresa other = (SnapshotEmpresa) obj;
		if (año != other.año)
			return false;
		if (cuenta == null) {
			if (other.cuenta != null)
				return false;
		} else if (!cuenta.equals(other.cuenta))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (semestre != other.semestre)
			return false;
		return true;
	}

}
