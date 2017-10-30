package controller;

import java.util.Map;

import main.app.Token;
import service.MetodologiaService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiaController {

	public static ModelAndView home(Request req, Response res) {
		Token.autenticar(req, res);
		Map<String, Object> mapMetodologias = MetodologiaService
				.homeView(Token.autenticar(req, res));
		return new ModelAndView(mapMetodologias, "homePage/metodologias.hbs");
	}

	public static ModelAndView agregarNombreView(Request req, Response res) {
		Token.autenticar(req, res);
		return new ModelAndView(null, "layoutMetodologiasAgregarNombre.hbs");
	}

	public static ModelAndView agregarCondicionesView(Request req, Response res) {
		Token.autenticar(req, res);
		
		String aaa = req.queryParams("nombreMetodologia");
		MetodologiaService.agregarCondicion(
				req.cookie("errorCrearMetodologia"),
				req.queryParams("indicadorSeleccionado"),
				req.queryParams("tipoCondicionSeleccionado"),
				req.queryParams("condicionSeleccionada"),
				req.queryParams("pesoOCompararSeleccionado"),
				req.queryParams("ultimosAniosSeleccionado"));
		Map<String, Object> condiciones = MetodologiaService
				.mapeoCondiciones(Token.autenticar(req, res));

		return new ModelAndView(condiciones,
				"layoutMetodologiasAgregarCondiciones.hbs");
	}

	public static ModelAndView consultarView(Request req, Response res) {
		Token.autenticar(req, res);
		Map<String, Object> metodologias = MetodologiaService.consultarView(
				Token.autenticar(req, res), req.params("metodologiaId"));
		return new ModelAndView(metodologias, "layoutMetodologiasConsultar.hbs");
	}

	// ---------------------------Metodos POST---------------------------//

	public static Void agregarNombre(Request req, Response res) {
		try {
			MetodologiaService.verificarNombreMetodologia(req
					.queryParams("nombreMetodologiaSeleccionado"));
			res.redirect("/metodologias/nuevaCondicion");
		} catch (Exception e) {
			res.redirect("/metodologias/nuevaMetodologia");
		}
		return null;
	}

	public static Void reiniciar(Request req, Response res) {
		MetodologiaService.reiniciar();
		res.redirect("/metodologias/nuevaCondicion");
		return null;
	}

	public static Void agregarMetodologia(Request req, Response res) {
		try {
			MetodologiaService.agregarMetodologia(Token.autenticar(req,
					res));
			res.redirect("/metodologias");
		} catch (Exception e) {
			res.cookie("errorCrearMetodologia",
					"Error al crear la metodologia: " + e.getMessage(), 5);
			res.redirect("/metodologias/nuevaCondicion");
		}
		return null;
	}

	public static Void delete(Request req, Response res) {
		MetodologiaService.eliminar(req.params("metodologiaId"));
		res.redirect("/metodologias");
		return null;
	}

}
