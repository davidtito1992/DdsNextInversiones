package ex_condiciones;

import parserIndicador.ParseException;
import model.Empresa;

public interface CondicionCualitativa {

	public double calcular(Empresa empresa) throws ParseException;

}
