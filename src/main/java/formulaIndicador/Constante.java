package formulaIndicador;

import java.math.BigDecimal;

public class Constante extends FormulaIndicador{

	private final BigDecimal numero ;
    private static final int SCALE = 2;

	public Constante(String num){
        this.numero = new BigDecimal(num).setScale(SCALE);
	}
	@Override
	public BigDecimal calcular(String empresa, int anio, int semestre) {
		
		return this.numero;
	}
	
	
}