package formulaIndicador;

import java.math.BigDecimal;

public interface FormulaIndicador {

	public BigDecimal calcular();

	public final int SCALE = 2;
}
