package main.cache;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

import model.User;

@Entity
@Table(name = "CacheIndicador")
@Transactional
public class CacheIndicador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cacheIndicadorId;

	private long idUser;

	private String registroIndicador;
	private String nombreEmpresa;
	private int anio;
	private int semestre;
	private long calculo;

	public CacheIndicador() {
	}

	public CacheIndicador(long idUser, String registroIndicador,
			String nombreEmpresa, int anio, int semestre, long calculo) {
		this.idUser = idUser;
		this.registroIndicador = registroIndicador;
		this.nombreEmpresa = nombreEmpresa;
		this.anio = anio;
		this.semestre = semestre;
		this.calculo = calculo;

	}

	public Long getCacheIndicadorId() {
		return cacheIndicadorId;
	}

	public void setCacheIndicadorId(Long cacheIndicadorId) {
		this.cacheIndicadorId = cacheIndicadorId;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getRegistroIndicador() {
		return registroIndicador;
	}

	public void setRegistroIndicador(String registroIndicador) {
		this.registroIndicador = registroIndicador;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public long getCalculo() {
		return calculo;
	}

	public void setCalculo(long calculo) {
		this.calculo = calculo;
	}

}