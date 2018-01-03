package main.converter;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import main.app.AplicacionContexto;
import main.repositories.RepositorioEmpresa;
import main.repositories.RepositorioIndicador;
import main.repositories.RepositorioPrecalculos;
import model.Empresa;
import model.RegistroIndicador;
import model.SnapshotIndicador;

public class SnapshotIndicadorConverter {

	/********* METODOS *********/

	private RepositorioEmpresa getRepositorioEmpresas() {
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}

	private RepositorioIndicador getRepositorioIndicadores() {
		return AplicacionContexto.getInstance().getInstanceRepoIndicadores();
	}

	private RepositorioPrecalculos getRepositorioCacheIndicador() {
		return AplicacionContexto.getInstance().getInstanceRepoPrecalculos();
	}

	public List<SnapshotIndicador> snapshotsOf(Long idUser,
			RegistroIndicador indicador) {
		List<Empresa> empresas = getRepositorioEmpresas().allInstancesUser(
				idUser);
		List<SnapshotIndicador> snapshots = new ArrayList<SnapshotIndicador>();
		snapshots.addAll(this
				.resultadosIndicadores(idUser, indicador, empresas));
		return snapshots.stream().distinct().collect(Collectors.toList());
	}

	public List<SnapshotIndicador> allSnapshotIndicadores(Long idUser) {

		List<Empresa> empresas = getRepositorioEmpresas().allInstancesUser(
				idUser);
		List<RegistroIndicador> indicadores = getRepositorioIndicadores()
				.allInstancesUser(idUser);
		List<SnapshotIndicador> snapshots = new ArrayList<SnapshotIndicador>();

		indicadores.forEach(indicador -> snapshots.addAll(this
				.resultadosIndicadores(idUser, indicador, empresas)));

		return snapshots.stream().distinct().collect(Collectors.toList());
	}

	public List<SnapshotIndicador> resultadosIndicadores(Long userId,
			RegistroIndicador indicador, List<Empresa> listaDeEmpresas) {

		List<SnapshotIndicador> listSnapshot = listaDeEmpresas
				.stream()
				.map(empresa -> empresa
						.getPeriodos()
						.stream()
						.map(periodo -> {
							return crearSnapshotIndicador(userId, indicador,
									empresa.getNombre(), periodo.getAnio(),
									periodo.getSemestre());
						}).collect(Collectors.toList()))
				.flatMap(listaSnap -> listaSnap.stream())
				.collect(Collectors.toList());

		return listSnapshot;
	}

	public SnapshotIndicador crearSnapshotIndicador(Long userId,
			RegistroIndicador indicador, String nombreEmpresa, Year anio,
			int semestre) {

		String resultado = this.getRepositorioCacheIndicador()
				.getValorIndicadorPrecalculado(userId, indicador.getNombre(),
						nombreEmpresa, anio, semestre);

		SnapshotIndicador snapshotIndicador = new SnapshotIndicador(indicador,
				nombreEmpresa, anio.getValue(), semestre, resultado);
		return snapshotIndicador;
	}
}
