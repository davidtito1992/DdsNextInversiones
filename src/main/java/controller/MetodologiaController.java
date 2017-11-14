package controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import main.app.TokenUtils;
import model.SnapshotCondicion;
import service.MetodologiaService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiaController {

	public static ModelAndView home(Request req, Response res) {
		TokenUtils.autenticar(req, res);
		Map<String, Object> mapMetodologias = MetodologiaService.homeView(
				TokenUtils.autenticar(req, res));
		return new ModelAndView(mapMetodologias, "homePage/metodologias.hbs");
	}

	public static ModelAndView agregarNombreView(Request req, Response res) {
		TokenUtils.autenticar(req, res);
		return new ModelAndView(null, "layoutMetodologiasAgregarNombre.hbs");
	}

	public static ModelAndView agregarCondicionesView(Request req, Response res) {
		TokenUtils.autenticar(req, res);

		List<SnapshotCondicion> condicionesCreadas = MetodologiaService
				.agregarCondicion(req.cookie("errorCrearMetodologia"),
						req.queryParams("indicadorSeleccionado"),
						req.queryParams("tipoCondicionSeleccionado"),
						req.queryParams("condicionSeleccionada"),
						req.queryParams("pesoOCompararSeleccionado"),
						req.queryParams("ultimosAniosSeleccionado"),
						req.queryParams("nombreMetodologia"),
						req.queryParams("JSONCondiciones"));
		Map<String, Object> condiciones = MetodologiaService.mapeoCondiciones(
				TokenUtils.autenticar(req, res),
				req.queryParams("nombreMetodologia"),
				req.cookie("Notificacion"), condicionesCreadas);

		return new ModelAndView(condiciones,
				"layoutMetodologiasAgregarCondiciones.hbs");
	}

	public static ModelAndView consultarView(Request req, Response res) {
		TokenUtils.autenticar(req, res);
		Map<String, Object> metodologias = MetodologiaService.consultarView(
				TokenUtils.autenticar(req, res), req.params("metodologiaId"));
		return new ModelAndView(metodologias, "layoutMetodologiasConsultar.hbs");
	}

	// ---------------------------Metodos POST---------------------------//
	//
	// public static Void agregarNombre(Request req, Response res) {
	// try {
	// String nombreMetodologia = Objects.isNull(req
	// .queryParams("nombreMetodologiaSeleccionado"))
	// || req.queryParams("nombreMetodologiaSeleccionado")
	// .isEmpty() ? null : req
	// .queryParams("nombreMetodologiaSeleccionado");
	// res.redirect("/metodologias/nuevaMetodologia/" + nombreMetodologia);
	// } catch (Exception e) {
	// res.redirect("/metodologias/nuevaMetodologia");
	// }
	// return null;
	// }

	public static Void reiniciar(Request req, Response res) {
		MetodologiaService.reiniciar();
		res.redirect("/metodologias/nuevaMetodologia");
		return null;
	}

	public static Void agregarMetodologia(Request req, Response res) {

		try {
			MetodologiaService.agregarMetodologia(TokenUtils.autenticar(req, res),
					req.params("nombreMetodologia"),
					req.queryParams("JSONCondiciones"));
			res.cookie("Notificacion",
					"Metodologia :" + req.params("nombreMetodologia")
							+ " creada exitosamente!", 5);

		} catch (Exception e) {
			res.cookie(
					"Notificacion",
					"Error al crear la metodologia: "
							+ req.params("nombreMetodologia"), 5);

		}
		res.redirect("/metodologias/nuevaMetodologia");
		return null;

	}

	public static Void delete(Request req, Response res) {
        
		MetodologiaService.eliminar(req.params("metodologiaId"));

		res.redirect("/metodologias");
		return null;
	}

}
