package main.repositories;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jface.bindings.keys.ParseException;

import main.app.AplicacionContexto;
import main.app.DslIndicador;
import main.app.Redis;
import main.converter.SnapshotIndicadorConverter;
import main.dataManagment.dataLoader.JsonAdapter;
import main.dataManagment.dataUploader.AdapterToJson;
import model.Empresa;
import model.RegistroIndicador;
import model.SnapshotIndicador;
import model.CacheIndicador;
import redis.clients.jedis.Jedis;

public class RepositorioPrecalculos {

	private static RepositorioPrecalculos repositorioPrecalculos;
	private AdapterToJson adapter = new AdapterToJson();
	private Redis cache = new Redis();

	public static RepositorioPrecalculos getSingletonInstance() {
		if (repositorioPrecalculos == null) {
			repositorioPrecalculos = new RepositorioPrecalculos();
		}
		return repositorioPrecalculos;
	}

	/********* METODOS *********/

	public static List<Long> adaptarJsonAUsuariosId(String jsonUsuarios) {
		JsonAdapter adapter = new JsonAdapter();
		List<Long> usuarios = null;
		try {
			usuarios = adapter.adaptarIds(jsonUsuarios);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public void precalcularIndicador(Long usuarioId, RegistroIndicador indicador) {

		List<Empresa> empresas = this.getRepositorioEmpresas()
				.allInstancesUser(usuarioId);

		empresas.stream().map(
				empresa -> empresa
						.getPeriodos()
						.stream()
						.map(periodo -> {
							this.setValorIndicadorPrecalculado(
									usuarioId,
									indicador.getNombre(),
									empresa.getNombre(),
									periodo.getAnio(),
									periodo.getSemestre(),
									this.calcularIndicador(indicador,
											empresa.getNombre(),
											periodo.getAnio(),
											periodo.getSemestre()));

							return null;

						}));

	}

	/*
	 * public List<SnapshotIndicador> getIndicadoresPrecalculados(Long userId,
	 * String nombreIndicador) { JsonAdapter adapter = new JsonAdapter(); String
	 * json = jedisCache.get(userId + nombreIndicador); return
	 * adapter.adaptarSnapshotIndicadores(json); }
	 */
	public String getValorIndicadorPrecalculado(Long userId,
			String nombreIndicador, String nombreEmpresa, Year anio,
			int semestre) {

		AdapterToJson adapter = new AdapterToJson();

		Jedis jedis = this.cache.getConexion();

		String resultado = jedis.hget(adapter
				.getStringCacheIndicador(new CacheIndicador(userId,
						nombreIndicador, nombreEmpresa, anio, semestre)),
				"calculo");
		jedis.close();
		return resultado;
	}

	public void setValorIndicadorPrecalculado(Long userId,
			String nombreIndicador, String nombreEmpresa, Year anio,
			int semestre, String valorCalculado) {

		AdapterToJson adapter = new AdapterToJson();

		Jedis jedis = this.cache.getConexion();

		jedis.hset(adapter.getStringCacheIndicador(new CacheIndicador(userId,
				nombreIndicador, nombreEmpresa, anio, semestre)), "calculo",
				valorCalculado);
		jedis.close();
	}

	public void precalcularTodosLosIndicadores() {
		this.getRepositorioUsuarios()
				.allInstances()
				.forEach(
						user -> precalcularIndicadorDeUsuario(user.getUserId()));
	}

	public void precalcularIndicadorDeUsuario(Long usuarioId) {
		List<RegistroIndicador> indicadoresObtenidas = usuarioId != null ? RepositorioIndicador
				.getSingletonInstance().allInstancesUser(usuarioId)
				: new ArrayList<>();

		List<Empresa> empresas = this.getRepositorioEmpresas()
				.allInstancesUser(usuarioId);

		indicadoresObtenidas
				.forEach(indicador -> {
					empresas.stream()
							.map(empresa -> empresa
									.getPeriodos()
									.stream()
									.map(periodo -> {

										this.setValorIndicadorPrecalculado(
												usuarioId, indicador
														.getNombre(), empresa
														.getNombre(), periodo
														.getAnio(), periodo
														.getSemestre(),
												this.calcularIndicador(
														indicador,
														empresa.getNombre(),
														periodo.getAnio(),
														periodo.getSemestre()));

										return null;

									}));
				});

	}

	private RepositorioUsuario getRepositorioUsuarios() {
		return AplicacionContexto.getInstance().getInstanceRepoUsuarios();
	}

	public String calcularIndicador(RegistroIndicador indicador,
			String nomEmpresa, Year anio, int semestre) {

		String resultado;
		try {
			resultado = new DslIndicador()
					.prepararFormula(indicador, nomEmpresa, anio, semestre)
					.calcular().toString();
		} catch (main.parserIndicador.ParseException e) {
			resultado = e.getMessage();
		}

		return resultado;
	}

	public void borrarTodo() {
		this.cache.getConexion().flushAll();
	}

	/*
	 * public void actualizarPrecalculos(String jsonUsuarios) { List<Long>
	 * idsUsuarios = adaptarJsonAUsuariosId(jsonUsuarios);
	 * idsUsuarios.forEach(user -> { this.precalcularIndicadorDeUsuario(user); ;
	 * }); }
	 */
	private RepositorioEmpresa getRepositorioEmpresas() {
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}

}
