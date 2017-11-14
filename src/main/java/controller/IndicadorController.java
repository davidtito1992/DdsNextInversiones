package controller;

import java.util.Map;

import main.app.TokenUtils;
import service.IndicadorService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadorController {

	public IndicadorController() {
	}

	public static ModelAndView home(Request req, Response res) {
		Map<String, Object> mapIndicadores = IndicadorService.homeView(TokenUtils
				.autenticar(req, res));
		return new ModelAndView(mapIndicadores, "homePage/indicadores.hbs");
	}

	public static ModelAndView agregarView(Request req, Response res) {
		Map<String, Object> mapIndicadores = IndicadorService
				.homeAgregarIndicador(TokenUtils.autenticar(req, res),
						req.cookie("Notificacion"));
		return new ModelAndView(mapIndicadores, "homePage/nuevoIndicador.hbs");
	}

	public static Void agregar(Request req, Response res) {
		try {
			IndicadorService.agregar(req.queryParams("nombre"),
					req.queryParams("formula"), TokenUtils.autenticar(req, res));
			res.cookie("Notificacion",
					"Indicador: '" + req.queryParams("nombre")
							+ "' creado exitosamente", 5);

		} catch (Exception e) {
			res.cookie(
					"Notificacion",
					"No se pudo guardar el indicador: '" + e.getMessage() + "'",
					5);

		}
		res.redirect("/indicadores/nuevoIndicador");
		return null;
	}

	public static Void delete(Request req, Response res) {
		IndicadorService.delete(req.params("indicadorId"));

		res.cookie("Notificacion",
				"Indicador: '" + req.queryParams("indicadorId")
						+ "' borrado exitosamente", 5);
		res.redirect("/indicadores");
		return null;
	}

	public static ModelAndView consultarView(Request req, Response res) {

		Map<String, Object> mapIndicadores = IndicadorService.consultarView(
				req.params("indicadorId"), TokenUtils.autenticar(req, res));
		return new ModelAndView(mapIndicadores,
				"layoutIndicadoresConsultar.hbs");

	}

}
