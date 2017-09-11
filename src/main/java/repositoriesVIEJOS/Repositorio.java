package repositoriesVIEJOS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Repositorio<T> implements Iterable<T> {
	
	private List<T> elementos= new ArrayList<T>();
	
	public Repositorio(){
		super();
	}
	
	public List<T> getElementos() {
			return elementos;
	}

	public void setElementos(List<T> elementos) {
		this.elementos = elementos;
	}
	
	public void cargarListaDeElementos(List<T> lista){
		for(T element : lista){
			elementos.add(element);
		}
		
	}

	public void add(T objeto ) {
		elementos.add(objeto);
		}
	
	public void delete(T o){
		elementos.remove(o);
	}
	
	public void update(){
		
	}
	
	public Iterator<T> iterator() {
		return elementos.iterator();
	}

}