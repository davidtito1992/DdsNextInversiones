package indicadoresCondicionados;

import java.util.ArrayList;
import java.util.List;

import model.Metodologia;

import org.uqbar.commons.utils.ApplicationContext;

import repositories.RepositorioMetodologias;

public class ControladorDeMetodologia {

	
	public ArrayList<RankingEmpresa> calcular(String nombreMetodologia){
		
		Metodologia miMetodologia = this.getRepoMetodologias().getMetodologia(nombreMetodologia);
		
//		miMetodologia.calcular();
		
		
		List<IndicadorCondicionado> indicadoresCondicionados= miMetodologia.getIndicadoresCondicionados() ;
	
	//	indicadoresCondicionados.stream().map(indicadorCondicionado ->  this.calcularIndicador(indicadorCondicionado)).reduce(accumulator);
		
		ArrayList<RankingEmpresa> listRanking = new ArrayList<RankingEmpresa>() ;
		
		return listRanking;
	}
	
	public ArrayList<RankingEmpresa> calcularIndicadorCondicionado(IndicadorCondicionado indicadorCondicionado) {
		ArrayList<RankingEmpresa> listEmpresas = new ArrayList<RankingEmpresa>() ;
		return listEmpresas;
	}

	public RepositorioMetodologias getRepoMetodologias() {
		return ApplicationContext.getInstance().getSingleton(Metodologia.class);
	}
	
}
