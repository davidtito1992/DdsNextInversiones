package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import main.condiciones.Condicion;
import main.condiciones.CondicionesBuilder;
import main.dataManagment.dataLoader.GsonFactory;
import main.rankingEmpresa.RankingEmpresa;
import main.repositories.RepositorioEmpresa;
import main.repositories.RepositorioIndicador;
import main.repositories.RepositorioMetodologia;
import main.repositories.RepositorioUsuario;
import model.ControladorDeMetodologia;
import model.Metodologia;
import model.SnapshotCondicion;
import model.SnapshotRankingEmpresa;

public class MetodologiaService {

	@SuppressWarnings("static-access")
	public static void validar(String indicadorSeleccionado, String condicionSeleccionada,
			String tipoCondicionSeleccionado, BigDecimal pesoOCompararSeleccionado, int ultimosAniosSeleccionado)
			throws RuntimeException {

		if (condicionSeleccionada.equals(CondicionesBuilder.ANTIGUEDAD))
			indicadorSeleccionado = CondicionesBuilder.VACIARINDICADOR;

		if (indicadorSeleccionado == null)
			throw new RuntimeException("Seleccione un indicador");

		if (tipoCondicionSeleccionado == null)
			throw new RuntimeException("Seleccione un tipo de condicion");

		if (condicionSeleccionada == null) {
			throw new RuntimeException("Seleccione una condicion");
		}
		if (tipoCondicionSeleccionado.equalsIgnoreCase(CondicionesBuilder.CUANTITATIVA)
				&& (condicionSeleccionada.equalsIgnoreCase(CondicionesBuilder.CRECIENTE)
						|| condicionSeleccionada.equalsIgnoreCase(CondicionesBuilder.DECRECIENTE))) {
			throw new RuntimeException("Las condiciones Creciente y Decreciente no pueden ser Cuantitativas");
		}
		if (!condicionSeleccionada.equalsIgnoreCase(CondicionesBuilder.ANTIGUEDAD)
				&& Objects.isNull(ultimosAniosSeleccionado)) {
			throw new RuntimeException("Falta indicar desde que año aplica la condicion");
		} else {
			if (Objects.isNull(ultimosAniosSeleccionado)) {
				ultimosAniosSeleccionado = 0;
			}
		}
		if (!condicionSeleccionada.equalsIgnoreCase(CondicionesBuilder.CRECIENTE)
				&& !condicionSeleccionada.equalsIgnoreCase(CondicionesBuilder.DECRECIENTE)
				&& pesoOCompararSeleccionado == null) {
			throw new RuntimeException("Falta indicar el Peso o Numero a Comparar");
		} else {
			if (pesoOCompararSeleccionado == null) {
				pesoOCompararSeleccionado.valueOf(0);
			}
		}

	}

	public static List<String> listaCondiciones() {
		List<String> condiciones = new ArrayList<String>();
		condiciones.add(CondicionesBuilder.MAYOR);
		condiciones.add(CondicionesBuilder.MENOR);
		condiciones.add(CondicionesBuilder.ANTIGUEDAD);
		condiciones.add(CondicionesBuilder.CRECIENTE);
		condiciones.add(CondicionesBuilder.DECRECIENTE);
		return condiciones;
	}

	public static List<String> listaTiposCondiciones() {
		List<String> tiposCondiciones = new ArrayList<String>();
		tiposCondiciones.add(CondicionesBuilder.TAXATIVA);
		tiposCondiciones.add(CondicionesBuilder.CUANTITATIVA);
		return tiposCondiciones;
	}

	public static HashMap<String, Object> mapeoConsultarMetodologia(ControladorDeMetodologia controlador) {
		HashMap<String, Object> mapConsultaMetodologias = new HashMap<>();
		List<SnapshotRankingEmpresa> empresasOk = controlador.obtenerSnapshotRankingEmpresas();
		List<SnapshotRankingEmpresa> empresasError = controlador.obtenerSnapshotRankingEmpresasFallidas();

		mapConsultaMetodologias.put("resultadoOk", empresasOk);
		mapConsultaMetodologias.put("resultadoError", empresasError);
		return mapConsultaMetodologias;
	}

	public static HashMap<String, Object> mapeoCondiciones(Long idUsuario, String nombreMetodologia, String cookie,
			List<SnapshotCondicion> listaCondiciones) {
		ArrayList<String> indicadores = RepositorioIndicador.getSingletonInstance().allInstancesUser(idUsuario).stream()
				.map(indicador -> indicador.getNombre()).collect(Collectors.toCollection(ArrayList::new));
		HashMap<String, Object> mapAMetod = new HashMap<>();
		mapAMetod.put("condiciones", listaCondiciones());
		mapAMetod.put("tipoCondiciones", listaTiposCondiciones());
		mapAMetod.put("indicadores", indicadores);

		mapAMetod.put("condicionesCreadas", listaCondiciones);
		mapAMetod.put("condicionesCreadasEmpty", listaCondiciones.isEmpty());
		mapAMetod.put("JSONCondiciones", crearJSONCondiciones(listaCondiciones));

		mapAMetod.put("nombreMetodologia", nombreMetodologia);
		mapAMetod.put("Notificacion", cookie);
		// mapAMetod.put("errorAgregarCondicion", errorAgregarCondicion);
		return mapAMetod;
	}

	public static HashMap<String, Object> homeView(Long usuarioId) {
		List<Metodologia> metodologiasObtenidas = RepositorioMetodologia.getSingletonInstance()
				.allInstancesUser(usuarioId);

		HashMap<String, Object> mapMetodologias = new HashMap<>();
		mapMetodologias.put("metodologias", metodologiasObtenidas);
		mapMetodologias.put("listaVacia", metodologiasObtenidas.isEmpty());
		return mapMetodologias;
	}

	public static List<SnapshotCondicion> agregarCondicion(String cookie, String indicador, String tipoCondicion,
			String condicion, String peso, String anios, String nombreMetodologia, String JSONCondiciones) {
		// String errorCrearMetodologia = cookie;

		List<SnapshotCondicion> condicionesCreadas = new ArrayList<SnapshotCondicion>();
		String JSONCondicionesInput = Objects.isNull(JSONCondiciones) || JSONCondiciones.isEmpty() ? null
				: JSONCondiciones;
		String indicadorSeleccionado = Objects.isNull(indicador) || indicador.isEmpty() ? null : indicador;
		String tipoCondicionSeleccionado = Objects.isNull(tipoCondicion) || tipoCondicion.isEmpty() ? null
				: tipoCondicion;
		String condicionSeleccionada = Objects.isNull(condicion) || condicion.isEmpty() ? null : condicion;
		BigDecimal pesoOCompararSeleccionado = Objects.isNull(peso) || peso.isEmpty() ? null
				: BigDecimal.valueOf(Long.parseLong(peso));
		int ultimosAniosSeleccionado = Objects.isNull(anios) || anios.isEmpty() ? 0 : Integer.parseInt(anios);
		try {
			if (!(Objects.isNull(JSONCondicionesInput)))
				condicionesCreadas = snapshotCondicionDesdeJSON(JSONCondicionesInput);
			validar(indicadorSeleccionado, condicionSeleccionada, tipoCondicionSeleccionado, pesoOCompararSeleccionado,
					ultimosAniosSeleccionado);

			condicionesCreadas.add(new SnapshotCondicion(tipoCondicionSeleccionado, condicionSeleccionada,
					indicadorSeleccionado, pesoOCompararSeleccionado, ultimosAniosSeleccionado));

		} catch (Exception e) {
			// if (!(Objects.isNull(condicionSeleccionada) &&
			// Objects.isNull(indicadorSeleccionado)
			// && Objects.isNull(tipoCondicionSeleccionado) && ultimosAniosSeleccionado == 0
			// && Objects.isNull(pesoOCompararSeleccionado)))
			// errorAgregarCondicion = "La ultima condicion ingresada no cumple las
			// validaciones necesarias. Intentelo nuevamente.";
		}
		return condicionesCreadas;

	}

	public static String crearJSONCondiciones(List<SnapshotCondicion> condicionesCreadas) {
		Gson gson = new Gson();
		String JSONCondiciones = gson.toJson(condicionesCreadas);
		return JSONCondiciones;
	}

	public static ArrayList<SnapshotCondicion> snapshotCondicionDesdeJSON(String snapshotCondiciones) {
		ArrayList<SnapshotCondicion> listaSnapshotCondicion = new ArrayList<SnapshotCondicion>();
		Type listType = new TypeToken<ArrayList<SnapshotCondicion>>() {
		}.getType();
		listaSnapshotCondicion = GsonFactory.getGson().fromJson(snapshotCondiciones, listType);
		return listaSnapshotCondicion;
	}

	public static HashMap<String, Object> consultarView(Long usuarioId, String metodologiaId) {
		List<RankingEmpresa> rEmpresas = new ArrayList<RankingEmpresa>();
		RepositorioEmpresa.getInstance().allInstancesUser(usuarioId).stream()
				.forEach(empresa -> rEmpresas.add(new RankingEmpresa(empresa)));

		Metodologia metodologia = RepositorioMetodologia.getSingletonInstance().buscar(Long.parseLong(metodologiaId));

		ControladorDeMetodologia contrMet = new ControladorDeMetodologia(metodologia, rEmpresas);
		return mapeoConsultarMetodologia(contrMet);
	}

	public static void eliminar(String idMetodologia) {
		RepositorioMetodologia.getSingletonInstance().eliminar(Long.parseLong(idMetodologia));
	}

	public static void agregarMetodologia(Long usuarioId, String nombreMetodologia, String JSONCondiciones) {
		List<Condicion> condiciones = new ArrayList<Condicion>();
		List<SnapshotCondicion> condicionesCreadas = new ArrayList<SnapshotCondicion>();

		if (!(Objects.isNull(JSONCondiciones) || JSONCondiciones.isEmpty()))
			condicionesCreadas = snapshotCondicionDesdeJSON(JSONCondiciones);

		condicionesCreadas.stream()
				.forEach(snapshotCondicion -> condiciones.add(new CondicionesBuilder().crear(snapshotCondicion)));
		Metodologia metodologia = new Metodologia(nombreMetodologia, condiciones,
				RepositorioUsuario.getSingletonInstance().buscar(usuarioId));
		RepositorioMetodologia.getSingletonInstance().agregar(metodologia);

		condicionesCreadas = new ArrayList<SnapshotCondicion>();

	}

}
