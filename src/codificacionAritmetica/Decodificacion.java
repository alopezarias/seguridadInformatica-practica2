package codificacionAritmetica;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import vista.Main;

public class Decodificacion {

	ArrayList<SimboloIntervalo> finter = new ArrayList<SimboloIntervalo>();
	
	public Decodificacion(ArrayList<SimboloIntervalo> fuenteIntervalos) {
		this.finter = fuenteIntervalos;
	}

	public String decode(String num, int l) {
		
		BigDecimal f = new BigDecimal(num);;
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
	
	private SimboloIntervalo encontrar(BigDecimal f) {
		
		for(SimboloIntervalo si : this.finter) {
			if(si.isIn(f)) {
				return si;
			}
		}
		return null;
	}
	
	private BigDecimal normalizar(SimboloIntervalo si, BigDecimal num) {
		
		BigDecimal inferior = si.getInferior();
		BigDecimal superior = si.getSuperior();
		
		//H - L
		BigDecimal h_l = superior.subtract(inferior);
		
		//F - L
		BigDecimal f_l = num.subtract(inferior);
		
		//re1 / res2
		return f_l.divide(h_l, Main.PRECISION, RoundingMode.HALF_EVEN);	
	}
	
//	private Fraccion pasarAFraccion(String num) {
//		Fraccion f;
//		String decimales = num.substring(2);
//		int dec = decimales.length();
//		StringBuffer den = new StringBuffer("1");
//		for(int i = 0; i<dec; i++) {
//			den.append("0");
//		}
//		String denominador = den.toString();
//		f = new Fraccion(new BigInteger(decimales), new BigInteger(denominador));		
//		return f;
//	}
}
