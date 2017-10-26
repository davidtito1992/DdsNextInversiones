package controller;

import main.app.AppData;
import main.app.DslIndicador;
import main.converter.SnapshotIndicadorConverter;
import main.repositories.RepositorioIndicador;
import main.repositories.RepositorioUsuario;
import model.RegistroIndicador;
import model.SnapshotIndicador;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IndicadorController extends Controller {

	public IndicadorController() {
	}

	public static ModelAndView home(Request req, Response res) {
		autenticar(req, res);

		SnapshotIndicadorConverter snapshotIndicadorConverter = new SnapshotIndicadorConverter();

		HashMap<String, Object> mapIndicadores = new HashMap<>();

		List<RegistroIndicador> indicadoresObtenidas = autenticar(req, res) != null
				? RepositorioIndicador.getSingletonInstance().allInstancesUser(autenticar(req, res))
				: new ArrayList<>();
		mapIndicadores.put("indicadores", indicadoresObtenidas);

		List<SnapshotIndicador> snapshots = snapshotIndicadorConverter.allSnapshotIndicadores(autenticar(req, res));
		mapIndicadores.put("snapshots", snapshots);
		mapIndicadores.put("listaVacia", snapshots.isEmpty());
		mapIndicadores.put("errorAgregar", req.cookie("errorAgregarIndicador"));

		return new ModelAndView(mapIndicadores, "homePage/indicadores.hbs");
	}

	public static ModelAndView agregarView(Request req, Response res) {
		autenticar(req, res);

		return new ModelAndView(null, "homePage/nuevoIndicador.hbs");
	}

	public static Void agregar(Request req, Response res) {
		try {
			RegistroIndicador nuevoIndicador = new RegistroIndicador(req.queryParams("nombre"),
					req.queryParams("formula"));
			nuevoIndicador.setUser(RepositorioUsuario.getSingletonInstance().buscar(autenticar(req, res)));
			new DslIndicador().añadirIndicador(nuevoIndicador);
		} catch (Exception e) {
			res.cookie("errorAgregarIndicador", "No se pudo guardar el indicador: '" + e.getMessage() + "'", 5);
		}
		res.redirect("/indicadores");
		return null;
	}

	public static Void delete(Request req, Response res) {
		AppData appData = new AppData();
		String idIndicador = req.params("indicadorId");
		RegistroIndicador aBorrar = RepositorioIndicador.getSingletonInstance().buscar(Long.parseLong(idIndicador));
		appData.borrarIndicador(aBorrar);
		res.redirect("/indicadores");
		return null;
	}

	public static ModelAndView consultarView(Request req, Response res) {

		SnapshotIndicadorConverter snapshotIndicadorConverter = new SnapshotIndicadorConverter();
		HashMap<String, Object> mapIndicadores = new HashMap<>();

		RegistroIndicador indicador = RepositorioIndicador.getSingletonInstance()
				.buscar(Long.parseLong(req.params("indicadorId")));

		List<SnapshotIndicador> snapshots = snapshotIndicadorConverter.snapshotsOf(autenticar(req, res), indicador);
		mapIndicadores.put("snapshots", snapshots);

		return new ModelAndView(mapIndicadores, "layoutIndicadoresConsultar.hbs");

	}

}
