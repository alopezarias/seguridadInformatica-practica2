package codificacionAritmetica;

import java.math.BigDecimal;

import fuenteInformacion.Simbolo;

/**
 * La clase Simbolo Intervalo nos sirve para almacenar en un solo objeto su
 * simbolo, frecuencia e intervalo superior e inferior en forma de bigDecimal
 * 
 * @author angel
 *
 */
public class SimboloIntervalo {

	/**
	 * El simbolo en sí con su frecuencia
	 */
	private Simbolo simbolo = null;

	/**
	 * El extremo inferior
	 */
	private BigDecimal inferior = null;

	/**
	 * El extremo superior
	 */
	private BigDecimal superior = null;

	/**
	 * Constructor de la clase dado el símbolo y los extremos
	 * 
	 * @param s   Simbolo
	 * @param inf Extremo inferior
	 * @param sup Extremo superior
	 */
	public SimboloIntervalo(Simbolo s, BigDecimal inf, BigDecimal sup) {
		this.simbolo = s;
		this.inferior = inf;
		this.superior = sup;
	}

	/**
	 * Getter del símbolo
	 * 
	 * @return simbolo
	 */
	public Simbolo getSimbolo() {
		return this.simbolo;
	}

	/**
	 * Getter del extremo inferior
	 * 
	 * @return extremo inferior
	 */
	public BigDecimal getInferior() {
		return this.inferior;
	}

	/**
	 * Getter del extremo superior
	 * 
	 * @return extremo superior
	 */
	public BigDecimal getSuperior() {
		return this.superior;
	}

	/**
	 * Metodo que comprueba si un numero concreto está en un intervalo
	 * 
	 * @param d Numero decimal
	 * @return True si está dentro, false si no lo está
	 */
	public boolean isIn(BigDecimal d) {
		if (this.superior.compareTo(d) > 0 && this.inferior.compareTo(d) < 0) {
			return true;
		}
		return false;

	}

	/**
	 * Metodo para pasar a texto un intervalo dado
	 * 
	 * @return Intervalo en forma de string
	 */
	public String intervaloToString() {
		StringBuffer c = new StringBuffer("");

		c.append("[" + this.inferior.toString() + ", " + this.superior.toString() + "]");

		return c.toString();
	}

}
