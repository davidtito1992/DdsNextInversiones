package controller;

import main.app.DslIndicador;
import main.condiciones.Condicion;
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
import model.SnapshotCondicion;
import model.SnapshotRankingEmpresa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MetodologiaController extends Controller{
	
	static String nombreMetodologiaSeleccionado;
	static String indicadorSeleccionado;
	static String tipoCondicionSeleccionado;
	static String condicionSeleccionada;
	static BigDecimal pesoOCompararSeleccionado;
	static int ultimosAniosSeleccionado;
	static List<SnapshotCondicion> condicionesCreadas = new ArrayList<SnapshotCondicion>();
	static RepositorioIndicador repoInd = RepositorioIndicador.getSingletonInstance();

	public MetodologiaController() {
	}
	
	public static ModelAndView agregarNombreView(Request req, Response res) { 
		if (autenticar(req,res) != null) {
			return new ModelAndView(null, "layoutMetodologiasAgregarNombre.hbs");
		} else {
			res.redirect("/");
			return null;
		}		
	}
	
	public Void agregarNombre(Request req, Response res) {
		try {
			nombreMetodologiaSeleccionado = Objects.isNull(req.queryParams("nombreMetodologiaSeleccionado")) || req.queryParams("nombreMetodologiaSeleccionado").isEmpty() ? null : req.queryParams("nombreMetodologiaSeleccionado");
			res.redirect("/metodologias/agregar/condiciones");
		} catch (Exception e) {
			res.redirect("/indicadores/agregar");
		}
		return null;
	}
	
	public Void reiniciar(Request req, Response res){
		condicionesCreadas = new ArrayList<SnapshotCondicion>();
		res.redirect("metodologias/agregar/condiciones");
		return null;
	}
	
	public Void agregarMetodologia(Request req, Response res) {
		try {
			List<Condicion> condiciones = new ArrayList<Condicion>();
			condicionesCreadas.stream().
				forEach(snapshotCondicion -> condiciones.add(new CondicionesBuilder().crear(snapshotCondicion)));
			Metodologia metodologia = new Metodologia(nombreMetodologiaSeleccionado, condiciones);
			RepositorioMetodologia.getSingletonInstance().agregar(metodologia);
			
			condicionesCreadas = new ArrayList<SnapshotCondicion>();
			nombreMetodologiaSeleccionado = null;
			
			res.redirect("/metodologias");
		} catch (Exception e) {
			res.cookie("errorCrearMetodologia", "Error al crear la metodologia: " + e.getMessage(), 5);
			res.redirect("/metodologias/agregar/condiciones");
		}
		return null;
	}
	
	public static ModelAndView agregarCondicionesView(Request req, Response res) { 
		if (autenticar(req,res) != null) {
			
			String errorCrearMetodologia = req.cookie("errorCrearMetodologia");
			indicadorSeleccionado = Objects.isNull(req.queryParams("indicadorSeleccionado")) || req.queryParams("indicadorSeleccionado").isEmpty() ? null : req.queryParams("indicadorSeleccionado");
			tipoCondicionSeleccionado = Objects.isNull(req.queryParams("tipoCondicionSeleccionado")) || req.queryParams("tipoCondicionSeleccionado").isEmpty() ? null : req.queryParams("tipoCondicionSeleccionado");
			condicionSeleccionada = Objects.isNull(req.queryParams("condicionSeleccionada")) || req.queryParams("condicionSeleccionada").isEmpty() ? null : req.queryParams("condicionSeleccionada");
			pesoOCompararSeleccionado = Objects.isNull(req.queryParams("pesoOCompararSeleccionado")) || req.queryParams("pesoOCompararSeleccionado").isEmpty() ? 
					null : BigDecimal.valueOf(Long.parseLong(req.queryParams("pesoOCompararSeleccionado")));
			ultimosAniosSeleccionado = Objects.isNull(req.queryParams("ultimosAniosSeleccionado")) || req.queryParams("ultimosAniosSeleccionado").isEmpty() ?
					0 : Integer.parseInt(req.queryParams("ultimosAniosSeleccionado"));
			
			agregarCondicionCreada();

			return new ModelAndView(armarHashMapCondiciones(autenticar(req,res),errorCrearMetodologia), "layoutMetodologiasAgregarCondiciones.hbs");
		} else {
			res.redirect("/");
			return null;
		}
	}
	
	public static HashMap<String, Object> armarHashMapCondiciones(Long idUsuario, String errorCrearMetodologia){
		HashMap<String, Object> mapAMetod = new HashMap<>();
		mapAMetod.put("condiciones",listaCondiciones());
		mapAMetod.put("tipoCondiciones",listaTiposCondiciones());
		mapAMetod.put("indicadores",repoInd
				.todosLosNombresDeIndicadores(repoInd.allInstancesUser(idUsuario)));
		mapAMetod.put("condicionesCreadas",condicionesCreadas);
		mapAMetod.put("nombreMetodologia",nombreMetodologiaSeleccionado);
		mapAMetod.put("errorCrearMetodologia",errorCrearMetodologia);
		return mapAMetod;
	}
	
	public static void agregarCondicionCreada(){
		if (!(Objects.isNull(indicadorSeleccionado) || Objects.isNull(tipoCondicionSeleccionado) ||
				Objects.isNull(condicionSeleccionada) || Objects.isNull(pesoOCompararSeleccionado))){
			SnapshotCondicion snc = new SnapshotCondicion(tipoCondicionSeleccionado,condicionSeleccionada,
				indicadorSeleccionado,pesoOCompararSeleccionado,ultimosAniosSeleccionado);
			condicionesCreadas.add(snc);
		}
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
