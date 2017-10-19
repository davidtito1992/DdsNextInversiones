package controller;

import main.condiciones.CondicionesBuilder;
import main.rankingEmpresa.RankingEmpresa;
import main.repositories.RepositorioEmpresa;
import main.repositories.RepositorioIndicador;
import main.repositories.RepositorioMetodologia;
import main.repositories.RepositorioUsuario;
import model.ControladorDeMetodologia;
import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;
import model.SnapshotRankingEmpresa;
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
					.getSingletonInstance().allInstancesUser(usuarioId)
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
			List<RankingEmpresa> rEmpresas = new ArrayList<RankingEmpresa>();
			RepositorioEmpresa.getInstance().allInstancesUser(autenticar(req,res))
				.stream().forEach(empresa -> rEmpresas.add(new RankingEmpresa(empresa)));
			
			Metodologia metodologia = RepositorioMetodologia.
						getSingletonInstance().buscar(Long.parseLong(req.params("metodologiaId")));
			
			ControladorDeMetodologia controlador = new ControladorDeMetodologia(metodologia,rEmpresas);
			
			HashMap<String, List<SnapshotRankingEmpresa>> mapConsultaMetodologias = new HashMap<>();
			List<SnapshotRankingEmpresa> empresasOk = controlador.obtenerSnapshotRankingEmpresas();
			List<SnapshotRankingEmpresa> empresasError = controlador.obtenerSnapshotRankingEmpresasFallidas();
			
			mapConsultaMetodologias.put("resultadoOk", empresasOk);
			mapConsultaMetodologias.put("resultadoError", empresasError);
			return new ModelAndView(mapConsultaMetodologias, "layoutMetodologiasConsultar.hbs");
		} else {
			res.redirect("/");
			return null;
		}
	}
	
	public static ModelAndView agregarView(Request req, Response res) { 
		RepositorioIndicador repoInd = RepositorioIndicador.getSingletonInstance();
		if (autenticar(req,res) != null) {
			HashMap<String, Object> mapAMetod = new HashMap<>();
			mapAMetod.put("condiciones",listaCondiciones());
			mapAMetod.put("tipoCondiciones",listaTiposCondiciones());
			mapAMetod.put("indicadores",repoInd
					.todosLosNombresDeIndicadores(repoInd.allInstancesUser(autenticar(req,res))));

			return new ModelAndView(mapAMetod, "layoutMetodologiasAgregar.hbs");
		} else {
			res.redirect("/");
			return null;
		}
	}
	
	public Void agregarCondicion(Request req, Response res) {
		res.redirect("/metodologias");
		return null;
	}
	
	public static List<String> listaCondiciones(){
		List<String> condiciones = new ArrayList<String>();
		condiciones.add(CondicionesBuilder.MAYOR);
		condiciones.add(CondicionesBuilder.MENOR);
		condiciones.add(CondicionesBuilder.ANTIGUEDAD);
		condiciones.add(CondicionesBuilder.CRECIENTE);
		condiciones.add(CondicionesBuilder.DECRECIENTE);
		return condiciones;
	}
	
	public static List<String> listaTiposCondiciones(){
		List<String> tiposCondiciones = new ArrayList<String>();
		tiposCondiciones.add(CondicionesBuilder.TAXATIVA);
		tiposCondiciones.add(CondicionesBuilder.CUANTITATIVA);
		return tiposCondiciones;
	}
	
	
//	public Void consultar(Request req, Response res){
//
////		res.redirect("/metodologias/consultar"); 
//		return null;
//	}


}
