package controller;

import main.repositories.RepositorioEmpresa;
import main.viewmodel.EmpresaViewM;
import model.Empresa;
import model.SnapshotEmpresa;


import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmpresaController extends Controller{

	private static EmpresaViewM adapter = new EmpresaViewM();
	
	public static ModelAndView home(Request req, Response res) {

		RepositorioEmpresa repo = RepositorioEmpresa.getInstance();

		Long idUsuario = autenticar(req, res);
		List<Empresa> empresasObtenidas = idUsuario != null ? repo
				.findFromUser(idUsuario) : new ArrayList<>();

		return new ModelAndView(armarHashMap(empresasObtenidas),
				"homePage/empresas.hbs");
	}
	
	public static HashMap<String, List<?>> armarHashMap(List<Empresa> empresasObtenidas){
		
		HashMap<String, List<?>> mapEmpresas = new HashMap<>();
		
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
	
}