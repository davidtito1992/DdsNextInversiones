package controller;

import main.app.AppData;
import main.repositories.RepositorioIndicador;
import main.viewmodel.ConsultarIndicadorViewM;
import model.RegistroIndicador;
import model.SnapshotIndicador;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IndicadorController {

	private static LoginController loginController;
	private AppData appData = new AppData();
	private static ConsultarIndicadorViewM indicadorViewM = new ConsultarIndicadorViewM();

	public IndicadorController(LoginController log) {
		IndicadorController.loginController = log;
	}

	public static ModelAndView home(Request req, Response res) {
		if (getIdUsuario() != null) {
			HashMap<String, List<?>> mapIndicadores = new HashMap<>();

			List<RegistroIndicador> indicadoresObtenidas = getIdUsuario() != null ? RepositorioIndicador
					.getSingletonInstance().findFromUser(getIdUsuario())
					: new ArrayList<>();
			mapIndicadores.put("indicadores", indicadoresObtenidas);

			List<SnapshotIndicador> snapshots = indicadorViewM
					.allSnapshotIndicadores(getIdUsuario());
			mapIndicadores.put("snapshots", snapshots);

			return new ModelAndView(mapIndicadores, "homePage/indicadores.hbs");
		} else {
			res.redirect("/");
			return null;
		}
	}

	public Void redirect(Request req, Response res) {
		if (getIdUsuario() != null)
			res.redirect("/indicadores/" + getIdUsuario());
		else
			res.redirect("/");
		return null;
	}

	public Void delete(Request req, Response res) {
		String idIndicador = req.params("indicadorId");
		RegistroIndicador aBorrar = RepositorioIndicador.getSingletonInstance()
				.buscar(Long.parseLong(idIndicador));
		appData.borrarIndicador(aBorrar);
		res.redirect("/indicadores/" + getIdUsuario());
		return null;
	}

	public static Long getIdUsuario() {
		return loginController.getIdUsuario();
	}

}
