package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.app.DslIndicador;
import main.converter.SnapshotIndicadorConverter;
import main.repositories.RepositorioIndicador;
import main.repositories.RepositorioUsuario;
import model.RegistroIndicador;
import model.SnapshotIndicador;

public class IndicadorService {

	public static HashMap<String, Object> consultarView(String indicadorId, Long usuarioId) {
		SnapshotIndicadorConverter snapshotIndicadorConverter = new SnapshotIndicadorConverter();
		HashMap<String, Object> mapIndicadores = new HashMap<>();

		RegistroIndicador indicador = RepositorioIndicador.getSingletonInstance().buscar(Long.parseLong(indicadorId));

		List<SnapshotIndicador> snapshots = snapshotIndicadorConverter.snapshotsOf(usuarioId, indicador);
		mapIndicadores.put("snapshots", snapshots);

		return mapIndicadores;
	}

	public static void delete(String indicadorId) {
		RepositorioIndicador.getSingletonInstance().eliminar(Long.parseLong(indicadorId));
	}

	public static void agregar(String nombre, String formula, Long usuarioId) throws Exception {
		RegistroIndicador nuevoIndicador = new RegistroIndicador(nombre, formula);
		nuevoIndicador.setUser(RepositorioUsuario.getSingletonInstance().buscar(usuarioId));
		new DslIndicador().añadirIndicador(nuevoIndicador);
	}

	public static HashMap<String, Object> homeView(Long usuarioId) {
		SnapshotIndicadorConverter snapshotIndicadorConverter = new SnapshotIndicadorConverter();

		HashMap<String, Object> mapIndicadores = new HashMap<>();

		List<RegistroIndicador> indicadoresObtenidas = usuarioId != null
				? RepositorioIndicador.getSingletonInstance().allInstancesUser(usuarioId)
				: new ArrayList<>();
		mapIndicadores.put("indicadores", indicadoresObtenidas);

		List<SnapshotIndicador> snapshots = snapshotIndicadorConverter.allSnapshotIndicadores(usuarioId);
		mapIndicadores.put("snapshots", snapshots);
		mapIndicadores.put("listaVacia", snapshots.isEmpty());
		// mapIndicadores.put("notificacion", cookie);

		return mapIndicadores;
	}

	public static HashMap<String, Object> homeAgregarIndicador(Long usuarioId, String cookie) {

		HashMap<String, Object> mapIndicadores = new HashMap<>();

		mapIndicadores.put("notificacion", cookie);

		return mapIndicadores;
	}
}
