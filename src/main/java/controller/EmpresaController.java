package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import model.Empresa;
import model.SnapshotEmpresa;
import main.repositories.RepositorioEmpresa;
import main.viewmodel.EmpresaViewM;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmpresaController {
	
	private static EmpresaViewM adapter = new EmpresaViewM();

	public static ModelAndView home(Request req, Response res) {
		HashMap<String, List<SnapshotEmpresa>> mapEmpresas = new HashMap<>();
		String idUsuarioAux = req.params("userId");
		Long idUsuario = idUsuarioAux != null && StringUtils.isNumeric(idUsuarioAux) ? Long.parseLong(idUsuarioAux) : null;
		List<Empresa> empresasObtenidas = idUsuario != null ? RepositorioEmpresa.getInstance().findFromUser(idUsuario) : new ArrayList<>();
		List<SnapshotEmpresa> snaps = adapter.dameSnapshotEmpresas(empresasObtenidas);
		mapEmpresas.put("empresas", snaps);
		return new ModelAndView(mapEmpresas, "homePage/empresas.hbs");
	}

}