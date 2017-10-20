package controller;

import main.condiciones.Condicion;
import main.condiciones.CondicionesBuilder;
import main.rankingEmpresa.RankingEmpresa;
import main.repositories.RepositorioEmpresa;
import main.repositories.RepositorioIndicador;
import main.repositories.RepositorioMetodologia;
import main.repositories.RepositorioUsuario;
import model.ControladorDeMetodologia;
import model.Metodologia;
import model.SnapshotCondicion;
import model.SnapshotRankingEmpresa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MetodologiaController extends Controller {

	static String nombreMetodologiaSeleccionado;
	static String indicadorSeleccionado;
	static String tipoCondicionSeleccionado;
	static String condicionSeleccionada;
	static BigDecimal pesoOCompararSeleccionado;
	static int ultimosAniosSeleccionado;
	static List<SnapshotCondicion> condicionesCreadas = new ArrayList<SnapshotCondicion>();
	static RepositorioIndicador repoInd = RepositorioIndicador.getSingletonInstance();
	static String errorCrearMetodologia;
	static String errorAgregarCondicion;

	public MetodologiaController() {
	}

	// ---------------------------Metodos ModelAndView---------------------------//

	public static ModelAndView home(Request req, Response res) {
		autenticar(req, res);
		
		List<Metodologia> metodologiasObtenidas = RepositorioMetodologia.getSingletonInstance()
				.allInstancesUser(autenticar(req, res));

		HashMap<String, List<Metodologia>> mapMetodologias = new HashMap<>();
		mapMetodologias.put("metodologias", metodologiasObtenidas);

		return new ModelAndView(mapMetodologias, "homePage/metodologias.hbs");
	}

	public static ModelAndView agregarNombreView(Request req, Response res) {
		autenticar(req, res);
		condicionesCreadas = new ArrayList<SnapshotCondicion>();
		return new ModelAndView(null, "layoutMetodologiasAgregarNombre.hbs");
	}

	public static ModelAndView agregarCondicionesView(Request req, Response res) {
		errorAgregarCondicion = null;
		autenticar(req, res);
		
		recuperoParametrosCondicion(req);
		agregarCondicionCreada();

		return new ModelAndView(mapeoCondiciones(autenticar(req, res)), "layoutMetodologiasAgregarCondiciones.hbs");
	}

	public static ModelAndView consultarView(Request req, Response res) {
		autenticar(req, res);

		List<RankingEmpresa> rEmpresas = new ArrayList<RankingEmpresa>();
		RepositorioEmpresa.getInstance().allInstancesUser(autenticar(req, res)).stream()
				.forEach(empresa -> rEmpresas.add(new RankingEmpresa(empresa)));

		Metodologia metodologia = RepositorioMetodologia.getSingletonInstance()
				.buscar(Long.parseLong(req.params("metodologiaId")));

		ControladorDeMetodologia contrMet = new ControladorDeMetodologia(metodologia, rEmpresas);
		return new ModelAndView(mapeoConsultarMetodologia(contrMet), "layoutMetodologiasConsultar.hbs");
	}

	// ---------------------------Metodos POST---------------------------//

	public Void agregarNombre(Request req, Response res) {
		try {
			nombreMetodologiaSeleccionado = Objects.isNull(req.queryParams("nombreMetodologiaSeleccionado"))
					|| req.queryParams("nombreMetodologiaSeleccionado").isEmpty() ? null
							: req.queryParams("nombreMetodologiaSeleccionado");
			res.redirect("/metodologias/nuevaCondicion");
		} catch (Exception e) {
			res.redirect("/metodologias/nuevaMetodologia");
		}
		return null;
	}

	public Void reiniciar(Request req, Response res) {
		condicionesCreadas = new ArrayList<SnapshotCondicion>();
		errorAgregarCondicion = null;
		errorCrearMetodologia = null;
		res.redirect("/metodologias/nuevaCondicion");
		return null;
	}

	public Void agregarMetodologia(Request req, Response res) {
		try {
			List<Condicion> condiciones = new ArrayList<Condicion>();
			condicionesCreadas.stream()
					.forEach(snapshotCondicion -> condiciones.add(new CondicionesBuilder().crear(snapshotCondicion)));
			Metodologia metodologia = new Metodologia(nombreMetodologiaSeleccionado, condiciones,
					RepositorioUsuario.getSingletonInstance().buscar(autenticar(req, res)));
			RepositorioMetodologia.getSingletonInstance().agregar(metodologia);

			condicionesCreadas = new ArrayList<SnapshotCondicion>();
			nombreMetodologiaSeleccionado = null;

			res.redirect("/metodologias");
		} catch (Exception e) {
			res.cookie("errorCrearMetodologia", "Error al crear la metodologia: " + e.getMessage(), 5);
			res.redirect("/metodologias/nuevaCondicion");
		}
		return null;
	}

	public Void delete(Request req, Response res) {
		String idMetodologia = req.params("metodologiaId");
		RepositorioMetodologia.getSingletonInstance().eliminar(Long.parseLong(idMetodologia));
		res.redirect("/metodologias");
		return null;
	}

	// ---------------------------Otros Metodos---------------------------//

	public static HashMap<String, Object> mapeoConsultarMetodologia(ControladorDeMetodologia controlador) {
		HashMap<String, Object> mapConsultaMetodologias = new HashMap<>();
		List<SnapshotRankingEmpresa> empresasOk = controlador.obtenerSnapshotRankingEmpresas();
		List<SnapshotRankingEmpresa> empresasError = controlador.obtenerSnapshotRankingEmpresasFallidas();

		mapConsultaMetodologias.put("resultadoOk", empresasOk);
		mapConsultaMetodologias.put("resultadoError", empresasError);
		return mapConsultaMetodologias;
	}

	public static void recuperoParametrosCondicion(Request req) {
		errorCrearMetodologia = req.cookie("errorCrearMetodologia");
		indicadorSeleccionado = Objects.isNull(req.queryParams("indicadorSeleccionado"))
				|| req.queryParams("indicadorSeleccionado").isEmpty() ? null : req.queryParams("indicadorSeleccionado");
		tipoCondicionSeleccionado = Objects.isNull(req.queryParams("tipoCondicionSeleccionado"))
				|| req.queryParams("tipoCondicionSeleccionado").isEmpty() ? null
						: req.queryParams("tipoCondicionSeleccionado");
		condicionSeleccionada = Objects.isNull(req.queryParams("condicionSeleccionada"))
				|| req.queryParams("condicionSeleccionada").isEmpty() ? null : req.queryParams("condicionSeleccionada");
		pesoOCompararSeleccionado = Objects.isNull(req.queryParams("pesoOCompararSeleccionado"))
				|| req.queryParams("pesoOCompararSeleccionado").isEmpty() ? null
						: BigDecimal.valueOf(Long.parseLong(req.queryParams("pesoOCompararSeleccionado")));
		ultimosAniosSeleccionado = Objects.isNull(req.queryParams("ultimosAniosSeleccionado"))
				|| req.queryParams("ultimosAniosSeleccionado").isEmpty() ? 0
						: Integer.parseInt(req.queryParams("ultimosAniosSeleccionado"));
	}

	public static HashMap<String, Object> mapeoCondiciones(Long idUsuario) {
		HashMap<String, Object> mapAMetod = new HashMap<>();
		mapAMetod.put("condiciones", listaCondiciones());
		mapAMetod.put("tipoCondiciones", listaTiposCondiciones());
		mapAMetod.put("indicadores", repoInd.todosLosNombresDeIndicadores(repoInd.allInstancesUser(idUsuario)));
		mapAMetod.put("condicionesCreadas", condicionesCreadas);
		mapAMetod.put("nombreMetodologia", nombreMetodologiaSeleccionado);
		mapAMetod.put("errorCrearMetodologia", errorCrearMetodologia);
		mapAMetod.put("errorAgregarCondicion", errorAgregarCondicion);
		return mapAMetod;
	}

	public static void agregarCondicionCreada() {
		try {
			validar();
			condicionesCreadas.add(new SnapshotCondicion(tipoCondicionSeleccionado, condicionSeleccionada,
					indicadorSeleccionado, pesoOCompararSeleccionado, ultimosAniosSeleccionado));
		} catch (RuntimeException e) {
			if (!(Objects.isNull(condicionSeleccionada) && Objects.isNull(indicadorSeleccionado)
					&& Objects.isNull(tipoCondicionSeleccionado) && ultimosAniosSeleccionado == 0
					&& Objects.isNull(pesoOCompararSeleccionado)))
				errorAgregarCondicion = "La ultima condicion ingresada no cumple las validaciones necesarias. Intentelo nuevamente.";
		}
	}

	public static void validar() throws RuntimeException {

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

}
