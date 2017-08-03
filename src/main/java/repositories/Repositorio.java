package repositories;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Repositorio<T> implements Iterable<T> {
	
	private ArrayList<T> elementos= new ArrayList<T>();
	
	public Repositorio(){
		super();
	}
	
	public ArrayList<T> getElementos() {
			return elementos;
	}

	public void setElementos(ArrayList<T> elementos) {
		this.elementos = elementos;
	}
	
	public void cargarListaDeElementos(ArrayList<T> lista){
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
