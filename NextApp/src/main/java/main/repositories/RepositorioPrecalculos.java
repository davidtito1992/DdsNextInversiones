package main.repositories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.bindings.keys.ParseException;

import main.app.AplicacionContexto;
import main.converter.SnapshotIndicadorConverter;
import main.dataManagment.dataLoader.JsonAdapter;
import main.dataManagment.dataUploader.AdapterToJson;
import model.Empresa;
import model.RegistroIndicador;
import model.SnapshotIndicador;
import redis.clients.jedis.Jedis;

public class RepositorioPrecalculos {

	private static RepositorioPrecalculos repositorioPrecalculos;
	private SnapshotIndicadorConverter snapshotIndicadorConverter = new SnapshotIndicadorConverter();
	private AdapterToJson adapter = new AdapterToJson();
	private Jedis jedisCache = new Jedis();

	public static RepositorioPrecalculos getSingletonInstance() {
		if (repositorioPrecalculos == null) {
			repositorioPrecalculos = new RepositorioPrecalculos();
		}
		return repositorioPrecalculos;
	}

	/********* METODOS *********/

	public static List<Long> adaptarJsonAUsuariosId(String jsonUsuarios) {
		JsonAdapter adapter = new JsonAdapter();
		List<Long> usuarios = null;
		try {
			usuarios = adapter.adaptarIds(jsonUsuarios);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public void precalcularIndicador(Long usuarioId,
			RegistroIndicador nuevoIndicador) {
		SnapshotIndicadorConverter snapshotIndicadorConverter = new SnapshotIndicadorConverter();
		AdapterToJson adapter = new AdapterToJson();
		List<SnapshotIndicador> snapshots = snapshotIndicadorConverter
				.snapshotsOf(usuarioId, nuevoIndicador);
		String snapshotsJson = adapter
				.getStringListRegistroIndicador(snapshots);
		jedisCache.set(usuarioId + nuevoIndicador.getNombre(), snapshotsJson);
	}
	
	public List<SnapshotIndicador> getIndicadoresPrecalculados(
			Long userId, String nombreIndicador) {
		JsonAdapter adapter = new JsonAdapter();
		String json = jedisCache.get(userId + nombreIndicador);
		return adapter.adaptarSnapshotIndicadores(json);
	}
	
	public void precalcularIndicadorDeUsuario(Long usuarioId) {
		List<RegistroIndicador> indicadoresObtenidas = usuarioId != null ? RepositorioIndicador
				.getSingletonInstance().allInstancesUser(usuarioId)
				: new ArrayList<>();

		indicadoresObtenidas.forEach(indicador -> {
			List<SnapshotIndicador> snapshots = snapshotIndicadorConverter
					.snapshotsOf(usuarioId, indicador);
			String snapshotsJson = adapter
					.getStringListRegistroIndicador(snapshots);
			jedisCache.set(usuarioId + indicador.getNombre(), snapshotsJson);
		});
	}

	public void precalcularTodosLosIndicadores() {
		this.getRepositorioUsuarios()
				.allInstances()
				.forEach(
						user -> precalcularIndicadorDeUsuario(user.getUserId()));
	}

	public RepositorioUsuario getRepositorioUsuarios() {
		return AplicacionContexto.getInstance().getInstanceRepoUsuarios();
	}

	public void borrarTodo() {
		jedisCache.flushAll();
	}
	
	public void actualizarPrecalculos(String jsonUsuarios){
		List<Long> idsUsuarios = adaptarJsonAUsuariosId(jsonUsuarios);
		idsUsuarios.forEach(user -> {
			this.precalcularIndicadorDeUsuario(user);;
		});
	}

}
