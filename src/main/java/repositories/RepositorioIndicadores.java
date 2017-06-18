package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Cuenta;
import model.RegistroIndicador;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;
import org.uqbar.commons.utils.Observable;

@Observable
public class RepositorioIndicadores extends
		CollectionBasedRepo<RegistroIndicador> {

	/********* ATRIBUTOS *********/

	private static RepositorioIndicadores instance = new RepositorioIndicadores();

	/********* METODOS *********/

	public void cargarListaIndicadores(List<RegistroIndicador> Indicadores) {
		for (int i = 0; i < Indicadores.size(); i++) {
			create(Indicadores.get(i));
		}
	}

	public static RepositorioIndicadores repositorioMaestro() {
		return instance;
	}

	@Override
	public Class<RegistroIndicador> getEntityType() {
		return RegistroIndicador.class;
	}

	@Override
	public RegistroIndicador createExample() {
		return new RegistroIndicador("", "");
	}

	@Override
	protected Predicate getCriterio(RegistroIndicador example) {
		return null;
	}

	public ArrayList<String> todosLosNombresDeIndicadores(
			List<RegistroIndicador> listaIndicadores) {

		ArrayList<String> nombresDeTodosLosIndicadores = listaIndicadores
				.stream().map(indicador -> indicador.getNombre()).distinct()
				.sorted().collect(Collectors.toCollection(ArrayList::new));
		return nombresDeTodosLosIndicadores;
	}

	public boolean esIndicador(String componente) {
		return this
				.allInstances()
				.stream()
				.map(regIndicador -> regIndicador.getNombre())
				.anyMatch(
						NombreregIndicador -> NombreregIndicador
								.equalsIgnoreCase(componente));
	}

	public RegistroIndicador getRegistroIndicador(String nombreIndicador) {
		List<RegistroIndicador> registroIndicadores = this
				.allInstances()
				.stream()
				.filter(registroIndicador -> registroIndicador.getNombre()
						.equalsIgnoreCase(nombreIndicador))
				.collect(Collectors.toList());
		return registroIndicadores.get(0);
	}
}

// public String transformIndicadores(String formulaConIndicadores) {
// String devolverEsto = formulaConIndicadores;
// if (contieneIndicadores(formulaConIndicadores)) {
// String[] componentes = formulaConIndicadores.split(" ");
//
// for (int i = 0; i < componentes.length; i++) {
//
// if (esIndicador(componentes[i])) {
// componentes[i] = "( "
// + transformIndicadores(getIndicador(componentes[i])
// .getFormula()) + " )";
// }
//
// }
// devolverEsto = String.join(" ", componentes);
// }
// return devolverEsto;
// }

// public RegistroIndicador getIndicador(String nombre) {
// List<RegistroIndicador> indicadoresConEseNombre = this.filtrar(nombre);
// if (indicadoresConEseNombre.isEmpty()) {
// return null;
// }
// return indicadoresConEseNombre.get(0);
// }
// public List<RegistroIndicador> filtrar(String nombreSeleccionado) {
// return this
// .allInstances()
// .stream()
// .filter(indicador -> filtroNombre(nombreSeleccionado, indicador))
// .collect(Collectors.toList());
// }
//
// public boolean filtroNombre(String nombreSeleccionado, RegistroIndicador
// indicador) {
// if (nombreSeleccionado == null) {
// return true;
// } else {
// return indicador.getNombre().equals(nombreSeleccionado);
// }
// }

// public boolean esIndicador(String nombre) {
// List<RegistroIndicador> indicadoresConEseNombre = this.filtrar(nombre);
// if (indicadoresConEseNombre.isEmpty()) {
// return false;
// }
// return true;
//
// }

// public boolean contieneIndicadores(String formula) {
// boolean flag = false;
// String[] componentes = formula.split(" ");
// for (int i = 0; i < componentes.length; i++) {
// if (esIndicador(componentes[i])) {
// flag = true;
// }
// }
// return flag;
// }
//
// public boolean indicadorYaExistente(String nombre) {
//
// return allInstances().stream().anyMatch(
// indicador -> indicador.getNombre().equals(nombre));
//
// }
