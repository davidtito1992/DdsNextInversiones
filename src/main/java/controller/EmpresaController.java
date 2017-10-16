package controller;

import java.util.HashMap;
import java.util.List;

import model.Empresa;
import main.repositories.RepositorioEmpresa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmpresaController {

	public static ModelAndView home(Request req, Response res) {
		HashMap<String, List<Empresa>> mapEmpresas = new HashMap<>();
		String idUsuario = req.params("id");
		List<Empresa> empresasObtenidas = RepositorioEmpresa.getInstance()
				.findFromUser(idUsuario);
		mapEmpresas.put("empresa", empresasObtenidas);
		return new ModelAndView(mapEmpresas, "homePage/empresa.hbs");
	}

}
