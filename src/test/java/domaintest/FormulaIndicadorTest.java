package domaintest;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import formulaIndicador.Constante;
import formulaIndicador.Division;
import formulaIndicador.Expresion;
import formulaIndicador.Multiplicacion;
import formulaIndicador.Resta;
import formulaIndicador.Suma;
import formulaIndicador.Variable;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FormulaIndicadorTest {

	Constante const1;
	Constante const2;

	Variable var1;
	Variable var2;

	Expresion expr1;
	Expresion expr2;

	@Before
	public void inicializar() {

		const1 = new Constante("10");
		const2 = new Constante("5");

		var1 = new Variable("variable1");
		var1.setValor(new BigDecimal(6));

		var2 = new Variable("variable2");
		var2.setValor(new BigDecimal(2));
		
		expr1 = new Expresion(const1, var2, new Suma()); //12
		expr2 = new Expresion(var1, var2, new Resta()); //4

	}
	@Test
	public void sumaDeConstantes() throws RuntimeException {
		BigDecimal resultado = new Suma().ejecutar(const1.calcular(),
				const2.calcular());
		assertThat(resultado, is(new BigDecimal(15).setScale(2)));
	}

	@Test
	public void restaDeConstantes() throws RuntimeException {
		BigDecimal resultado = new Resta().ejecutar(const1.calcular(),
				const2.calcular());
		assertThat(resultado, is(new BigDecimal(5).setScale(2)));
	}

	@Test
	public void multiplicacionDeConstantes() throws RuntimeException {
		BigDecimal resultado = new Multiplicacion().ejecutar(const1.calcular(),
				const2.calcular());
		assertThat(resultado, is(new BigDecimal(50).setScale(2)));
	}

	@Test
	public void divisionDeConstantes() throws RuntimeException {
		BigDecimal resultado = new Division().ejecutar(const1.calcular(),
				const2.calcular());
		assertThat(resultado, is(new BigDecimal(2).setScale(2)));
	}

	@Test
	public void sumaDeNodosConstantes() throws RuntimeException {
		BigDecimal resultado = new Expresion(const1, const2, new Suma())
				.calcular();
		assertThat(resultado, is(new BigDecimal(15).setScale(2)));
	}

	@Test
	public void restaDeNodosConstantes() throws RuntimeException {
		BigDecimal resultado = new Expresion(const1, const2, new Resta())
				.calcular();
		assertThat(resultado, is(new BigDecimal(5).setScale(2)));
	}

	@Test
	public void multiplicacionDeNodosConstantes() throws RuntimeException {
		BigDecimal resultado = new Expresion(const1, const2,
				new Multiplicacion()).calcular();
		assertThat(resultado, is(new BigDecimal(50).setScale(2)));
	}

	@Test
	public void divisionDeNodosConstantes() throws RuntimeException {
		BigDecimal resultado = new Expresion(const1, const2, new Division())
				.calcular();
		assertThat(resultado, is(new BigDecimal(2).setScale(2)));
	}

	@Test
	public void sumaDeVariables() throws RuntimeException {

		BigDecimal resultado = new Suma().ejecutar(var1.calcular(),
				var2.calcular());
		assertThat(resultado, is(new BigDecimal(8).setScale(2)));
	}

	@Test
	public void restaDeVariables() throws RuntimeException {

		BigDecimal resultado = new Resta().ejecutar(var1.calcular(),
				var2.calcular());
		assertThat(resultado, is(new BigDecimal(4).setScale(2)));
	}

	@Test
	public void multiplicacionDeVariables() throws RuntimeException {

		BigDecimal resultado = new Multiplicacion().ejecutar(var1.calcular(),
				var2.calcular());
		assertThat(resultado, is(new BigDecimal(12).setScale(2)));
	}

	@Test
	public void divisionDeVariables() throws RuntimeException {

		BigDecimal resultado = new Division().ejecutar(var1.calcular(),
				var2.calcular());
		assertThat(resultado, is(new BigDecimal(3).setScale(2)));
	}


	@Test
	public void sumaDeNodoIzqExpresionYNodoDerExpresion()
			throws RuntimeException {

		Expresion nuevaExpresion =new Expresion(expr1, expr2, new Suma());
		BigDecimal resultado = nuevaExpresion.calcular();
		assertThat(resultado, is(new BigDecimal(16).setScale(2)));
	}


	@Test
	public void RestaDeNodoIzqExpresionYNodoDerExpresion()
			throws RuntimeException {

		Expresion nuevaExpresion =new Expresion(expr1, expr2, new Resta());
		BigDecimal resultado = nuevaExpresion.calcular();
		assertThat(resultado, is(new BigDecimal(8).setScale(2)));
	}
	
	@Test
	public void MultiplicacionDeNodoIzqExpresionYNodoDerExpresion()
			throws RuntimeException {

		Expresion nuevaExpresion =new Expresion(expr1, expr2, new Multiplicacion());
		BigDecimal resultado = nuevaExpresion.calcular();
		assertThat(resultado, is(new BigDecimal(48).setScale(2)));
	}
	
	@Test
	public void DivisionDeNodoIzqExpresionYNodoDerExpresion()
			throws RuntimeException {

		Expresion nuevaExpresion =new Expresion(expr1, expr2, new Division());
		BigDecimal resultado = nuevaExpresion.calcular();
		assertThat(resultado, is(new BigDecimal(3).setScale(2)));
	}
	
	
	
	
}