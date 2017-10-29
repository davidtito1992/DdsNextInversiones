package controller;

import java.util.Map;

import service.EmpresaService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmpresaController {

	public static ModelAndView home(Request req, Response res) {
		Controller.autenticar(req, res);
		Map<String, Object> mapEmpresas = EmpresaService.homeView(
				req.queryParams("nombreCuenta"),
				req.queryParams("nombreEmpresa"), req.queryParams("anio"),
				req.queryParams("semestre"), Controller.autenticar(req, res));

		return new ModelAndView(mapEmpresas, "homePage/empresas.hbs");

	}

}