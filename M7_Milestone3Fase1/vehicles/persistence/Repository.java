package M7_Milestone3Fase1.vehicles.persistence;

import java.util.ArrayList;
import java.util.List;

import M7_Milestone3Fase1.vehicles.domain.Elemento;

public class Repository<E extends Elemento> {
	
	private List<E> elementList = new ArrayList<>();
	
	public Repository() {}
	
	public void addElement(E element) throws Exception {
		if(element == null) throw new Exception("Unable to update database: New entry missing.");
		
		elementList.add(element);
	}
	
	public E findElement(int idElemento) {
		return elementList.get(idElemento);	
	}
	
	public List<E> findElement(String buscadorStr) {
		if(buscadorStr == null || buscadorStr.isBlank()) return elementList;
		
		List<E> trobades = new ArrayList<>();
		for(E e : elementList)
			if(e.getElement().toUpperCase().contains(buscadorStr.toUpperCase()))
				trobades.add(e);
		return trobades;
	}
	
	public int size() {
		return elementList.size();
	}

}
