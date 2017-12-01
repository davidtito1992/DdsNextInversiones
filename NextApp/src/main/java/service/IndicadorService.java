package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.bindings.keys.ParseException;

import main.app.AppData;
import main.app.DslIndicador;
import main.dataManagment.dataLoader.JsonAdapter;
import main.repositories.RepositorioIndicador;
import main.repositories.RepositorioPrecalculos;
import main.repositories.RepositorioUsuario;
import model.Empresa;
import model.RegistroIndicador;

public class IndicadorService {

	public static HashMap<String, Object> consultarView(String indicadorId,
			Long usuarioId) {
		HashMap<String, Object> mapIndicadores = new HashMap<>();
		String nombreIndicador = RepositorioIndicador.getSingletonInstance()
				.buscar(Long.parseLong(indicadorId)).getNombre();
		mapIndicadores
				.put("snapshots",
						RepositorioPrecalculos.getSingletonInstance()
								.getIndicadoresPrecalculados(usuarioId,
										nombreIndicador));
		return mapIndicadores;
	}

	public static void delete(String indicadorId) {
		AppData appData = new AppData();
		RegistroIndicador aBorrar = RepositorioIndicador.getSingletonInstance()
				.buscar(Long.parseLong(indicadorId));
		appData.borrarIndicador(aBorrar);
	}

	public static void agregar(String nombre, String formula, Long usuarioId)
			throws Exception {
		RegistroIndicador nuevoIndicador = new RegistroIndicador(nombre,
				formula);
		nuevoIndicador.setUser(RepositorioUsuario.getSingletonInstance()
				.buscar(usuarioId));
		new DslIndicador().añadirIndicador(nuevoIndicador);
		RepositorioPrecalculos.getSingletonInstance().precalcularIndicador(
				usuarioId, nuevoIndicador);
	}

	public static HashMap<String, Object> homeView(Long usuarioId) {
		HashMap<String, Object> mapIndicadores = new HashMap<>();
		List<RegistroIndicador> indicadoresObtenidas = usuarioId != null ? RepositorioIndicador
				.getSingletonInstance().allInstancesUser(usuarioId)
				: new ArrayList<>();
		mapIndicadores.put("indicadores", indicadoresObtenidas);
		mapIndicadores.put("listaVacia", indicadoresObtenidas.isEmpty());
		return mapIndicadores;
	}

	public static HashMap<String, Object> homeAgregarIndicador(Long usuarioId,
			String cookie) {
		HashMap<String, Object> mapIndicadores = new HashMap<>();
		mapIndicadores.put("notificacion", cookie);
		return mapIndicadores;
	}

	public static void actualizarPrecalculos(String jsonUsuarios) {
		RepositorioPrecalculos.getSingletonInstance().actualizarPrecalculos(
				jsonUsuarios);
	}

	public static List<Empresa> adaptarJsonAEmpresas(String jsonEmpresas) {
		JsonAdapter adapter = new JsonAdapter();
		List<Empresa> empresas = null;
		try {
			empresas = adapter.adaptarEmpresas(jsonEmpresas);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return empresas;
	}

}
