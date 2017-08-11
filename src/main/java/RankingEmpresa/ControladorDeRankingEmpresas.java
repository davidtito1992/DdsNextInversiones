package RankingEmpresa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Empresa;
import repositories.RepositorioUnicoDeEmpresas;
import app.AplicacionContexto;

public class ControladorDeRankingEmpresas {
	
	
	@SuppressWarnings("unchecked")
	public List<RankingEmpresa> obtenerRankingEmpresas(){
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas = getRepositorioEmpresas().getElementos();
		return empresas.stream()
				.map(empresa -> new RankingEmpresa(empresa))
				.collect(Collectors.toList());
	}
	
	public RepositorioUnicoDeEmpresas getRepositorioEmpresas() {
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}
}
