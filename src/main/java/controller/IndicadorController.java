package controller;

import java.util.Map;

import main.app.Token;
import service.IndicadorService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadorController {

	public IndicadorController() {
	}

	public static ModelAndView home(Request req, Response res) {
		Token.autenticar(req, res);
		Map<String, Object> mapIndicadores = IndicadorService.homeView(
				Token.autenticar(req, res),
				req.cookie("errorAgregarIndicador"));
		return new ModelAndView(mapIndicadores, "homePage/indicadores.hbs");
	}

	public static ModelAndView agregarView(Request req, Response res) {
		Token.autenticar(req, res);
		return new ModelAndView(null, "homePage/nuevoIndicador.hbs");
	}

	public static Void agregar(Request req, Response res) {
		try {
			IndicadorService
					.agregar(req.queryParams("nombre"),
							req.queryParams("formula"),
							Token.autenticar(req, res));
		} catch (Exception e) {
			res.cookie(
					"errorAgregarIndicador",
					"No se pudo guardar el indicador: '" + e.getMessage() + "'",
					5);
		}
		res.redirect("/indicadores");
		return null;
	}

	public static Void delete(Request req, Response res) {
		IndicadorService.delete(req.params("indicadorId"));
		res.redirect("/indicadores");
		return null;
	}

	public static ModelAndView consultarView(Request req, Response res) {

		Map<String, Object> mapIndicadores = IndicadorService.consultarView(
				req.params("indicadorId"), Token.autenticar(req, res));
		return new ModelAndView(mapIndicadores,
				"layoutIndicadoresConsultar.hbs");

	}

}
