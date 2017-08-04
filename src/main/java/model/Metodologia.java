package model;

import indicadoresCondicionados.ControladorDeMetodologia;
import indicadoresCondicionados.IndicadorCondicionado;
import indicadoresCondicionados.RankingEmpresa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.Observable;

import parserIndicador.ParseException;
import ex_condiciones.CondicionCualitativa;
import ex_condiciones.CondicionTaxativa;

@SuppressWarnings("serial")
@Observable
public class Metodologia extends Entity {

	// public Metodologia(String nombre, List<CondicionTaxativa> condTaxativas,
	// List<CondicionCualitativa> condCualitativas) {
	// this.nombre = nombre;
	// this.condicionesTaxativas = condTaxativas;
	// this.condicionesCualitativas = condCualitativas;
	// }
	public Metodologia(String nombre,
			ArrayList<IndicadorCondicionado> indicadoresCondicionados) {
		this.nombre = nombre;
		// this.condicionesTaxativas = condTaxativas;
		// this.condicionesCualitativas = condCualitativas;
		this.indicadoresCondicionados = indicadoresCondicionados;

	}

	/********* ATRIBUTOS *********/

	public String nombre;
	// public List<CondicionTaxativa> condicionesTaxativas;
	// public List<CondicionCualitativa> condicionesCualitativas;
	public ArrayList<IndicadorCondicionado> indicadoresCondicionados;

	/********* GETTERS/SETTERS *********/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<IndicadorCondicionado> getIndicadoresCondicionados() {
		return indicadoresCondicionados;
	}

	public void setIndicadoresCondicionados(
			ArrayList<IndicadorCondicionado> indicadoresCondicionados) {
		this.indicadoresCondicionados = indicadoresCondicionados;
	}

	// public int analizarResultado(Empresa empresa) throws ParseException {
	// if (!analizarCondicionesTaxativas(empresa)) {
	// return 0;
	// } else {
	// return analizarCondicionesCualitativas(empresa);
	// }
	// }
	//
	// private boolean analizarCondicionesTaxativas(Empresa empresa)
	// throws ParseException {
	// for (int i = 0; i < condicionesTaxativas.size(); i++) {
	// if (!condicionesTaxativas.get(i).calcular(empresa)) {
	// return false;
	// }
	// }
	// return true;
	// }
	//
	// private int analizarCondicionesCualitativas(Empresa empresa)
	// throws ParseException {
	// int acumulador = 0;
	// for (int i = 0; i < condicionesCualitativas.size(); i++) {
	// acumulador += condicionesCualitativas.get(i).calcular(empresa);
	// }
	// return acumulador;
	// }

	public ArrayList<RankingEmpresa> calcular() {

		ArrayList<IndicadorCondicionado> indicadoresCondicionados = this
				.getIndicadoresCondicionadosOrdenados(this
						.getIndicadoresCondicionados());

		ArrayList<RankingEmpresa> listRanking = this
				.obtenerRankingNuloDeTodasLasEmpresas();

		for (int i = 0; i < indicadoresCondicionados.size(); i++) {

			listRanking = this.calcularIndicadorCondicionado(
					indicadoresCondicionados.get(i), listRanking);
		}
		listRanking.sort(Comparator.comparing(RankingEmpresa::getRanking));
		listRanking.get(listRanking.size() -1).setRanking(-1);
		listRanking.get(listRanking.size() -1).setObservaciones("No Invertir");
		return listRanking;
	}

	public ArrayList<IndicadorCondicionado> getIndicadoresCondicionadosOrdenados(
			ArrayList<IndicadorCondicionado> indicadoresCondicionados) {

		// ***************HARDCODE**********//
		indicadoresCondicionados.remove(0);
		indicadoresCondicionados.add(new IndicadorCondicionado(3, "ROE", null));
		indicadoresCondicionados.add(new IndicadorCondicionado(2,
				"CantidadAcciones", null));
		indicadoresCondicionados.add(new IndicadorCondicionado(4,
				"IngresoNeto", null));
		indicadoresCondicionados.add(new IndicadorCondicionado(2, "I2", null));
		// ///***********************//

		indicadoresCondicionados.sort(Comparator
				.comparing(IndicadorCondicionado::getPrioridad));

		return indicadoresCondicionados;
	}

	public ArrayList<RankingEmpresa> calcularIndicadorCondicionado(
			IndicadorCondicionado indicadorCondicionado,
			ArrayList<RankingEmpresa> listRanking) {

		return indicadorCondicionado.calcular(listRanking);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<RankingEmpresa> obtenerRankingNuloDeTodasLasEmpresas() {

		ControladorDeMetodologia controlador = new ControladorDeMetodologia();

		ArrayList<RankingEmpresa> listEmpresas = new ArrayList<RankingEmpresa>();
		controlador
				.getRepoEmpresas()
				.todosLosNombresDeEmpresas(
						controlador.getRepoEmpresas().getElementos())
				.forEach(
						nombreEmpresa -> listEmpresas.add(new RankingEmpresa(0,
								nombreEmpresa)));
		return listEmpresas;
	}

}
