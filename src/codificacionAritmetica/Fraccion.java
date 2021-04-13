package codificacionAritmetica;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import vista.Main;

/**
 * Esta clase me proporciona exactitud a la hora de funcionar con valores
 * decimales en fracciones. De esta manera no pierdo ningun decimal en el
 * calculo de operaciones.
 * 
 * @author angel
 *
 */
public class Fraccion {

	/**
	 * Numerador de la fraccion
	 */
	BigInteger numerador;

	/**
	 * Denominador de la fraccion
	 */
	BigInteger denominador;

	/**
	 * Valor que resulta de dividir el numerador entre el denominador
	 */
	BigDecimal valor;

	/**
	 * Constructor de la clase que forma la fracción dados dos parámetros
	 * 
	 * @param num Numerador
	 * @param den Denominador
	 */
	public Fraccion(Object num, Object den) {
		this.numerador = new BigInteger(num.toString());
		this.denominador = new BigInteger(den.toString());
	}

	/**
	 * Constructor de la fraccion dado el valor
	 * 
	 * @param v Valor
	 */
	public Fraccion(BigDecimal v) {
		this.valor = v;
	}

	/**
	 * Devuelve el valor en decimal de la fraccion
	 * 
	 * @return Valor de la fraccion
	 */
	public BigDecimal getValor() {
		BigDecimal res = new BigDecimal(this.numerador);
		BigDecimal den = new BigDecimal(this.denominador);
		return res.divide(den, Main.PRECISION, RoundingMode.HALF_UP);
	}

	/**
	 * Getter del denominador
	 * 
	 * @return Denominador
	 */
	public BigInteger getDen() {
		return this.denominador;
	}

	/**
	 * Getter del numerador
	 * 
	 * @return Numerador
	 */
	public BigInteger getNum() {
		return this.numerador;
	}

	/**
	 * Metodo para pasar a cadena de texto una fracción
	 * 
	 * @return Fracción en forma de texto
	 */
	public String toString() {
		StringBuffer c = new StringBuffer("");

		c.append(this.numerador + "/" + this.denominador);

		return c.toString();
	}
}
