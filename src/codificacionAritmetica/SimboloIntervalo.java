package codificacionAritmetica;

import java.math.BigDecimal;
import java.math.BigInteger;

import fuenteInformacion.Simbolo;

public class SimboloIntervalo {

	private Simbolo simbolo = null;
	private Fraccion inferior = null;
	private Fraccion superior = null;

	public SimboloIntervalo(Simbolo s, Fraccion inf, Fraccion sup) {
		this.simbolo = s;
		this.inferior = inf;
		this.superior = sup;
	}

	public Simbolo getSimbolo() {
		return this.simbolo;
	}

	public Fraccion getInferior() {
		return this.inferior;
	}

	public Fraccion getSuperior() {
		return this.superior;
	}

	public boolean isIn(Fraccion f) {

		/*Fraccion menor = null;
		Fraccion mayor = null;
		Fraccion medio = null;
		
		BigInteger dComun = mcd(this.inferior.getDen(), this.superior.getDen(), f.getDen());
		
		menor = new Fraccion(this.inferior.getNum().multiply(dComun.divide(this.inferior.getDen())), dComun);
		medio = new Fraccion(f.getNum().multiply(dComun.divide(f.getDen())), dComun);
		mayor = new Fraccion(this.superior.getNum().multiply((dComun.divide(this.superior.getDen()))), dComun);
		
		if(menor.getNum().compareTo(medio.getNum()) <=0 && medio.getNum().compareTo(mayor.getNum()) < 0) {
			return true;
		}		
		return false;*/
		
		BigDecimal superior = this.superior.getValor();
		BigDecimal inferior = this.inferior.getValor();
		BigDecimal fval = f.getValor();
		if(superior.compareTo(fval) >0 && inferior.compareTo(fval) <0) {
			return true;
		}
		return false;

	}

	@Deprecated
	public static BigInteger mcd(BigInteger a, BigInteger b, BigInteger c) {

		BigInteger i = new BigInteger("0");
		BigInteger zero = BigInteger.ZERO;
		BigInteger one = BigInteger.ONE;
		BigInteger numMax;

		numMax = a;

		if (b.compareTo(numMax)>=1)
			numMax = b;

		if (c.compareTo(numMax)>=1)
			numMax = c;

		i = numMax;
		while ((i.mod(a) != zero) || (i.mod(b) != zero) || (i.mod(c) != zero))
			i = i.add(one);

		return i;
	}
	
	public String intervaloToString() {
		StringBuffer c = new StringBuffer("");
		
		c.append("[" + this.inferior.toString() + ", " + this.superior.toString() + "]");
		
		return c.toString();
	}

}
