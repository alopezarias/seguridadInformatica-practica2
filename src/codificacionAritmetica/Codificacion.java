package codificacionAritmetica;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Clase codificacion, que nos permite codificar cualquier cadena de caracteres
 * dada una fuente con sus frecuencias y referencias
 * 
 * @author angel
 *
 */
public class Codificacion {

	/**
	 * Array del conjunto de simbolos, frecuencias y sus intervalos correspondientes
	 */
	private ArrayList<SimboloIntervalo> finter = null;
	
	/**
	 * El array con los simbolos que se quieren codificar en el numero decimal
	 */
	private ArrayList<String> simb = null;
	
	/**
	 * El array con los simbolos que tenemos en la fuente, es decir, los que somos
	 * capaces de identificar y codificar
	 */
	private ArrayList<String> fuente = null;
	
	/**
	 * Constructor de la codificacion que recibe la fuente con los intervalos ya configurados
	 * @param fuenteIntervalos Simbolos con frecuencias e intervalos
	 */
	public Codificacion(ArrayList<SimboloIntervalo> fuenteIntervalos) {
		this.finter = fuenteIntervalos;
		fuente = new ArrayList<String>();
		
		for (SimboloIntervalo si:this.finter) {
			fuente.add(si.getSimbolo().getSimbolo());
		}		
	}

	/**
	 * Metodo para verificar si todos los simbolos que queremos codificar están
	 * contemplados en la fuente dada como entrada. En caso de que no devolverá
	 * un false y no codificará nada.
	 * 
	 * @param cadena Cadena a codificar
	 * @return True o false, dependiendo de si se va a poder codificar o no
	 */
	public boolean verificarCadena(String cadena) {
		
		splitSimbolos(cadena);
		
		for(String s : this.simb) {
			if(!this.fuente.contains(s)) { //punto de falla, no se si lo hará bien
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Metodo que separa los simbolos, para averiguar los caracteres que hay que codificar
	 * separados uno por uno.
	 * @param cadena Cadena a codificar
	 */
	private void splitSimbolos(String cadena) {
		char c;
		StringBuffer finalText = new StringBuffer("");
		String texto;
		
		for(int i=0; i<cadena.length(); i++) {
			c = cadena.charAt(i);
			if(c == '\n')
				finalText.append("  ");
			else
				finalText.append(c);
		}
		
		texto = finalText.toString();
		finalText = new StringBuffer("");
		
		for(int i=1; i<=texto.length(); i++) {
			
			finalText.append(texto.charAt(i-1));
			if(i!=0 && i!=texto.length()) {
				finalText.append("\t");
			}
		}
		
		texto = finalText.toString();
		finalText = null;
		
		String[] simbDif = texto.split("\t");
		this.simb = new ArrayList<String>();
		
		for(String simb : simbDif) {
			this.simb.add(simb);
		}
	}

	/**
	 * El método que va a codificar la cadena de texto
	 * @param cadena Cadena a codificar
	 * @return La cadena codificada en número decimal
	 */
	public String code(String cadena) {
		
		return codeRec(null, null, 0);
	}
	
	/**
	 * Metodo recursivo que se encarga de ir recorriendo el array de caracteres
	 * de la cadena a codificar, y actualizando los intervalos y normalizando los valores
	 * para poder dar un valor decimal a la cadena codificada.
	 * 
	 * @param inf Limite inferior del intervalo en el que estamos
	 * @param sup Limite superior del interfalo en el que estamos
	 * @param i Numero de caracter que estamos codificando
	 * @return La cadena de texto codificada en un número decimal
	 */
	private String codeRec(BigDecimal inf, BigDecimal sup, int i) {
		
		int indice = buscarIndice(i);
		SimboloIntervalo si = this.finter.get(indice);
		
		BigDecimal nuevoInf = si.getInferior();
		BigDecimal nuevoSup = si.getSuperior();
		
		if(i!=0) {
			//en caso de que no sea el primer caracter, remodelamos el intervalo
			nuevoInf = normalizar(inf, sup, nuevoInf);
			nuevoSup = normalizar(inf, sup, nuevoSup);
		}
		
		if(i==this.simb.size()-1) {
			return calcularCodigo(nuevoInf, nuevoSup);
		}else {
			return codeRec(nuevoInf, nuevoSup, i+1);
		}
		
	}
	
	/**
	 * Metodo que nos sirve para buscar el indice de un caracter de la codificacion
	 *  dentro del array de la fuente.
	 *  
	 * @param i Indice a buscar, de la cadena de simbolos a codificar
	 * @return Indice que tiene el caracter a buscar
	 */
	private int buscarIndice(int i) {
		
		int index = 0;
		
		String s = this.simb.get(i), s2;
		
		for(SimboloIntervalo si : this.finter) {
			
			s2 = si.getSimbolo().getSimbolo();
			
			if(s2.contentEquals(s)) {
				return index;
			}
			
			index++;
		}
		
		return -1;
	}
	
	/**
	 * Metodo que nos sirve para normalizar los valores de los extremos del intervalo
	 * en el que trabajamos.
	 * @param L
	 * @param H
	 * @param j
	 * @return La normalizacion de los intervalos
	 */
	private BigDecimal normalizar(BigDecimal L, BigDecimal H, BigDecimal j) {	
		
		//L + (H - L) * j
			//H - L
		BigDecimal H_L = H.subtract(L);
			//RES * j
		BigDecimal H_L_j = H_L.multiply(j);	
			//L + res
		BigDecimal L_H_L_j = L.add(H_L_j);
		
		return L_H_L_j;
	}
	
	/**
	 * Metodo para calcular el codigo, ya dado el resultado final de los intervalos finales
	 * @param inf
	 * @param sup
	 * @return codigo decimal final que representa la cadena y el intervalo final
	 */
	private String calcularCodigo(BigDecimal inf, BigDecimal sup) {
		
		BigDecimal L = inf;
		BigDecimal H = sup;
		
		String Ls = String.valueOf(L);
		String Hs = String.valueOf(H);
		
		int i = 0;
		
		do {
			i++;
		}while(Ls.charAt(i) == Hs.charAt(i));
		i--;
		
		if(Hs.length() > i+1) {
			return Hs.substring(0, i+2);
		}
		
		do {
			i++;
		}while(Ls.charAt(i) == 9);
		
		StringBuffer result = new StringBuffer("");
		result.append(Ls.substring(0, i));
		result.append(Integer.valueOf(Ls.charAt(i))+1);
		
		return result.toString(); //implementar codigo de seleccion de valor
	}

}
