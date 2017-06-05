package app;

import model.Indicador;

public class Parser {

	private String nombre;
	private String formula;

	public Parser(Indicador unIndicador) {
		this.nombre = unIndicador.getNombre();
		this.formula = unIndicador.getFormula();
	}

	public void parsear() throws Exception {

		if (!parentesisValidos(this.formula)) {
			throw new Exception(
					"Se han ingresado mal los parentesis, o no se encuentra todo separado por espacios");
		}

		if (this.nombre.contains(" ")) {
			throw new Exception(
					"Favor de ingresar un nombre que no contenga espacios");
		}

		if (formulaContieneNombre(this.nombre, this.formula)) {
			throw new Exception(
					"No puede usar ese nombre porque se encuentra dentro de la formula del mismo");
		}
	}

	public boolean parentesisValidos(String formula) {
		String[] componentes = formula.split(" ");
		int cont = 0;
		for (int i = 0; i < componentes.length; i++) {
			if (componentes[i].equals("(")) {
				cont++;
			}
			if (componentes[i].equals(")")) {
				cont--;
			}
		}
		if (cont != 0) {
			return false;
		}
		return true;
	}

	public boolean formulaContieneNombre(String nombre, String formula) {
		String[] componentes = formula.split(" ");
		for (int i = 0; i < componentes.length; i++) {
			if (nombre == componentes[i]) {
				return true;
			}
		}
		return false;
	}

}
