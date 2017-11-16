package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.bindings.keys.ParseException;

import main.app.AppData;
import main.app.DslIndicador;
import main.converter.SnapshotIndicadorConverter;
import main.dataManagment.dataLoader.JsonAdapter;
import main.dataManagment.dataUploader.AdapterToJson;
import main.repositories.RepositorioIndicador;
import main.repositories.RepositorioUsuario;
import model.RegistroIndicador;
import model.SnapshotIndicador;
import redis.clients.jedis.Jedis;

public class IndicadorService {

	private static Jedis jedisCache = new Jedis();

	public static HashMap<String, Object> consultarView(String indicadorId, Long usuarioId) {

		HashMap<String, Object> mapIndicadores = new HashMap<>();
		String nombreIndicador = RepositorioIndicador.getSingletonInstance().buscar(Long.parseLong(indicadorId))
				.getNombre();

		mapIndicadores.put("snapshots", getIndicadoresPrecalculados(nombreIndicador));
		return mapIndicadores;
	}

	public static void delete(String indicadorId) {
		AppData appData = new AppData();
		RegistroIndicador aBorrar = RepositorioIndicador.getSingletonInstance().buscar(Long.parseLong(indicadorId));
		appData.borrarIndicador(aBorrar);
	}

	public static void agregar(String nombre, String formula, Long usuarioId) throws Exception {
		RegistroIndicador nuevoIndicador = new RegistroIndicador(nombre, formula);
		nuevoIndicador.setUser(RepositorioUsuario.getSingletonInstance().buscar(usuarioId));
		new DslIndicador().añadirIndicador(nuevoIndicador);

		SnapshotIndicadorConverter snapshotIndicadorConverter = new SnapshotIndicadorConverter();
		AdapterToJson adapter = new AdapterToJson();

		List<SnapshotIndicador> snapshots = snapshotIndicadorConverter.snapshotsOf(usuarioId, nuevoIndicador);
		String snapshotsJson = adapter.getStringListRegistroIndicador(snapshots);
		jedisCache.set(nuevoIndicador.getNombre(), snapshotsJson);

	}

	public static HashMap<String, Object> homeView(Long usuarioId) {

		HashMap<String, Object> mapIndicadores = new HashMap<>();

		List<RegistroIndicador> indicadoresObtenidas = usuarioId != null
				? RepositorioIndicador.getSingletonInstance().allInstancesUser(usuarioId)
				: new ArrayList<>();

		mapIndicadores.put("indicadores", indicadoresObtenidas);
		mapIndicadores.put("listaVacia", indicadoresObtenidas.isEmpty());

		return mapIndicadores;
	}

	private static List<SnapshotIndicador> getIndicadoresPrecalculados(String nombreIndicador) {
		JsonAdapter adapter = new JsonAdapter();
		String json = jedisCache.get(nombreIndicador);
		return adapter.adaptarSnapshotIndicadores(json);

	}

	public static HashMap<String, Object> homeAgregarIndicador(Long usuarioId, String cookie) {
		HashMap<String, Object> mapIndicadores = new HashMap<>();
		mapIndicadores.put("notificacion", cookie);
		return mapIndicadores;
	}
}
