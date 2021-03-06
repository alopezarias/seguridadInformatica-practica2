package codificacionAritmetica;

import java.math.BigInteger;
import java.util.ArrayList;

public class Decodificacion {

	ArrayList<SimboloIntervalo> finter = new ArrayList<SimboloIntervalo>();
	
	public Decodificacion(ArrayList<SimboloIntervalo> fuenteIntervalos) {
		this.finter = fuenteIntervalos;
	}

	public String decode(String num, int l) {
		
		Fraccion f = this.pasarAFraccion(num);
		StringBuffer cadena = new StringBuffer("");
		SimboloIntervalo si;
		
		while(l>0) {
			si = encontrar(f);
			cadena.append(si.getSimbolo().getSimbolo());
			f = normalizar(si, f);
			l--;
		}
		return cadena.toString();
	}
	
	private SimboloIntervalo encontrar(Fraccion f) {
		
		for(SimboloIntervalo si : this.finter) {
			if(si.isIn(f)) {
				return si;
			}
		}
		return null;
	}
	
	private Fraccion normalizar(SimboloIntervalo si, Fraccion num) {
		
		Fraccion inferior = si.getInferior();
		Fraccion superior = si.getSuperior();
		
		//H - L
		BigInteger minimo = SimboloIntervalo.mcd(inferior.getDen(), superior.getDen(), BigInteger.ONE);
		BigInteger Lm = inferior.getNum().multiply((minimo.divide(inferior.getDen())));
		BigInteger Hm = superior.getNum().multiply((minimo.divide(inferior.getDen())));
		
		Fraccion abajo = new Fraccion(Hm.subtract(Lm), minimo);
		
		//F - L
		//Fraccion valor = pasarAFraccion(num);
		BigInteger minimo2 = SimboloIntervalo.mcd(inferior.getDen(), num.getDen(), BigInteger.ONE);
		BigInteger Ln = inferior.getNum().multiply((minimo2.divide(inferior.getDen())));
		BigInteger V = num.getNum().multiply((minimo2.divide(num.getDen())));
		
		Fraccion arriba = new Fraccion(V.subtract(Ln), minimo2);
		
		//re1 / res2
		BigInteger numerador = arriba.getNum().multiply(abajo.getDen());
		BigInteger denominador = arriba.getDen().multiply(abajo.getNum());
		
		return new Fraccion(numerador,denominador);		
	}
	
	private Fraccion pasarAFraccion(String num) {
		Fraccion f;
		String decimales = num.substring(2);
		int dec = decimales.length();
		StringBuffer den = new StringBuffer("1");
		for(int i = 0; i<dec; i++) {
			den.append("0");
		}
		String denominador = den.toString();
		f = new Fraccion(new BigInteger(decimales), new BigInteger(denominador));		
		return f;
	}
}
