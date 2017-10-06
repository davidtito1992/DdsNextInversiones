package model;

import java.time.Year;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.uqbar.commons.utils.Observable;

import converter.YearConverter;

@Entity
@Table(name = "Periodos")
@Observable
public class Periodo {

	/********* ATRIBUTOS *********/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long periodoId;

	@Convert(converter = YearConverter.class)
	private Year anio;

	private int semestre;

	@JoinColumn
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	private List<Cuenta> cuentas;

	/********* GETTERS/SETTERS *********/

	public Periodo() {
	}

	public Long getPeriodoId() {
		return periodoId;
	}

	public void setPeriodoId(Long periodoId) {
		this.periodoId = periodoId;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public Year getAnio() {
		return anio;
	}

	public void setAnio(Year anio) {
		this.anio = anio;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
}
