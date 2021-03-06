package fuenteInformacion;
/**
 * Clase Simbolo que hace de nodo para el arrayList de simbolos. Contiene el simbolo en sí además
 * de la frecuencia de aparición en el texto de la fuente.
 * @author angel
 *
 */
public class Simbolo implements Comparable<Simbolo>{

	/**
	 * Simbolo en cuestión
	 */
	private String simbolo;
	
	/**
	 * Frecuencia del símbolo
	 */
	private int numero = 0;
	
	/**
	 * Constructor de la clase que crea un elemento
	 * @param c Simbolo
	 * @param n Frecuencia
	 */
	public Simbolo(String c, int n) {
		this.simbolo = c;
		this.numero = n;
	}
	
	/**
	 * Seteador del símbolo
	 * @param c Simbolo
	 */
	public void setSimbolo(String c) {
		this.simbolo = c;
	}
	 /**
	  * Añade uno a la frecuencia del simbolo
	  */
	public void addNum() {
		this.numero++;
	}
	
	/**
	 * Obtiene el símbolo en cuestión
	 * @return Simbolo
	 */
	public String getSimbolo() {
		return this.simbolo;
	}
	
	/**
	 * Obtiene la frecuencia del simbolo
	 * @return Frecuencia
	 */
	public int getNumero() {
		return this.numero;
	}
	
	/**
	 * Comparador de símbolos, según el símbolo en cuestión
	 * @param s simbolo a comparar
	 * @return True, si son el mismo símbolo, False si no
	 */
	public boolean compareSimbolo( Simbolo s) {
		
		return this.simbolo.equals(s.getSimbolo());
	}
	
	/**
	 * Devuelve en forma de string el simbolo
	 */
	public String toString() {
		StringBuffer cad = new StringBuffer("");
		
		cad.append(this.simbolo + "\t" + this.numero);
		
		return cad.toString();
	}
	
	@Override
    /**
     * Metodo sobreescrito de la interfaz Comparable, para que ordene según la frecuencia
     * de mayor a menor valor.
     */
	public int compareTo(Simbolo s) {
        if (this.numero > s.getNumero()) {
            return -1;
        }
        if (this.numero < s.getNumero()) {
            return 1;
        }
        return 0;
    }
}
