package domaintest;

import java.math.BigDecimal;

import model.Empresa;
import model.RegistroIndicador;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import RankingEmpresa.RankingEmpresa;
import app.AppData;
import condiciones.CondicionCrecienteODecreciente.CreceODecrece;
import condiciones.CondicionCuantitativaAntiguedad;
import condiciones.CondicionCrecienteODecreciente;
import condiciones.CondicionCuantitativaMayorOMenorA;
import condiciones.CondicionSumatoria.MenorOMayor;
import condiciones.CondicionTaxativaMayorOMenorA;

public class CondicionesUnitariasTest {

	public RegistroIndicador ingresoNeto;
	public Empresa facebook;
	public Empresa twitter;
	public Empresa google;
	public RankingEmpresa rEmpresaFB;
	public RankingEmpresa rEmpresaTW;
	public RankingEmpresa rEmpresaGO;

	@Before
	public void inicializar() throws Exception {
		new AppData().cargarEmpresas();
		new AppData().cargarIndicadores();

		ingresoNeto = new AppData().getRepositorioIndicadores()
				.getRegistroIndicador("IngresoNeto");
		facebook = new AppData().getRepositorioEmpresas()
				.getEmpresa("Facebook");
		twitter = new AppData().getRepositorioEmpresas().getEmpresa("Twitter");
		google = new AppData().getRepositorioEmpresas().getEmpresa("Google");
		rEmpresaFB = new RankingEmpresa(facebook);
		rEmpresaTW = new RankingEmpresa(twitter);
		rEmpresaGO = new RankingEmpresa(google);
	}

	@Test
	public void cuantitativaMayorATest() throws Exception {
		RankingEmpresa rEmpResul = new CondicionCuantitativaMayorOMenorA(
				MenorOMayor.mayorA, ingresoNeto, 2, new BigDecimal(1))
				.calcular(rEmpresaFB);

		Assert.assertEquals(0,
				BigDecimal.valueOf(16).compareTo(rEmpResul.getRanking()));
	}

	@Test(expected = RuntimeException.class)
	public void taxativaMayorATestERR() throws Exception {
		new CondicionTaxativaMayorOMenorA(MenorOMayor.mayorA, ingresoNeto, 2,
				BigDecimal.valueOf(17)).calcular(rEmpresaFB);

	}

	@Test
	public void taxativaMayorATestOK() throws Exception {
		RankingEmpresa rEmpResul = new CondicionTaxativaMayorOMenorA(
				MenorOMayor.mayorA, ingresoNeto, 2, BigDecimal.valueOf(16))
				.calcular(rEmpresaFB);

		Assert.assertFalse(rEmpResul.getErrorTaxativa());
	}

	@Test(expected = RuntimeException.class)
	public void taxativaMenorATestERR() throws Exception {
		new CondicionTaxativaMayorOMenorA(MenorOMayor.menorA, ingresoNeto, 2,
				BigDecimal.valueOf(15)).calcular(rEmpresaFB);
	}

	@Test
	public void taxativaMenorATestOK() throws Exception {
		RankingEmpresa rEmpResul = new CondicionTaxativaMayorOMenorA(
				MenorOMayor.menorA, ingresoNeto, 2, BigDecimal.valueOf(16))
				.calcular(rEmpresaFB);

		Assert.assertFalse(rEmpResul.getErrorTaxativa());
	}

	@Test
	public void antiguedad() throws Exception {
		RankingEmpresa rEmpResul = new CondicionCuantitativaAntiguedad(
				new BigDecimal(3)).calcular(rEmpresaFB);

		Assert.assertEquals(0,
				BigDecimal.valueOf(3).compareTo(rEmpResul.getRanking()));
	}

	@Test
	public void crecienteOK() throws Exception {
		RankingEmpresa rEmpResul = new CondicionCrecienteODecreciente(
				CreceODecrece.CRECIENTE, ingresoNeto,
				5).calcular(rEmpresaFB);

		Assert.assertFalse(rEmpResul.getErrorTaxativa());
	}

	@Test(expected = RuntimeException.class)
	public void crecienteERR() throws Exception {
		new CondicionCrecienteODecreciente(
				CreceODecrece.CRECIENTE, ingresoNeto,
				5).calcular(rEmpresaTW);
	}

	@Test(expected = RuntimeException.class)
	public void decrecienteERR() throws Exception {
		new CondicionCrecienteODecreciente(
				CreceODecrece.DECRECIENTE,
				ingresoNeto, 5).calcular(rEmpresaFB);
	}

	@Test
	public void decrecienteOK() throws Exception {
		RankingEmpresa rEmpResul = new CondicionCrecienteODecreciente(
				CreceODecrece.DECRECIENTE,
				ingresoNeto, 5).calcular(rEmpresaTW);

		Assert.assertFalse(rEmpResul.getErrorTaxativa());
	}

	@Test
	public void cuantitativaMenorA() throws Exception {
		RankingEmpresa resultado = new CondicionCuantitativaMayorOMenorA(
				MenorOMayor.menorA, ingresoNeto, 5, new BigDecimal(10))
				.calcular(rEmpresaFB);

		Assert.assertEquals(0,
				BigDecimal.valueOf(-160).compareTo(resultado.getRanking()));
	}

	@Test(expected = RuntimeException.class)
	public void cuantitativaMenorAErrNoIndicador() throws Exception {
		new CondicionCuantitativaMayorOMenorA(MenorOMayor.menorA, ingresoNeto, 5,
				new BigDecimal(10)).calcular(rEmpresaGO);
	}

	@Test(expected = RuntimeException.class)
	public void cuantitativaMeAErrNoIndicador() throws Exception {
		new CondicionCrecienteODecreciente(
				CreceODecrece.CRECIENTE, ingresoNeto,
				5).calcular(rEmpresaGO);
	}
}
