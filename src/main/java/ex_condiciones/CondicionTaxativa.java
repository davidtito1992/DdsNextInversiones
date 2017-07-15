package ex_condiciones;

import parserIndicador.ParseException;
import model.Empresa;

public interface CondicionTaxativa {

	public boolean calcular(Empresa empresa) throws ParseException;
}
