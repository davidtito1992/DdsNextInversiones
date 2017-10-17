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
		
			HashMap<String, List<SnapshotEmpresa>> mapEmpresas = new HashMap<>();
			RepositorioEmpresa repo = RepositorioEmpresa.getInstance();
			
			Long idUsuario = autenticar(req,res);
			List<Empresa> empresasObtenidas = idUsuario != null ? repo.findFromUser(idUsuario) : new ArrayList<>();
			List<SnapshotEmpresa> snaps = adapter
					.dameSnapshotEmpresas(empresasObtenidas);
			mapEmpresas.put("empresas", snaps);
			
			return new ModelAndView(mapEmpresas, "homePage/empresas.hbs");
	}
}