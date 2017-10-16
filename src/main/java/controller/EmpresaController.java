package controller;

import main.repositories.RepositorioEmpresa;
import main.viewmodel.EmpresaViewM;
import model.Empresa;
import model.SnapshotEmpresa;

import org.apache.commons.lang3.StringUtils;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmpresaController extends Controller{

	private static EmpresaViewM adapter = new EmpresaViewM();
	
	public EmpresaController() {
	}

	public static ModelAndView home(Request req, Response res) {
		
			HashMap<String, List<SnapshotEmpresa>> mapEmpresas = new HashMap<>();
			Long idUsuario = autenticar(req,res);
			List<Empresa> empresasObtenidas = idUsuario != null ? RepositorioEmpresa
					.getInstance().findFromUser(idUsuario) : new ArrayList<>();
			List<SnapshotEmpresa> snaps = adapter
					.dameSnapshotEmpresas(empresasObtenidas);
			mapEmpresas.put("empresas", snaps);
			return new ModelAndView(mapEmpresas, "homePage/empresas.hbs");
	}
}