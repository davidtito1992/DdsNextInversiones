//package formulaTeam;
//
//import java.io.StringReader;
//import java.util.List;
//
//import model.Cuenta;
//import model.Empresa;
//import model.RegistroIndicador;
//
//import org.uqbar.commons.utils.ApplicationContext;
//
//import repositories.RepositorioEmpresa;
//import repositories.RepositorioIndicadores;
//import java.math.BigDecimal;
//
//public class CalculoFormula {
//
//	public BigDecimal analizarResultado(Indicador indicador,
//			List<Cuenta> cuentasUnaEmpresa) throws ParseException {
//
//		String formulaSinIndicadores = getRepoIndicadores()
//				.transformIndicadores(indicador.getFormula());
//
//		String formulaACalcular = getRepoEmpresas().transformValores(
//				formulaSinIndicadores, cuentasUnaEmpresa);
//
//		BigDecimal resultado = new BigDecimal(0);
//		Calculator calculator = new Calculator(new StringReader(
//				formulaACalcular));
//		try {
//			resultado = calculator.calculate();
//		} catch (ParseException e) {
//			throw new ParseException(
//					"Este indicador utiliza una cuenta que no esta disponible en este periodo");
//		}
//		return resultado;
//	}
//
//	public RepositorioIndicadores getRepoIndicadores() {
//		return ApplicationContext.getInstance().getSingleton(Indicador.class);
//	}
//
//	public RepositorioEmpresa getRepoEmpresas() {
//		return ApplicationContext.getInstance().getSingleton(Empresa.class);
//	}
//}
