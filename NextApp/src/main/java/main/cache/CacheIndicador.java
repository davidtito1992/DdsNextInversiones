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

	/*
	 * @ManyToOne(fetch= FetchType.LAZY)
	 * 
	 * @JoinColumn private Periodo periodo;
	 * 
	 * @ManyToOne(fetch= FetchType.LAZY)
	 * 
	 * @JoinColumn private RegistroIndicador indicador;
	 */

	public CacheIndicador() {
	}

	public CacheIndicador(long idUser, String registroIndicador,
			String nombreEmpresa, int anio, int semestre, long calculo) {
		this.idUser = idUser;
		this.registroIndicador = registroIndicador;
		this.nombreEmpresa = nombreEmpresa;
		this.anio= anio ;
		this.semestre= semestre ;
		this.calculo= calculo ;
		
	}

}