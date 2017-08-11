package viewmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.ControladorDeMetodologia;
import model.Empresa;
import model.Metodologia;
import model.SnapshotRankingEmpresa;

import org.uqbar.commons.utils.Observable;

import repositories.RepositorioUnicoDeEmpresas;
import RankingEmpresa.RankingEmpresa;
import app.AplicacionContexto;

@Observable
public class ConsultarMetodologiaViewM {

	/********* ATRIBUTOS *********/

	private Metodologia metodologia;
	private List<RankingEmpresa> rankingDeEmpresas;
	private List<SnapshotRankingEmpresa> snapshotRankingEmpresas;
	//private SnapshotRankingEmpresa snapshotRankingEmpresasSeleccionado;
	private List<SnapshotRankingEmpresa> snapshotRankingEmpresasFallidas;
	//private SnapshotRankingEmpresa snapshotRankingEmpresasFallidasSeleccionado;

	public ConsultarMetodologiaViewM(Metodologia metodologia) {

		this.metodologia = metodologia;

		this.inicializar();
		this.llenarTablas();
	}

	/********* GETTERS/SETTERS *********/

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

	public List<SnapshotRankingEmpresa> getSnapshotRankingEmpresas() {
		return snapshotRankingEmpresas;
	}

	public void setSnapshotRankingEmpresas(
			List<SnapshotRankingEmpresa> snapshotRankingEmpresas) {
		this.snapshotRankingEmpresas = snapshotRankingEmpresas;
	}

	public List<SnapshotRankingEmpresa> getSnapshotRankingEmpresasFallidas() {
		return snapshotRankingEmpresasFallidas;
	}

	public void setSnapshotRankingEmpresasFallidas(
			List<SnapshotRankingEmpresa> snapshotRankingEmpresasFallidas) {
		this.snapshotRankingEmpresasFallidas = snapshotRankingEmpresasFallidas;
	}

	/********* METODOS *********/

	@SuppressWarnings("unchecked")
	public void inicializar() {
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas = getRepositorioEmpresas().getElementos();
		rankingDeEmpresas = empresas.stream()
				.map(empresa -> new RankingEmpresa(empresa))
				.collect(Collectors.toList());

	}

	public void llenarTablas() {
		ControladorDeMetodologia controladorDeMetodologia = new ControladorDeMetodologia(metodologia, rankingDeEmpresas);
		snapshotRankingEmpresas = controladorDeMetodologia.obtenerSnapshotRankingEmpresas();
		snapshotRankingEmpresasFallidas = controladorDeMetodologia.obtenerSnapshotRankingEmpresasFallidas();
	}

	public RepositorioUnicoDeEmpresas getRepositorioEmpresas() {
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}

}
