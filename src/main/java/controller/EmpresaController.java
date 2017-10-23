package controller;

import main.repositories.RepositorioEmpresa;
import model.Empresa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class EmpresaController extends Controller {

	public static ModelAndView home(Request req, Response res) {
		String cuentaSeleccionada = Objects.isNull(req.queryParams("nombreCuenta"))
				|| req.queryParams("nombreCuenta").isEmpty() ? null : req.queryParams("nombreCuenta");
		String nombreSeleccionado = Objects.isNull(req.queryParams("nombreEmpresa"))
				|| req.queryParams("nombreEmpresa").isEmpty() ? null : req.queryParams("nombreEmpresa");
		Year anioSeleccionado = Objects.isNull(req.queryParams("anio")) || req.queryParams("anio").isEmpty() ? null
				: Year.parse(req.queryParams("anio"));
		Integer semestreSeleccionado = Objects.isNull(req.queryParams("semestre")) || req.queryParams("semestre").isEmpty()
				? null
				: Integer.parseInt(req.queryParams("semestre"));
		
		HashMap<String, Object> mapEmpresas = armarHashMap(
				autenticar(req, res), cuentaSeleccionada, nombreSeleccionado,
				anioSeleccionado, semestreSeleccionado);

		return new ModelAndView(mapEmpresas, "homePage/empresas.hbs");
	}

	public static HashMap<String, Object> armarHashMap(Long idUsuario,
			String cuentaSeleccionada, String nombreSeleccionado,
			Year anioSeleccionado, Integer semestreSeleccionado) {
		HashMap<String, Object> mapEmpresas = new HashMap<>();

		List<Empresa> todasLasEmpresasDelUsuario = RepositorioEmpresa.getInstance().allInstancesUser(idUsuario);
		List<Empresa> empresasPorFiltros = idUsuario != null
				? RepositorioEmpresa.getInstance().filtrar(cuentaSeleccionada, nombreSeleccionado, semestreSeleccionado, anioSeleccionado,
						idUsuario)
				: new ArrayList<>();

		mapEmpresas.put("empresas", RepositorioEmpresa.getInstance().dameSnapshotEmpresas(empresasPorFiltros, nombreSeleccionado,
				cuentaSeleccionada, anioSeleccionado, semestreSeleccionado));
		mapEmpresas.put("nombresCuentas",
				RepositorioEmpresa.getInstance().todosLosNombresDeCuentas(todasLasEmpresasDelUsuario));
		mapEmpresas.put("nombresEmpresas",
				RepositorioEmpresa.getInstance().todosLosNombresDeEmpresas(todasLasEmpresasDelUsuario));
		mapEmpresas.put("anios", RepositorioEmpresa.getInstance().todosLosAnios(todasLasEmpresasDelUsuario));
		mapEmpresas.put("periodos", RepositorioEmpresa.getInstance().todosLosPeriodos(todasLasEmpresasDelUsuario));

		return mapEmpresas;
	}

}