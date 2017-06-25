package domaintest;

import java.math.BigDecimal;

import org.junit.Test;

import formulaIndicador.Constante;
import formulaIndicador.Division;
import formulaIndicador.Expresion;
import formulaIndicador.Multiplicacion;
import formulaIndicador.Resta;
import formulaIndicador.Suma;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class FormulaIndicadorTest {

    @Test
    public void sumaDeConstantes() throws RuntimeException {
        BigDecimal resultado = new Suma().ejecutar(new Constante("1").calcular(), new Constante("2").calcular());
        assertThat(resultado, is(new BigDecimal(3).setScale(2)));
    }
    
    @Test
    public void restaDeConstantes() throws RuntimeException {
        BigDecimal resultado = new Resta().ejecutar(new Constante("4").calcular(), new Constante("2").calcular());
        assertThat(resultado, is(new BigDecimal(2).setScale(2)));
    }
    
    @Test
    public void multiplicacionDeConstantes() throws RuntimeException {
        BigDecimal resultado = new Multiplicacion().ejecutar(new Constante("4").calcular(), new Constante("2").calcular());
        assertThat(resultado, is(new BigDecimal(8).setScale(2)));
    }
    
    @Test
    public void divisionDeConstantes() throws RuntimeException {
        BigDecimal resultado = new Division().ejecutar(new Constante("4").calcular(), new Constante("2").calcular());
        assertThat(resultado, is(new BigDecimal(2).setScale(2)));
    }
    
    
    @Test
    public void sumaDeNodosMedianteUnaExpresion() throws RuntimeException {
    	  BigDecimal resultado = new Expresion(new Constante("4"), new Constante("2"),new Suma()).calcular();
          assertThat(resultado, is(new BigDecimal(6).setScale(2)));
      }
    
    @Test
    public void restaDeNodosMedianteUnaExpresion() throws RuntimeException {
    	  BigDecimal resultado = new Expresion(new Constante("100"), new Constante("50"),new Resta()).calcular();
          assertThat(resultado, is(new BigDecimal(50).setScale(2)));
      }

    @Test
    public void multiplicacionDeNodosMedianteUnaExpresion() throws RuntimeException {
    	  BigDecimal resultado = new Expresion(new Constante("10"), new Constante("10"),new Multiplicacion()).calcular();
          assertThat(resultado, is(new BigDecimal(100).setScale(2)));
      }
    @Test
    public void divisionDeNodosMedianteUnaExpresion() throws RuntimeException {
    	  BigDecimal resultado = new Expresion(new Constante("200"), new Constante("10"),new Division()).calcular();
          assertThat(resultado, is(new BigDecimal(20).setScale(2)));
      }
}