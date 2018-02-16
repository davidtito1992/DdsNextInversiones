package model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Cuentas")
public class Cuenta {

	/********* ATRIBUTOS *********/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cuentaId;
	
	private String nombre;
	
	private BigDecimal valor;

	/********* GETTERS/SETTERS *********/

	public Cuenta() {
	}
	
	public Cuenta(String nombre, BigDecimal valor) {
		this.nombre = nombre;
		this.valor = valor;
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

	/********* METODOS *********/


}
