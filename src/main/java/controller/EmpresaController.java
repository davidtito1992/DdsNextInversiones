package controller;

import main.repositories.RepositorioEmpresa;
import main.repositories.RepositorioIndicador;
import main.repositories.RepositorioUsuario;
import main.viewmodel.EmpresaViewM;
import model.Empresa;
import model.RegistroIndicador;
import model.SnapshotEmpresa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class EmpresaController extends Controller{

	private static EmpresaViewM adapter = new EmpresaViewM();
	
	public static ModelAndView home(Request req, Response res) {

		RepositorioEmpresa repo = RepositorioEmpresa.getInstance();
		
		String nombreCuenta = Objects.isNull(req.queryParams("nombreCuenta")) || req.queryParams("nombreCuenta").isEmpty() ? null : req.queryParams("nombreCuenta");
		String nombreEmpresa = Objects.isNull(req.queryParams("nombreEmpresa")) || req.queryParams("nombreEmpresa").isEmpty() ? null : req.queryParams("nombreEmpresa");
		Year anio = Objects.isNull(req.queryParams("anio")) || req.queryParams("anio").isEmpty() ? 
					null : Year.parse(req.queryParams("anio"));
		Integer semestre = Objects.isNull(req.queryParams("semestre")) || req.queryParams("semestre").isEmpty() ?
					null : Integer.parseInt(req.queryParams("semestre"));

		Long idUsuario = autenticar(req, res);
		List<Empresa> empresasObtenidas = idUsuario != null ? 
				repo.filtrarLike(nombreCuenta,nombreEmpresa,semestre,anio,idUsuario) : new ArrayList<>();
		
		HashMap<String, Object> mapEmpresas = armarHashMap(empresasObtenidas);
		
		mapEmpresas.put("nombreEmpresaSeleccionado", nombreEmpresa);
		
		return new ModelAndView(mapEmpresas,
				"homePage/empresas.hbs");
	}
	
	public static HashMap<String, Object> armarHashMap(List<Empresa> empresasObtenidas){
		
		HashMap<String, Object> mapEmpresas = new HashMap<>();
		
		mapEmpresas.put("empresas", adapter.dameSnapshotEmpresas(empresasObtenidas));
		mapEmpresas.put("nombresCuentas", RepositorioEmpresa.getInstance().
				todosLosNombresDeCuentas(empresasObtenidas));
		mapEmpresas.put("nombresEmpresas", RepositorioEmpresa.getInstance().
				todosLosNombresDeEmpresas(empresasObtenidas));
		mapEmpresas.put("anios",RepositorioEmpresa.getInstance().
				todosLosAnios(empresasObtenidas));
		mapEmpresas.put("periodos",RepositorioEmpresa.getInstance().
				todosLosPeriodos(empresasObtenidas));
		
		return mapEmpresas;
	}
	
//	public ModelAndView buscar(Request req, Response res){
//		
//		List<Empresa> empresas = RepositorioEmpresa.getInstance().filtrar(req.queryParams("nombreCuenta"), 
//				req.queryParams("nombreEmpresa"), Integer.parseInt(req.queryParams("semestre")), 
//				Year.parse(req.queryParams("anio")));
//		
//		HashMap<String, List<?>> mapEmpresas = new HashMap<>();
//		mapEmpresas.put("empresas", empresas);
//		
//		return new ModelAndView(mapEmpresas,"homePage/empresas.hbs");
//	}
	
}