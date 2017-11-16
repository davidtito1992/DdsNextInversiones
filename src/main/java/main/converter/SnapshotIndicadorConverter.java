package main.converter;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import main.app.AplicacionContexto;
import main.app.DslIndicador;
import main.cache.CacheIndicador;
import main.repositories.RepositorioCacheIndicador;
import main.repositories.RepositorioEmpresa;
import main.repositories.RepositorioIndicador;
import model.Empresa;
import model.RegistroIndicador;
import model.SnapshotIndicador;

public class SnapshotIndicadorConverter {

	/********* METODOS *********/

	public RepositorioEmpresa getRepositorioEmpresas() {
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}

	public List<SnapshotIndicador> snapshotsOf(Long idUser,
			RegistroIndicador indicador) {
		List<Empresa> empresas = getRepositorioEmpresas().allInstancesUser(
				idUser);
		List<SnapshotIndicador> snapshots = new ArrayList<SnapshotIndicador>();
		snapshots.addAll(resultadosIndicadores(idUser, indicador, empresas));
		return snapshots.stream().distinct().collect(Collectors.toList());
	}

	public List<SnapshotIndicador> allSnapshotIndicadores(Long idUser) {

		List<Empresa> empresas = getRepositorioEmpresas().allInstancesUser(
				idUser);
		List<RegistroIndicador> indicadores = getRepositorioIndicadores()
				.allInstancesUser(idUser);
		List<SnapshotIndicador> snapshots = new ArrayList<SnapshotIndicador>();

		indicadores.forEach(indicador -> snapshots
				.addAll(resultadosIndicadores(idUser, indicador, empresas)));

		return snapshots.stream().distinct().collect(Collectors.toList());
	}

	public RepositorioIndicador getRepositorioIndicadores() {
		return AplicacionContexto.getInstance().getInstanceRepoIndicadores();
	}

	public List<SnapshotIndicador> resultadosIndicadores(Long idUser,
			RegistroIndicador indicador, List<Empresa> listaDeEmpresas) {

		List<SnapshotIndicador> listSnapshot = listaDeEmpresas
				.stream()
				.map(empresa -> empresa
						.getPeriodos()
						.stream()
						.map(periodo -> {
							return crearSnapshotIndicador(idUser, indicador,
									empresa.getNombre(), periodo.getAnio(),
									periodo.getSemestre());
						}).collect(Collectors.toList()))
				.flatMap(listaSnap -> listaSnap.stream())
				.collect(Collectors.toList());

		return listSnapshot;
	}

	public SnapshotIndicador crearSnapshotIndicador(Long idUser,
			RegistroIndicador indicador, String nombreEmpresa, Year anio,
			int semestre) {

		String resultado;
		try {
			if (RepositorioCacheIndicador.getSingletonInstance()
					.existeCacheIndicador(idUser, indicador.getNombre(),
							nombreEmpresa, anio, semestre)) {

				resultado = RepositorioCacheIndicador.getSingletonInstance()
						.getValorCacheIndicador(idUser, indicador.getNombre(),
								nombreEmpresa, anio, semestre);

			} else {
				resultado = new DslIndicador()
						.prepararFormula(indicador, nombreEmpresa, anio,
								semestre).calcular().toPlainString();
				/*CacheIndicador nuevoCacheIndicador = new CacheIndicador(idUser,
						indicador.getNombre(), nombreEmpresa, anio.getValue(),
						semestre, Long.valueOf(resultado));

				RepositorioCacheIndicador.getSingletonInstance().agregar(
						nuevoCacheIndicador);*/
			}
		} catch (Exception e) {
			resultado = e.getMessage();
		}

		SnapshotIndicador snapshotIndicador = new SnapshotIndicador(indicador,
				nombreEmpresa, anio, semestre, resultado);
		return snapshotIndicador;
	}
}
