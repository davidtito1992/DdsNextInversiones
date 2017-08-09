package viewmodel;

import java.util.ArrayList;
import java.util.List;

import model.Metodologia;

import org.uqbar.commons.utils.Observable;

import RankingEmpresa.RankingEmpresa;

@Observable
public class ConsultarMetodologiaViewM {

	/********* ATRIBUTOS *********/

	private List<String> nombresEmpresas = new ArrayList<String>();
	private String nombreEmpresaSeleccionado;
	private Metodologia metodologia;
	private List<RankingEmpresa> rankingDeEmpresas;
	private RankingEmpresa rankingDeEmpresaSeleccionado;

	public ConsultarMetodologiaViewM(Metodologia metodologia) {

		this.metodologia = metodologia;

		this.llenarTablas();
	}

	/********* GETTERS/SETTERS *********/

	public List<String> getNombresEmpresas() {
		return nombresEmpresas;
	}

	public void setNombresEmpresas(List<String> nombresEmpresas) {
		this.nombresEmpresas = nombresEmpresas;
	}

	public String getNombreEmpresaSeleccionado() {
		return nombreEmpresaSeleccionado;
	}

	public void setNombreEmpresaSeleccionado(String nombreEmpresaSeleccionado) {
		this.nombreEmpresaSeleccionado = nombreEmpresaSeleccionado;
	}

	public Metodologia getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}

	public List<RankingEmpresa> getRankingDeEmpresas() {
		return rankingDeEmpresas;
	}

	public void setRankingDeEmpresas(List<RankingEmpresa> rankingDeEmpresas) {
		this.rankingDeEmpresas = rankingDeEmpresas;
	}

	public RankingEmpresa getRankingDeEmpresaSeleccionado() {
		return rankingDeEmpresaSeleccionado;
	}

	public void setRankingDeEmpresaSeleccionado(
			RankingEmpresa rankingDeEmpresaSeleccionado) {
		this.rankingDeEmpresaSeleccionado = rankingDeEmpresaSeleccionado;
	}

	/********* METODOS *********/

	public void reiniciar() {
		this.limpiarFiltros();
		this.llenarTablas();
	}

	public void limpiarFiltros() {
		this.nombreEmpresaSeleccionado = null;
		// semestreSeleccionado = null;
	}

	public void llenarTablas() {
		//TODO
	}

	public void buscar() {
	}

}
