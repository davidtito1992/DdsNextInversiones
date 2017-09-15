package model;

import java.time.Year;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;

@Entity
@Table(name= "Periodo")
@Observable
public class Periodo {

	/********* ATRIBUTOS *********/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "periodoId")
	private Long periodoId;
	
	//@MapsId("empresaId")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	@Column(name = "anio")
	private Year anio;
	
	@Column(name = "semestre")
	private int semestre;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "periodo", cascade = CascadeType.ALL)
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
