package M7_Milestone3Fase1.vehicles.domain;

public abstract class Elemento {
/* 
 * Las bases de datos manejan unidades desarrolladas a partir de la clase 'Elemento'
 * idElemento: n�mero de identificador �nico (establecido v�a COUNTER particular para cada clase derivada)
 * buscadorStr: cadena alfanum�rica que servir� para todos los buscadores (= apellido, matr�cula, �)
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
	
	// Descripci�n breve para los buscadores
	@Override
	public String toString() {
		return buscadorStr + " (#" + String.format("%05d", idElemento) + ")";
	}
	
	// Descripci�n completa del elemento
	public abstract String showElement(); 
	
}
