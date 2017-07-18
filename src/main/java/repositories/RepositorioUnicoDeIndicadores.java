package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.RegistroIndicador;

public class RepositorioUnicoDeIndicadores {
	
	private List<RegistroIndicador> indicadoresExistentes;
	private static RepositorioUnicoDeIndicadores repositorioIndicadores = null; 
	
	public static RepositorioUnicoDeIndicadores getSingletonInstance(){
        if (repositorioIndicadores == null){
        	repositorioIndicadores = new RepositorioUnicoDeIndicadores();}
        else{
        	throw new RuntimeException("El repositorio de indicadores ya ha sido creado");}
       
        return repositorioIndicadores;
	}
	
	private RepositorioUnicoDeIndicadores(){
		indicadoresExistentes = new ArrayList<>();
	}
	
	
	
//	---------- Metodos ---------
	
	public List<RegistroIndicador> getIndicadoresExistentes() {
		return indicadoresExistentes;
	}

	public void setIndicadoresExistentes(
			List<RegistroIndicador> indicadoresExistentes) {
		this.indicadoresExistentes = indicadoresExistentes;
	}

	public void cargarListaIndicadores(List<RegistroIndicador> registrosIndicadores) {
		for(RegistroIndicador registroIndicador : registrosIndicadores){
			indicadoresExistentes.add(registroIndicador);
		}
	}
	
	public ArrayList<String> todosLosNombresDeIndicadores(List<RegistroIndicador> listaIndicadores) {
		ArrayList<String> nombresDeTodosLosIndicadores = listaIndicadores
				.stream().map(indicador -> indicador.getNombre()).distinct()
				.sorted().collect(Collectors.toCollection(ArrayList::new));
		return nombresDeTodosLosIndicadores;
	}
	
	public boolean esIndicador(String componente) {
		return indicadoresExistentes.stream().map(regIndicador -> regIndicador.getNombre())
				.anyMatch(NombreregIndicador -> NombreregIndicador.equalsIgnoreCase(componente));
	}

	public RegistroIndicador getRegistroIndicador(String nombreIndicador) {
		List<RegistroIndicador> registroIndicadores = indicadoresExistentes
				.stream()
				.filter(registroIndicador -> registroIndicador.getNombre()
						.equalsIgnoreCase(nombreIndicador))
				.collect(Collectors.toList());
		return registroIndicadores.get(0);
	}

}
