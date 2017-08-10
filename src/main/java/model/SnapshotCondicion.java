package model;

import java.math.BigDecimal;

import org.uqbar.commons.utils.Observable;

@Observable
public class SnapshotCondicion {

	private String tipoCondicion;
	private String nombreCondicion;
	private String indicador;
	private BigDecimal pesoOComparar;
	private int anios;

	public SnapshotCondicion(String tipo, String nombre, String indicador,
			BigDecimal pesoOComparar, int anios) {
		this.tipoCondicion = tipo;
		this.nombreCondicion = nombre;
		this.indicador = indicador;
		this.pesoOComparar = pesoOComparar;
		this.anios = anios;
	}

	public String getTipoCondicion() {

		return tipoCondicion;
	}

	public void setTipoCondicion(String tipoCondicion) {
		this.tipoCondicion = tipoCondicion;
	}

	public String getNombreCondicion() {
		return nombreCondicion;
	}

	public void setNombreCondicion(String nombreCondicion) {
		this.nombreCondicion = nombreCondicion;
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public BigDecimal getPesoOComparar() {
		return pesoOComparar;
	}

	public void setPesoOComparar(BigDecimal pesoOComparar) {
		this.pesoOComparar = pesoOComparar;
	}

	public int getAnios() {
		return anios;
	}

	public void setAnios(int anios) {
		this.anios = anios;
	}

}
