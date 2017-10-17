package controller;

import main.repositories.RepositorioIndicador;
import main.repositories.RepositorioMetodologia;
import main.repositories.RepositorioUsuario;
import model.Metodologia;
import model.RegistroIndicador;
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
		res.redirect("/metodologias");
		return null;
	}
	
	public static ModelAndView consultarView(Request req, Response res) {
		if (autenticar(req,res) != null) {
			return new ModelAndView(null, "layoutMetodologiasConsultar.hbs");
		} else {
			res.redirect("/");
			return null;
		}
	}
	
	public Void consultar(Request req, Response res){
//		String nombre = req.queryParams("nombre");
//		String formula = req.queryParams("formula");
//		RegistroIndicador nuevoIndicador = new RegistroIndicador();
//		nuevoIndicador.setNombre(nombre);
//		nuevoIndicador.setFormula(formula);
//		nuevoIndicador.setUser(RepositorioUsuario.getSingletonInstance().buscar(autenticar(req,res)));
//		RepositorioIndicador.getSingletonInstance().agregar(nuevoIndicador);
		res.redirect("/metodologias/consultar"); 
		return null;
	}


}
