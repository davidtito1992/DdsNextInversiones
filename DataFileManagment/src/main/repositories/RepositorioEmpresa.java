package main.repositories;

import main.db.EntityManagerHelper;
import model.Cuenta;
import model.Empresa;
import model.Periodo;
import model.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.uqbar.commons.utils.Observable;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Observable
public class RepositorioEmpresa extends Repository<Empresa> {

	private static RepositorioEmpresa repositorioEmpresa;

	private RepositorioEmpresa() {
		super(Empresa.class);
	}

	public static RepositorioEmpresa getInstance() {
		if (repositorioEmpresa == null) {
			repositorioEmpresa = new RepositorioEmpresa();
		}
		return repositorioEmpresa;
	}

	/********* METODOS 
	 * @throws Exception *********/
	
	
	
	public void Actualizar(Empresa empresa){
		if (!Objects.isNull(empresa.getEmpresaId())){ //En el constructor de Empresa asigno empresaId en null si la empresa no existe para el usuario
			actualizarPeriodos(empresa);
		} else {
			EntityManagerHelper.beginTransaction();
			entityManager.persist(empresa);
			EntityManagerHelper.commit();
		}
	}
	
	public void actualizarPeriodos(Empresa empresa){
		try{
			empresa.getPeriodos()
			.forEach(periodo -> {
				if(existePeriodo(empresa, periodo)){
					actualizarCuentas(empresa, periodo, empresa.getNombre(), empresa.getUser().getEmail());
				}else{
					EntityManagerHelper.beginTransaction();
//					entityManager.persist(periodo);//no se esta persisitiendo la relacion con la empresa padre, pero si esta guardando las cuentas hijas correctamente	
					Empresa empresaGuardada = this.buscar(empresa.getEmpresaId());
					List<Periodo> periodosGuardados = empresaGuardada.getPeriodos();
					periodosGuardados.add(periodo);
					empresaGuardada.setPeriodos(periodosGuardados);
					EntityManagerHelper.commit();
				}
			});
		}catch (Exception e)
		{
			String aaa = e.getLocalizedMessage();
		}
	}
	
	public boolean existePeriodo(Empresa empresa, Periodo periodo){
		return !Objects.isNull(getPeriodo(empresa.getNombre(), empresa.getUser().getEmail(), periodo));
	}
	
	public void actualizarCuentas(Empresa empresa, Periodo periodo, String nombreEmpresa, String userMail){
		periodo.getCuentas().forEach(cuenta -> {
			Cuenta cuentaGuardada = this.getCuenta(nombreEmpresa, userMail, periodo.getAnio(), periodo.getSemestre(), cuenta);
			if (!Objects.isNull(cuentaGuardada)){
				EntityManagerHelper.beginTransaction();
				cuentaGuardada.setValor(cuenta.getValor());
				EntityManagerHelper.commit();
			}else{
				EntityManagerHelper.beginTransaction();
				Periodo periodoGuardado = getPeriodo(nombreEmpresa, userMail, periodo);
				List<Cuenta> cuentasGuardadas = periodoGuardado.getCuentas();
				cuentasGuardadas.add(cuenta);
				periodoGuardado.setCuentas(cuentasGuardadas);
				EntityManagerHelper.commit();
			}
		});
	}
	
	public Cuenta getCuenta(String nombreEmpresa, String userMail, Year anio, Integer semestre, Cuenta cuenta){
		try{
			String query = "SELECT c FROM Empresa e INNER JOIN e.periodos p INNER JOIN p.cuentas c INNER JOIN e.user u "
					+ "WHERE e.nombre = :nombreEmpresa "
					+ "AND p.anio = :anio "
					+ "AND p.semestre = :semestre "
					+ "AND u.email = :userMail "
					+ "AND c.nombre = :nombreCuenta";
			TypedQuery<Cuenta> q2 = entityManager.createQuery(query, Cuenta.class)
					.setParameter("nombreEmpresa", nombreEmpresa)
					.setParameter("anio", anio)
					.setParameter("semestre", semestre)
					.setParameter("userMail", userMail)
					.setParameter("nombreCuenta", cuenta.getNombre());
			return q2.getSingleResult();
		}
		catch (NoResultException e){
			return null;
		}
	}
	
	public Periodo getPeriodo(String nombreEmpresa, String userMail, Periodo periodo){
		try{	
			String query = "SELECT p FROM Empresa e INNER JOIN e.periodos p INNER JOIN e.user u "
					+ "WHERE e.nombre = :nombreEmpresa "
					+ "AND p.anio = :anio "
					+ "AND p.semestre = :semestre "
					+ "AND u.email = :userMail";
			TypedQuery<Periodo> q2 = entityManager.createQuery(query, Periodo.class)
					.setParameter("nombreEmpresa", nombreEmpresa)
					.setParameter("anio", periodo.getAnio())
					.setParameter("semestre", periodo.getSemestre())
					.setParameter("userMail", userMail);
			return q2.getSingleResult();
		}
		catch (NoResultException e){
			return null;
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<Empresa> filtrar(String cuentaSeleccionada,
			String nombreSeleccionado, Integer semestreSeleccionado,
			Year anioSeleccionado, Long idUsuario) {

		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(
				Empresa.class);
		if (nombreSeleccionado != null) {
			criteria.add(Restrictions.eq("nombre", nombreSeleccionado));
		}
		criteria.createAlias("periodos", "periodo");
		if (anioSeleccionado != null) {
			criteria.add(Restrictions.eq("periodo.anio", anioSeleccionado));
		}
		if (semestreSeleccionado != null) {
			criteria.add(Restrictions.eq("periodo.semestre",
					semestreSeleccionado));
		}
		criteria.createAlias("periodo.cuentas", "cuenta");
		if (cuentaSeleccionada != null) {
			criteria.add(Restrictions.eq("cuenta.nombre", cuentaSeleccionada));
		}
		
		criteria.createAlias("user", "us");
		if (idUsuario != null) {
			criteria.add(Restrictions.eq("us.userId", idUsuario));
		}

		return (List<Empresa>) criteria.setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY).list();
	}
	
	public ArrayList<Year> todosLosAnios(List<Empresa> listaEmpresas) {

		ArrayList<Year> todosLosAnios = listaEmpresas.stream()
				.map(empresa -> empresa.getPeriodos())
				.flatMap(periodos -> periodos.stream())
				.map(periodo -> periodo.getAnio()).distinct().sorted()
				.collect(Collectors.toCollection(ArrayList::new));

		return todosLosAnios;
	}

	public ArrayList<Integer> todosLosPeriodos(List<Empresa> listaEmpresas) {
		String query = "SELECT DISTINCT p.semestre FROM Periodo p";

		ArrayList<Integer> todosLosPeriodos = (ArrayList<Integer>) entityManager
				.createQuery(query, Integer.class).getResultList();

		return todosLosPeriodos;
	}

	public ArrayList<String> todosLosNombresDeCuentas(
			List<Empresa> listaEmpresas) {

		String query = "SELECT DISTINCT c.nombre FROM Cuenta  c";

		ArrayList<String> nombresDeTodasLasCuentas = (ArrayList<String>) entityManager
				.createQuery(query, String.class).getResultList();

		return nombresDeTodasLasCuentas;

	}

	@SuppressWarnings("unchecked")
	public List<String> todosLosNombresDeEmpresas(List<Empresa> empresas) {
		Criteria criteria = entityManager.unwrap(Session.class)
				.createCriteria(Empresa.class)
				.setProjection(Projections.property("nombre"));
		return (List<String>) criteria.list();
	}

	public BigDecimal getValorCuenta(String nombreEmpresa, Year anio,
			int semestre, String nombreCuenta) {
		Cuenta cuentaADevolver;
		try {
			cuentaADevolver = this.obtenerCuenta(nombreEmpresa, anio, semestre,
					nombreCuenta);
		} catch (Exception e) {
			throw new RuntimeException(
					"No pudimos obtener el valor de la variable: "
							+ nombreCuenta);
		}
		return cuentaADevolver.getValor();

	}

	public boolean esCuenta(String nombreCuenta) {

		String query = "SELECT c FROM Cuenta c WHERE c.nombre = :nombreCuenta";

		Query q2 = entityManager.createQuery(query).setParameter(
				"nombreCuenta", nombreCuenta);

		if (!q2.getResultList().isEmpty()) {
			return true;
		}
		return false;

	}

	public Cuenta obtenerCuenta(String nombreSeleccionado,
			Year anioSeleccionado, Integer semestreSeleccionado,
			String nombreCuenta) {
		String query = "SELECT c FROM Empresa e INNER JOIN e.periodos p INNER JOIN p.cuentas c "
				+ "WHERE e.nombre = :nombreEmpresa "
				+ "AND p.anio = :anio "
				+ "AND p.semestre = :semestre "
				+ "AND c.nombre = :nombreCuenta";
		TypedQuery<Cuenta> q2 = entityManager.createQuery(query, Cuenta.class)
				.setParameter("nombreEmpresa", nombreSeleccionado)
				.setParameter("anio", anioSeleccionado)
				.setParameter("semestre", semestreSeleccionado)
				.setParameter("nombreCuenta", nombreCuenta);
		return q2.getSingleResult();
	}

	public Empresa getEmpresa(String nombreEmpresa) {
		Criteria criteria = entityManager.unwrap(Session.class)
				.createCriteria(Empresa.class)
				.add(Restrictions.eq("nombre", nombreEmpresa));
		return (Empresa) criteria.uniqueResult();
	}
	
	public Empresa getEmpresaUser(String nombre, String email) {
		String query = "SELECT e FROM Empresa e INNER JOIN e.user u "
				+ "WHERE e.nombre = :nombreEmpresa "
				+ "AND u.email = :email ";
		TypedQuery<Empresa> q2 = entityManager.createQuery(query, Empresa.class)
				.setParameter("nombreEmpresa", nombre)
				.setParameter("email", email);
		return q2.getSingleResult();
	}
	
	public Empresa getEmpresaIdUser(Long idEmpresa, String email) {
		String query = "SELECT e FROM Empresa e INNER JOIN e.user u "
				+ "WHERE e.nombre = :idEmpresa "
				+ "AND u.email = :email ";
		TypedQuery<Empresa> q2 = entityManager.createQuery(query, Empresa.class)
				.setParameter("idEmpresa", idEmpresa)
				.setParameter("email", email);
		return q2.getSingleResult();
	}
	
	public Empresa getEmpresaOtroUser(Long idEmpresa, String email) {
		String query = "SELECT e FROM Empresa e INNER JOIN e.user u "
				+ "WHERE e.nombre = :idEmpresa "
				+ "AND u.email != :email ";
		TypedQuery<Empresa> q2 = entityManager.createQuery(query, Empresa.class)
				.setParameter("idEmpresa", idEmpresa)
				.setParameter("email", email);
		return q2.getSingleResult();
	}

	public Long getEmpresaId(String nombre, User user) {
		String query = "SELECT e FROM Empresa e INNER JOIN e.user u "
				+ "WHERE e.nombre = :nombre "
				+ "AND u.email = :email ";
		TypedQuery<Empresa> q2 = entityManager.createQuery(query, Empresa.class)
				.setParameter("nombre", nombre)
				.setParameter("email", user.getEmail());
		return q2.getSingleResult().getEmpresaId();
	}

}