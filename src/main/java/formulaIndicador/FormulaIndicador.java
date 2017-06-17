package formulaIndicador;

import java.math.BigDecimal;

public interface FormulaIndicador {

	public BigDecimal calcular(String empresa, int anio, int semestre);

}
