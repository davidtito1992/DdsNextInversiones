package db;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Cuenta;
import model.Empresa;
import model.Periodo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class PersistenciaTest extends AbstractPersistenceTest implements
		WithGlobalEntityManager {

	EntityManagerFactory emf;
	EntityManager em;

	Empresa faceb = new Empresa();
	Empresa google = new Empresa();
	Periodo periodo1 = new Periodo();
	Periodo periodo2 = new Periodo();
	List<Periodo> periodos = new ArrayList<Periodo>();

	Cuenta ebitda = new Cuenta();
	Cuenta fds = new Cuenta();
	Cuenta netoContinuas = new Cuenta();

	@Before
	public void setUp() {

		emf = Persistence.createEntityManagerFactory("db");
		em = emf.createEntityManager();

		ebitda.setNombre("EBITDA");
		ebitda.setValor(new BigDecimal(10));

		fds.setNombre("FDS");
		fds.setValor(new BigDecimal(20));

		netoContinuas.setNombre("NetoContinuas");
		netoContinuas.setValor(new BigDecimal(30));

		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		cuentas.add(ebitda);
		cuentas.add(fds);
		cuentas.add(netoContinuas);

		List<Cuenta> cuentas2 = new ArrayList<Cuenta>();
		cuentas2.add(ebitda);

		periodo1.setAnio(Year.of(2016));
		periodo1.setCuentas(cuentas);

		periodo2.setAnio(Year.of(2014));
		periodo2.setCuentas(cuentas2);

		Empresa faceb = new Empresa();
		faceb.setNombre("Facebook");
		faceb.setPeriodos(periodos);
	}

	@Test
	public void persistenciaDeCuentas() {

		EntityTransaction tx = em.getTransaction();

		tx.begin();
		em.persist(ebitda);
		em.persist(fds);
		em.persist(netoContinuas);
		tx.commit();

		Cuenta ebitdaEncontrado = em.find(Cuenta.class, ebitda.getCuentaId());
		Cuenta fdsEncontrado = em.find(Cuenta.class, fds.getCuentaId());
		Cuenta netoContinuasEncontrado = em.find(Cuenta.class,
				netoContinuas.getCuentaId());

		assertEquals(ebitda, ebitdaEncontrado);
		assertEquals(fds, fdsEncontrado);
		assertEquals(netoContinuas, netoContinuasEncontrado);
	}

	@Test
	public void persistenciaDeEmpresas() {

		EntityTransaction tx = em.getTransaction();

		tx.begin();
		em.persist(faceb);
		em.persist(google);
		tx.commit();

		Empresa facebEncontrado = em.find(Empresa.class, faceb.getEmpresaId());
		Empresa googleEncontrado = em
				.find(Empresa.class, google.getEmpresaId());

		assertEquals(faceb, facebEncontrado);
		assertEquals(google, googleEncontrado);
	}

	@Test
	public void persistenciaDePeriodos() {

		EntityTransaction tx = em.getTransaction();

		tx.begin();
		em.persist(periodo1);
		em.persist(periodo2);
		tx.commit();

		Periodo periodo1Encontrado = em.find(Periodo.class,
				periodo1.getPeriodoId());
		Periodo periodo2Encontrado = em.find(Periodo.class,
				periodo2.getPeriodoId());

		assertEquals(periodo1, periodo1Encontrado);
		assertEquals(periodo2, periodo2Encontrado);
	}

	@After
	public void tearDown() {
		em.close();
		emf.close();
	}

}
