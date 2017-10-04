package repositories;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import model.Cuenta;
import model.Empresa;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.uqbar.commons.utils.Observable;

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

	/********* METODOS *********/

//	@SuppressWarnings("unchecked")
//	@Transactional
//	public List<Empresa> allInstances() {
//
//		/*
//		 * String query = "FROM Empresa e";
//		 * 
//		 * Query q2 = entityManager.createQuery(query); return
//		 * q2.getResultList();
//		 */
//		return entityManager.unwrap(Session.class)
//				.createCriteria(Empresa.class).list();
//
//	}

	@SuppressWarnings("unchecked")
	public List<Empresa> filtrar(String cuentaSeleccionada,
			String nombreSeleccionado, Integer semestreSeleccionado,
			Year anioSeleccionado) {

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
		/*
		 * ArrayList<Integer> todosLosPeriodos = listaEmpresa.stream()
		 * .map(empresa -> empresa.getPeriodos()) .flatMap(periodos ->
		 * periodos.stream()) .map(periodo ->
		 * periodo.getSemestre()).distinct().sorted()
		 * .collect(Collectors.toCollection(ArrayList::new));
		 */

		return todosLosPeriodos;
	}

	public ArrayList<String> todosLosNombresDeCuentas(
			List<Empresa> listaEmpresas) {

		String query = "SELECT DISTINCT c.nombre FROM Cuenta  c";

		ArrayList<String> nombresDeTodasLasCuentas = (ArrayList<String>) entityManager
				.createQuery(query, String.class).getResultList();
		/*
		 * listaEmpresas.stream() .map(empresa -> empresa.getPeriodos())
		 * .flatMap(periodos -> periodos.stream()) .map(periodo ->
		 * periodo.getCuentas()) .flatMap(cuentas -> cuentas.stream())
		 * .map(cuenta -> cuenta.getNombre()).distinct().sorted()
		 * .collect(Collectors.toCollection(ArrayList::new));
		 */

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

	/*
	 * public List<Cuenta> obtenerCuenta(String nombreSeleccionado, Year
	 * anioSeleccionado, Integer semestreSeleccionado, String nombreCuenta) {
	 * 
	 * List<Cuenta> cuentasADevolver = this .allInstances() .stream()
	 * .filter(empresa -> empresa.getNombre().equalsIgnoreCase(
	 * nombreSeleccionado)) .map(empresa -> empresa.getPeriodos())
	 * .flatMap(periodo -> periodo.stream()) .filter(periodo ->
	 * periodo.getAnio().equals(anioSeleccionado) && periodo.getSemestre() ==
	 * semestreSeleccionado) .map(periodo -> periodo.getCuentas())
	 * .flatMap(cuentas -> cuentas.stream()) .filter(cuenta ->
	 * cuenta.getNombre().equalsIgnoreCase(
	 * nombreCuenta)).collect(Collectors.toList());
	 * 
	 * return cuentasADevolver;
	 * 
	 * }
	 */

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

}