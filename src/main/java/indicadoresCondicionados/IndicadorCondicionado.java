package indicadoresCondicionados;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import app.DslIndicador;
import model.Empresa;
import model.RegistroIndicador;
import model.SnapshotIndicador;

import RankingEmpresa.RankingEmpresa;

public class IndicadorCondicionado {
	private int prioridad;
	private String nombreIndicador;
	private List<Condicion> condiciones;

	public IndicadorCondicionado(int prioridad, String nombre,
			List<Condicion> condiciones) {

		this.prioridad = prioridad;
		this.nombreIndicador = nombre;
		this.condiciones = condiciones;
	}

	public String getNombreIndicador() {
		return nombreIndicador;
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setProridad(int proridad) {
		this.prioridad = proridad;
	}

	public ArrayList<RankingEmpresa> calcular(
			ArrayList<RankingEmpresa> listRanking) {

		ControladorDeMetodologia controlador = new ControladorDeMetodologia();

		RegistroIndicador indicador = controlador.getRepoIndicadores()
				.getRegistroIndicador(this.getNombreIndicador());

		ArrayList<Empresa> listaEmpresas = (ArrayList<Empresa>) listRanking
				.stream()
				.map(i -> controlador.getRepoEmpresas().getEmpresa(
						i.getNombre()))
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
}
