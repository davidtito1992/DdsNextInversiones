package main.rankingEmpresa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import main.app.AplicacionContexto;
import main.repositories.RepositorioEmpresa;
import model.Empresa;

public class ControladorDeRankingEmpresas {

	public List<RankingEmpresa> obtenerRankingEmpresas() {
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas = this.getRepositorioEmpresas().allInstances();
		return empresas.stream().map(empresa -> new RankingEmpresa(empresa)).collect(Collectors.toList());
	}

	public RepositorioEmpresa getRepositorioEmpresas() {
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}
}
