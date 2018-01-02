package model;

import java.math.BigDecimal;
import java.time.Year;

public class CacheIndicador {

	private long idUser;
	private String nombreIndicador;
	private String nombreEmpresa;
	private Year anio;
	private int semestre;

	public CacheIndicador() {
	}

	public CacheIndicador(long idUser, String nombreIndicador,
			String nombreEmpresa, Year anio, int semestre) {
		this.idUser = idUser;
		this.nombreIndicador = nombreIndicador;
		this.nombreEmpresa = nombreEmpresa;
		this.anio = anio;
		this.semestre = semestre;
	}

	public String getCampoCalculo() {
		return "calculo";
	}

}
