package codificacionAritmetica;

import java.math.BigDecimal;

import fuenteInformacion.Simbolo;

public class SimboloIntervalo {

	private Simbolo simbolo = null;
	private BigDecimal inferior = null;
	private BigDecimal superior = null;

	public SimboloIntervalo(Simbolo s, BigDecimal inf, BigDecimal sup) {
		this.simbolo = s;
		this.inferior = inf;
		this.superior = sup;
	}

	public Simbolo getSimbolo() {
		return this.simbolo;
	}

	public BigDecimal getInferior() {
		return this.inferior;
	}

	public BigDecimal getSuperior() {
		return this.superior;
	}

	public boolean isIn(BigDecimal d) {
		if(this.superior.compareTo(d) >0 && this.inferior.compareTo(d) <0) {
			return true;
		}
		return false;

	}

//	@Deprecated
//	public static BigDecimal mcd(BigInteger a, BigInteger b, BigInteger c) {
//
//		BigInteger i = new BigInteger("0");
//		BigInteger zero = BigInteger.ZERO;
//		BigInteger one = BigInteger.ONE;
//		BigInteger numMax;
//
//		numMax = a;
//
//		if (b.compareTo(numMax)>=1)
//			numMax = b;
//
//		if (c.compareTo(numMax)>=1)
//			numMax = c;
//
//		i = numMax;
//		while ((i.mod(a) != zero) || (i.mod(b) != zero) || (i.mod(c) != zero))
//			i = i.add(one);
//
//		return i;
//	}
	
	public String intervaloToString() {
		StringBuffer c = new StringBuffer("");
		
		c.append("[" + this.inferior.toString() + ", " + this.superior.toString() + "]");
		
		return c.toString();
	}

}
