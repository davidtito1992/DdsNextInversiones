package condiciones;

import parserIndicador.ParseException;
import model.Empresa;

public interface CondicionCualitativa {

	public int calcular(Empresa empresa) throws ParseException;
}
