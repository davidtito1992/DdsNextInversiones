package domaintest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import repositories.RepositorioEmpresa;
import model.Cuenta;
import model.Empresa;
import model.Periodo;

public class FiltrosUnitariosTest {

	RepositorioEmpresa repo = new RepositorioEmpresa();

	public Empresa CrearEmpresaTest() {
		Empresa emp = new Empresa();
		Periodo per = new Periodo();
		Cuenta cue = new Cuenta();
		List<Cuenta> lCue = new ArrayList<Cuenta>();
		List<Periodo> lPer = new ArrayList<Periodo>();
		cue.setNombre("CTest");
		cue.setValor(10);
		lCue.add(cue);

		per.setAño(2017);
		per.setSemestre(2);
		per.setCuentas(lCue);
		lPer.add(per);
		emp.setNombre("Google");
		emp.setPeriodos(lPer);
		return emp;
	}

	@Test
	public void filtroNombreCorrecto() {
		Empresa emp = CrearEmpresaTest();
		assertEquals(true, repo.filtroNombre("Google", emp));
	}

	@Test
	public void filtroNombreIncorrecto() {
		Empresa emp = CrearEmpresaTest();
		assertEquals(false, repo.filtroNombre("NombreIncorrecto", emp));
	}

	@Test
	public void filtroAnioCorrecto() {
		Empresa emp = CrearEmpresaTest();
		assertEquals(true, repo.filtroAnio(2017, emp));
	}

	@Test
	public void filtroAnioIncorrecto() {
		Empresa emp = CrearEmpresaTest();
		assertEquals(false, repo.filtroAnio(1810, emp));
	}

	@Test
	public void filtroSemestreCorrecto() {
		Empresa emp = CrearEmpresaTest();
		assertEquals(true, repo.filtroSemestre(2, emp));
	}

	@Test
	public void filtroSemestreIncorrecto() {
		Empresa emp = CrearEmpresaTest();
		assertEquals(false, repo.filtroSemestre(97979, emp));
	}

	@Test
	public void filtroCuentaCorrecto() {
		Empresa emp = CrearEmpresaTest();
		assertEquals(true, repo.filtroCuenta("CTest", emp));
	}

	@Test
	public void filtroCuentaIncorrecto() {
		Empresa emp = CrearEmpresaTest();
		assertEquals(false, repo.filtroCuenta("CuentaIncorrecta", emp));
	}

	@Test
	public void filtradoGeneralCorrecto() {
		List<Empresa> lEmp = new ArrayList<Empresa>();
		lEmp.add(CrearEmpresaTest());
		repo.cargarListaEmpresas(lEmp);
		assertEquals(lEmp, repo.filtrar("CTest", "Google", 2, 2017));
	}

	@Test
	public void filtradoGeneralIncorrectoCuenta() {
		List<Empresa> lEmp = new ArrayList<Empresa>();
		List<Empresa> lEmpVacia = new ArrayList<Empresa>();
		lEmp.add(CrearEmpresaTest());
		repo.cargarListaEmpresas(lEmp);
		assertEquals(lEmpVacia,
				repo.filtrar("CuentaIncorrecta", "Google", 2, 2017));
	}

	@Test
	public void filtradoGeneralIncorrectoNombre() {
		List<Empresa> lEmp = new ArrayList<Empresa>();
		List<Empresa> lEmpVacia = new ArrayList<Empresa>();
		lEmp.add(CrearEmpresaTest());
		repo.cargarListaEmpresas(lEmp);
		assertEquals(lEmpVacia,
				repo.filtrar("CTest", "NombreIncorrecto", 2, 2017));
	}

	@Test
	public void filtradoGeneralIncorrectoSemestre() {
		List<Empresa> lEmp = new ArrayList<Empresa>();
		List<Empresa> lEmpVacia = new ArrayList<Empresa>();
		lEmp.add(CrearEmpresaTest());
		repo.cargarListaEmpresas(lEmp);
		assertEquals(lEmpVacia, repo.filtrar("CTest", "Google", 1, 2017));
	}

	@Test
	public void filtradoGeneralIncorrectoAño() {
		List<Empresa> lEmp = new ArrayList<Empresa>();
		List<Empresa> lEmpVacia = new ArrayList<Empresa>();
		lEmp.add(CrearEmpresaTest());
		repo.cargarListaEmpresas(lEmp);
		assertEquals(lEmpVacia, repo.filtrar("CTest", "Google", 2, 1810));
	}

}
