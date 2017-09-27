package repositories;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import model.Cuenta;
import model.Empresa;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.uqbar.commons.utils.Observable;

import db.EntityManagerHelper;

@SuppressWarnings("rawtypes")
@Observable
public class RepositorioEmpresa extends Repository {

	private static RepositorioEmpresa repositorioEmpresa;

	private RepositorioEmpresa() {
	}

	public static RepositorioEmpresa getInstance() {
		if (repositorioEmpresa == null) {
			repositorioEmpresa = new RepositorioEmpresa();
		}
		return repositorioEmpresa;
	}

	/********* METODOS *********/

	@Transactional
	public void agregarEmpresa(Empresa empresa) {
		EntityManagerHelper.beginTransaction();
		entityManager.merge(empresa);
		EntityManagerHelper.commit();
	}

	@Transactional
	public void eliminarEmpresa(long id) {
		EntityManagerHelper.beginTransaction();
		entityManager.remove(this.buscar(id));
		EntityManagerHelper.commit();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Empresa buscar(long id) {
		return (Empresa) findById(Empresa.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Empresa> allInstances() {
		return entityManager.unwrap(Session.class)
				.createCriteria(Empresa.class).list();
	}

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

	public ArrayList<Integer> todosLosPeriodos(List<Empresa> listaEmpresa) {

		ArrayList<Integer> todosLosPeriodos = listaEmpresa.stream()
				.map(empresa -> empresa.getPeriodos())
				.flatMap(periodos -> periodos.stream())
				.map(periodo -> periodo.getSemestre()).distinct().sorted()
				.collect(Collectors.toCollection(ArrayList::new));

		return todosLosPeriodos;
	}

	public ArrayList<String> todosLosNombresDeCuentas(
			List<Empresa> listaEmpresas) {

		ArrayList<String> nombresDeTodasLasCuentas = listaEmpresas.stream()
				.map(empresa -> empresa.getPeriodos())
				.flatMap(periodos -> periodos.stream())
				.map(periodo -> periodo.getCuentas())
				.flatMap(cuentas -> cuentas.stream())
				.map(cuenta -> cuenta.getNombre()).distinct().sorted()
				.collect(Collectors.toCollection(ArrayList::new));

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

		List<Cuenta> cuentaADevolver = this.obtenerCuenta(nombreEmpresa, anio,
				semestre, nombreCuenta);

		if (cuentaADevolver.isEmpty())

			throw new RuntimeException(
					"No pudimos obtener el valor de la variable: "
							+ nombreCuenta);
		else
			return cuentaADevolver.get(0).getValor();

	}

	public boolean esCuenta(String componente) {
		return this
				.todasLasCuentas()
				.stream()
				.map(cuenta -> cuenta.getNombre())
				.anyMatch(
						nombreCuenta -> nombreCuenta
								.equalsIgnoreCase(componente));
	}

	public List<Cuenta> obtenerCuenta(String nombreSeleccionado,
			Year anioSeleccionado, Integer semestreSeleccionado,
			String nombreCuenta) {

		List<Cuenta> cuentasADevolver = this
				.allInstances()
				.stream()
				.filter(empresa -> empresa.getNombre().equalsIgnoreCase(
						nombreSeleccionado))
				.map(empresa -> empresa.getPeriodos())
				.flatMap(periodo -> periodo.stream())
				.filter(periodo -> periodo.getAnio().equals(anioSeleccionado)
						&& periodo.getSemestre() == semestreSeleccionado)
				.map(periodo -> periodo.getCuentas())
				.flatMap(cuentas -> cuentas.stream())
				.filter(cuenta -> cuenta.getNombre().equalsIgnoreCase(
						nombreCuenta)).collect(Collectors.toList());

		return cuentasADevolver;

	}

	public List<Cuenta> todasLasCuentas() {
		List<Empresa> empresas = this.allInstances();

		List<Cuenta> todasLasCuentas = empresas.stream()
				.map(empresa -> empresa.getPeriodos())
				.flatMap(periodo -> periodo.stream())
				.map(periodo -> periodo.getCuentas())
				.flatMap(cuenta -> cuenta.stream()).distinct()
				.collect(Collectors.toList());

		return todasLasCuentas;
	}

	public Empresa getEmpresa(String nombreEmpresa) {
		Criteria criteria = entityManager.unwrap(Session.class)
				.createCriteria(Empresa.class)
				.add(Restrictions.eq("nombre", nombreEmpresa));
		return (Empresa) criteria.uniqueResult();
	}

}