package repositoriesVIEJOS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.RegistroIndicador;

import org.uqbar.commons.utils.Observable;

@Observable
public class RepositorioUnicoDeIndicadores extends Repositorio{
	
	private static RepositorioUnicoDeIndicadores repositorioIndicadores = null; 
	
	public static RepositorioUnicoDeIndicadores getSingletonInstance(){
		
        if (repositorioIndicadores == null){
        	repositorioIndicadores = new RepositorioUnicoDeIndicadores();}
       
        return repositorioIndicadores;
	}
	
	private RepositorioUnicoDeIndicadores(){
		super();
	}
	
	
//	---------- Metodos ---------

	@SuppressWarnings("unchecked")
	public void cargarListaIndicadores(List<RegistroIndicador> registrosIndicadores) {
		for(RegistroIndicador registroIndicador : registrosIndicadores){
			this.getElementos().add(registroIndicador);
		}
	}
	
	public ArrayList<String> todosLosNombresDeIndicadores(List<RegistroIndicador> listaIndicadores) {
		ArrayList<String> nombresDeTodosLosIndicadores = listaIndicadores
				.stream().map(indicador -> indicador.getNombre()).distinct()
				.sorted().collect(Collectors.toCollection(ArrayList::new));
		return nombresDeTodosLosIndicadores;
	}
	
	@SuppressWarnings("unchecked")
	public boolean esIndicador(String componente) {
		List<RegistroIndicador> indicadoresExistentes = new ArrayList<RegistroIndicador>();
		indicadoresExistentes = this.getElementos();
		return indicadoresExistentes.stream().map(regIndicador -> regIndicador.getNombre())
				.anyMatch(NombreregIndicador -> NombreregIndicador.equalsIgnoreCase(componente));
	}

	@SuppressWarnings("unchecked")
	public RegistroIndicador getRegistroIndicador(String nombreIndicador) {
		List<RegistroIndicador> indicadoresExistentes = new ArrayList<RegistroIndicador>();
		indicadoresExistentes = this.getElementos();
		List<RegistroIndicador> registroIndicadores = indicadoresExistentes
				.stream()
				.filter(registroIndicador -> registroIndicador.getNombre()
						.equalsIgnoreCase(nombreIndicador))
				.collect(Collectors.toList());
		return registroIndicadores.get(0);
	}
}
