package M7_Milestone3Fase1.vehicles.domain;

public abstract class Elemento {
/* 
 * Las bases de datos manejan unidades desarrolladas a partir de la clase 'Elemento'
 * idElemento: número de identificador único (establecido vía COUNTER particular para cada clase derivada)
 * buscadorStr: cadena alfanumérica que servirá para todos los buscadores (= apellido, matrícula, …)
 */
	
	private static int COUNTER = 0;
	
	protected int idElemento;
	protected String buscadorStr;
	
	public Elemento(String buscadorStr) {
		this.buscadorStr = buscadorStr;
		idElemento = COUNTER++;
	}
	
	
	public String getElement() {
		return buscadorStr;
	}
	
	// Descripción breve para los buscadores
	@Override
	public String toString() {
		return buscadorStr + " (#" + String.format("%05d", idElemento) + ")";
	}
	
	// Descripción completa del elemento
	public abstract String showElement(); 
	
}
