package model;

import java.util.List;
import java.util.stream.Collectors;

import main.rankingEmpresa.RankingEmpresa;


public class ControladorDeMetodologia {
	
	private Metodologia metodologia;
	private List<RankingEmpresa> rEmpresas;

	public ControladorDeMetodologia(Metodologia metodologia, List<RankingEmpresa> rEmpresas){
		this.metodologia = metodologia;
		this.rEmpresas = rEmpresas;
	}
	
	public List<RankingEmpresa> calcularRankingDeEmpresas(){
		return metodologia.calcularEmpresas(rEmpresas);
	}
	
	public List<SnapshotRankingEmpresa> obtenerSnapshotRankingEmpresas(){
		return calcularRankingDeEmpresas().stream()
				.filter(empresa -> !empresa.getErrorTaxativa())
				.map(rEmpresa -> new SnapshotRankingEmpresa(rEmpresa))
				.collect(Collectors.toList());
	}
	
	public List<SnapshotRankingEmpresa> obtenerSnapshotRankingEmpresasFallidas(){
		return calcularRankingDeEmpresas().stream()
				.filter(empresa -> empresa.getErrorTaxativa())
				.map(rEmpresa -> new SnapshotRankingEmpresa(rEmpresa))
				.collect(Collectors.toList());
	}
	
	
	
}
