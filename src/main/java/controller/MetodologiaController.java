package controller;

import main.repositories.RepositorioMetodologia;
import model.Metodologia;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MetodologiaController extends Controller{

	public MetodologiaController() {
	}

	public static ModelAndView home(Request req, Response res) {
			HashMap<String, List<Metodologia>> mapMetodologias = new HashMap<>();
			Long usuarioId = autenticar(req,res);
			List<Metodologia> metodologiasObtenidas = usuarioId != null ? RepositorioMetodologia
					.getSingletonInstance().findFromUser(usuarioId)
					: new ArrayList<>();
			mapMetodologias.put("metodologias", metodologiasObtenidas);
			return new ModelAndView(mapMetodologias, "homePage/metodologias.hbs");
	}
	
	public Void delete(Request req, Response res) {
		String idMetodologia = req.params("metodologiaId");
		RepositorioMetodologia.getSingletonInstance().eliminar(Long.parseLong(idMetodologia));
		res.redirect("/metodologias/");
		return null;
	}

}
