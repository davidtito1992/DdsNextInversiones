package model;

import org.uqbar.commons.utils.Observable;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "Cuenta")
@Observable
public class Cuenta {

	/********* ATRIBUTOS *********/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cuentaId")
	private Long cuentaId;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "valor")
	private BigDecimal valor;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "periodo_id")
	private Periodo periodo;

	/********* GETTERS/SETTERS *********/

	public Cuenta() {
	}

	public Long getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(Long cuentaId) {
		this.cuentaId = cuentaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	/********* METODOS *********/

	public Cuenta(String nombre, BigDecimal valor) {
		this.nombre = nombre;
		this.valor = valor;
	}

}
