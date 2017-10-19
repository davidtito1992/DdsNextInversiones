package controller;

import main.app.AppData;
import main.app.DslIndicador;
import main.repositories.RepositorioIndicador;
import main.repositories.RepositorioUsuario;
import main.viewmodel.ConsultarIndicadorViewM;
import model.RegistroIndicador;
import model.SnapshotIndicador;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IndicadorController extends Controller{

	private AppData appData = new AppData();
	private static ConsultarIndicadorViewM indicadorViewM = new ConsultarIndicadorViewM();

	public IndicadorController() {
	}

	public static ModelAndView home(Request req, Response res) {
		if (autenticar(req,res) != null) {
			HashMap<String, Object> mapIndicadores = new HashMap<>();

			List<RegistroIndicador> indicadoresObtenidas = autenticar(req,res) != null ? RepositorioIndicador
					.getSingletonInstance().findFromUser(autenticar(req,res))
					: new ArrayList<>();
			mapIndicadores.put("indicadores", indicadoresObtenidas);

			List<SnapshotIndicador> snapshots = indicadorViewM
					.allSnapshotIndicadores(autenticar(req,res));
			mapIndicadores.put("snapshots", snapshots);
			
			mapIndicadores.put("errorAgregar", req.cookie("errorAgregarIndicador"));
			

			return new ModelAndView(mapIndicadores, "homePage/indicadores.hbs");
		} else {
			res.redirect("/");
			return null;
		}
	}
	
	public static ModelAndView agregarView(Request req, Response res) {
		if (autenticar(req,res) != null) {
			return new ModelAndView(null, "homePage/agregarIndicador.hbs");
		} else {
			res.redirect("/");
			return null;
		}
	}
	
	public Void agregar(Request req, Response res) {
		try {
			RegistroIndicador nuevoIndicador = new RegistroIndicador(
					req.queryParams("nombre"), req.queryParams("formula"));
			nuevoIndicador.setUser(RepositorioUsuario.getSingletonInstance()
					.buscar(autenticar(req, res)));
			new DslIndicador().añadirIndicador(nuevoIndicador);
		} catch (Exception e) {
			res.cookie("errorAgregarIndicador", "No se pudo guardar el indicador: '" + e.getMessage() + "'", 5);
		}
		res.redirect("/indicadores");
		return null;
	}

//	public Void redirect(Request req, Response res) {
//		if (autenticar(req,res) != null)
//			res.redirect("/indicadores");
//		else
//			res.redirect("/");
//		return null;
//	}

	public Void delete(Request req, Response res) {
		String idIndicador = req.params("indicadorId");
		RegistroIndicador aBorrar = RepositorioIndicador.getSingletonInstance()
				.buscar(Long.parseLong(idIndicador));
		appData.borrarIndicador(aBorrar);
		res.redirect("/indicadores/");
		return null;
	}
	
	

}
