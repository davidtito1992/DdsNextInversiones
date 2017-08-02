package indicadoresCondicionados;

import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;
import model.SnapshotIndicador;
import org.uqbar.commons.utils.ApplicationContext;
import app.DslIndicador;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import repositories.RepositorioMetodologias;

public class ControladorDeMetodologia {

	public ArrayList<RankingEmpresa> calcular(Metodologia metodologia) {

		ArrayList<IndicadorCondicionado> indicadoresCondicionados = this
				.getIndicadoresCondicionadosOrdenados(metodologia
						.getIndicadoresCondicionados());

		ArrayList<RankingEmpresa> listRanking = this
				.obtenerRankingNuloDeTodasLasEmpresas();

		for (int i = 0; i < indicadoresCondicionados.size(); i++) {

			listRanking = this.calcularIndicadorCondicionado(
					indicadoresCondicionados.get(i), listRanking);
		}
		listRanking.sort(Comparator.comparing(RankingEmpresa::getRanking));
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

		RegistroIndicador indicador = this.getRepoIndicadores()
				.getRegistroIndicador(
						indicadorCondicionado.getNombreIndicador());

		ArrayList<Empresa> listaEmpresas = listRanking.stream()
				.map(i -> this.getRepoEmpresas().getEmpresa(i.getNombre()))
				.collect(Collectors.toCollection(ArrayList::new));

		List<SnapshotIndicador> listSnapshot = listaEmpresas
				.stream()
				.map(empresa -> empresa
						.getPeriodos()
						.stream()
						.map(periodo ->

						{
							return crearSnapshotIndicador(indicador,
									empresa.getNombre(), periodo.getAnio(),
									periodo.getSemestre());
						}).collect(Collectors.toList()))
				.flatMap(listaSnap -> listaSnap.stream())
				.collect(Collectors.toList());

		System.out.println("EJECUTO EL INDICADOR CONDICIONADO: "
				+ indicador.getNombre());
		listSnapshot
				.forEach(i -> System.out.println(i.getNombreEmpresa() + " "
						+ i.getAnio() + " " + i.getSemestre() + " "
						+ i.getResultado()));

		// **********HARDCODE******************/
		ArrayList<RankingEmpresa> listRanking2 = new ArrayList<RankingEmpresa>();
		listRanking2 = (ArrayList<RankingEmpresa>) listRanking.clone();
		if (indicador.getNombre().equalsIgnoreCase("ROE")) {

			listRanking2.get(0).setRanking(8);
			listRanking2.get(1).setRanking(2);
			listRanking2.get(2).setRanking(1);

		} else if (indicador.getNombre().equalsIgnoreCase("IngresoNeto")) {

			listRanking2.get(0).setRanking(3);
			listRanking2.get(1).setRanking(2);
			listRanking2.get(2).setRanking(5);
		} else {
			listRanking2.get(0).setRanking(4);
			listRanking2.get(1).setRanking(9);
			listRanking2.get(2).setRanking(2);

		}

		for (int i = 0; i < listRanking.size(); i++) {
			listRanking.get(i).setRanking(
					(listRanking.get(i).getRanking() + listRanking2.get(i)
							.getRanking()) / 2);
		}
		// ************************************//

		return listRanking;
	}

	public SnapshotIndicador crearSnapshotIndicador(
			RegistroIndicador indicador, String nombreEmpresa, Year anio,
			int semestre) {
		SnapshotIndicador snapshotIndicador = new SnapshotIndicador();
		snapshotIndicador.setNombreEmpresa(nombreEmpresa);
		snapshotIndicador.setAnio(anio);
		snapshotIndicador.setSemestre(semestre);
		String resultado;
		try {
			resultado = new DslIndicador()
					.prepararFormula(indicador, nombreEmpresa, anio, semestre)
					.calcular().toPlainString();
		} catch (Exception e) {
			resultado = e.getMessage();
		}
		snapshotIndicador.setResultado(resultado);
		return snapshotIndicador;
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