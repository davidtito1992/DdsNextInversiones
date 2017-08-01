package indicadoresCondicionados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

import org.uqbar.commons.utils.ApplicationContext;

import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import repositories.RepositorioMetodologias;

public class ControladorDeMetodologia {

	public ArrayList<RankingEmpresa> calcular(Metodologia metodologia) {

		ArrayList<IndicadorCondicionado> indicadoresCondicionados = this.getIndicadoresCondicionadosOrdenados(metodologia.getIndicadoresCondicionados());

		ArrayList<RankingEmpresa> listRanking = this
				.obtenerRankingNuloDeTodasLasEmpresas();

		// Collections.sort(indicadoresCondicionados, (ic1, ic2) ->
		// ic1.getPrioridad().)

		return listRanking;
	}

	public ArrayList<IndicadorCondicionado> getIndicadoresCondicionadosOrdenados(
			ArrayList<IndicadorCondicionado> indicadoresCondicionados) {

		indicadoresCondicionados.add(new IndicadorCondicionado(3, "ROE", null));
		indicadoresCondicionados
				.add(new IndicadorCondicionado(2, "DEUDA", null));
		indicadoresCondicionados.add(new IndicadorCondicionado(4, "MARGEN",
				null));
		indicadoresCondicionados.add(new IndicadorCondicionado(2, "LONGEVIDAD",
				null));
		indicadoresCondicionados.sort(Comparator
				.comparing(IndicadorCondicionado::getPrioridad));

		indicadoresCondicionados.forEach(IC -> System.out.println(IC
				.getNombreIndicador()));

		return indicadoresCondicionados;
	}

	public ArrayList<RankingEmpresa> calcularIndicadorCondicionado(
			IndicadorCondicionado indicadorCondicionado) {
		ArrayList<RankingEmpresa> listEmpresas = new ArrayList<RankingEmpresa>();
		return listEmpresas;
	}

	public ArrayList<RankingEmpresa> obtenerRankingNuloDeTodasLasEmpresas() {

		ArrayList<RankingEmpresa> listEmpresas = new ArrayList<RankingEmpresa>();
		this.getRepoEmpresas()
				.todosLosNombresDeEmpresas(
						this.getRepoEmpresas().allInstances())
				.forEach(
						nombreEmpresa -> listEmpresas.add(new RankingEmpresa(0,
								nombreEmpresa)));
		return listEmpresas;
	}

	public RepositorioMetodologias getRepoMetodologias() {
		return ApplicationContext.getInstance().getSingleton(Metodologia.class);
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(
				RegistroIndicador.class);
	}

}
