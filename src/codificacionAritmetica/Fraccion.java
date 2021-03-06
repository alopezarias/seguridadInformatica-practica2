package codificacionAritmetica;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import vista.Main;

/**
 * Esta clase me proporciona exactitud a la hora de funcionar con 
 * valores decimales en fracciones. De esta manera no pierdo ningun
 * decimal en el calculo de operaciones.
 * 
 * @author angel
 *
 */
public class Fraccion {

	BigInteger numerador;
	BigInteger denominador;
	BigDecimal valor;
	
	public Fraccion(Object num, Object den){
		this.numerador = new BigInteger(num.toString());
		this.denominador = new BigInteger(den.toString());
	}
	
	public Fraccion(BigDecimal v) {
		this.valor = v;
	}

	/**
	 * Devuelve el valor en decimal de la fraccion
	 * @return Valor de la fraccion
	 */
	public BigDecimal getValor() {
		BigDecimal res = new BigDecimal(this.numerador);
		BigDecimal den = new BigDecimal(this.denominador);
		return res.divide(den, Main.PRECISION, RoundingMode.HALF_UP);
	}
	
	public BigInteger getDen() {
		return this.denominador;
	}
	
	public BigInteger getNum() {
		return this.numerador;
	}
	
	public String toString() {
		StringBuffer c = new StringBuffer("");
		
		c.append(this.numerador + "/" + this.denominador);
		
		return c.toString();
	}
}
