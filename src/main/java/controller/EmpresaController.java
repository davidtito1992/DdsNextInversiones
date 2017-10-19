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
	
	static String nombreSeleccionado;
	static String cuentaSeleccionada;
	static Integer semestreSeleccionado;
	static Year anioSeleccionado;
	static RepositorioEmpresa repo = RepositorioEmpresa.getInstance();
	
	public static ModelAndView home(Request req, Response res) {
		cuentaSeleccionada = Objects.isNull(req.queryParams("nombreCuenta")) || req.queryParams("nombreCuenta").isEmpty() ? null : req.queryParams("nombreCuenta");
		nombreSeleccionado = Objects.isNull(req.queryParams("nombreEmpresa")) || req.queryParams("nombreEmpresa").isEmpty() ? null : req.queryParams("nombreEmpresa");
		anioSeleccionado = Objects.isNull(req.queryParams("anio")) || req.queryParams("anio").isEmpty() ? 
					null : Year.parse(req.queryParams("anio"));
		semestreSeleccionado = Objects.isNull(req.queryParams("semestre")) || req.queryParams("semestre").isEmpty() ?
					null : Integer.parseInt(req.queryParams("semestre"));
				
		return new ModelAndView(armarHashMap(autenticar(req, res)),"homePage/empresas.hbs");
	}
	
	public static HashMap<String, Object> armarHashMap(Long idUsuario){
		
		HashMap<String, Object> mapEmpresas = new HashMap<>();
	
		List<Empresa> todasLasEmpresasDelUsuario = RepositorioEmpresa.getInstance().allInstancesUser(idUsuario);
		List<Empresa> empresasPorFiltros = idUsuario != null ? 
				repo.filtrar(cuentaSeleccionada,nombreSeleccionado,semestreSeleccionado,anioSeleccionado,idUsuario) : new ArrayList<>();
		
		mapEmpresas.put("empresas", repo.dameSnapshotEmpresas(empresasPorFiltros,nombreSeleccionado,cuentaSeleccionada,anioSeleccionado,semestreSeleccionado));
		mapEmpresas.put("nombresCuentas", RepositorioEmpresa.getInstance().
				todosLosNombresDeCuentas(todasLasEmpresasDelUsuario));
		mapEmpresas.put("nombresEmpresas", RepositorioEmpresa.getInstance().
				todosLosNombresDeEmpresas(todasLasEmpresasDelUsuario));
		mapEmpresas.put("anios",RepositorioEmpresa.getInstance().
				todosLosAnios(todasLasEmpresasDelUsuario));
		mapEmpresas.put("periodos",RepositorioEmpresa.getInstance().
				todosLosPeriodos(todasLasEmpresasDelUsuario));
		
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