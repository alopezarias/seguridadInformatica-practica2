package codificacionAritmetica;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

import vista.Main;


public class Codificacion {

	private ArrayList<SimboloIntervalo> finter = null;
	private ArrayList<String> simb = null;
	private ArrayList<String> fuente = null;
	
	public Codificacion(ArrayList<SimboloIntervalo> fuenteIntervalos) {
		this.finter = fuenteIntervalos;
		fuente = new ArrayList<String>();
		
		for (SimboloIntervalo si:this.finter) {
			fuente.add(si.getSimbolo().getSimbolo());
		}		
	}

	public boolean verificarCadena(String cadena) {
		
		splitSimbolos(cadena);
		
		for(String s : this.simb) {
			if(!this.fuente.contains(s)) { //punto de falla, no se si lo hará bien
				return false;
			}
		}
		return true;
	}
	
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
				finalText.append("-");
			}
		}
		
		texto = finalText.toString();
		finalText = null;
		
		String[] simbDif = texto.split("-");
		this.simb = new ArrayList<String>();
		
		for(String simb : simbDif) {
			this.simb.add(simb);
		}
	}

	public String code(String cadena) {
		
		return codeRec(null, null, 0);
	}
	
	private String codeRec(Fraccion inf, Fraccion sup, int i) {
		//AQUÍ PETA FIJO EL PROGRAMA
		int indice = buscarIndice(i);
		SimboloIntervalo si = this.finter.get(indice);
		
		Fraccion nuevoInf = si.getInferior();
		Fraccion nuevoSup = si.getSuperior();
		
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
	
//	private Fraccion normalizar(Fraccion L, Fraccion H, Fraccion j) {	
//		//L + (H - L) * j
//			//H - L
//		BigInteger minimo = SimboloIntervalo.mcd(L.getDen(), H.getDen(), BigInteger.ONE);
//		BigInteger Lm = L.getNum().multiply(minimo.divide(L.getDen()));
//		BigInteger Hm = H.getNum().multiply(minimo.divide(H.getDen()));
//		Fraccion res = new Fraccion(Hm.subtract(Lm), minimo);		
//			//RES * j
//		res = new Fraccion(res.getNum().multiply(j.getNum()), res.getDen().multiply(j.getDen()));		
//			//L + res
//		minimo = SimboloIntervalo.mcd(res.getDen(), L.getDen(), BigInteger.ONE);
//		Lm = L.getNum().multiply((minimo.divide(L.getDen())));
//		BigInteger resM = res.getNum().multiply((minimo.divide(res.getDen())));
//		
//		return new Fraccion(Lm.add(resM),minimo);
//	}
	
	private Fraccion normalizar(Fraccion L, Fraccion H, Fraccion j) {	
		//L + (H - L) * j
			//H - L
		BigInteger minimo = SimboloIntervalo.mcd(L.getDen(), H.getDen(), BigInteger.ONE);
		BigInteger Lm = L.getNum().multiply(minimo.divide(L.getDen()));
		BigInteger Hm = H.getNum().multiply(minimo.divide(H.getDen()));
		Fraccion res = new Fraccion(Hm.subtract(Lm), minimo);		
			//RES * j
		res = new Fraccion(res.getNum().multiply(j.getNum()), res.getDen().multiply(j.getDen()));		
			//L + res
		BigDecimal arriba = new BigDecimal(res.getNum().toString());
		BigDecimal abajo = new BigDecimal(res.getDen().toString());
		BigDecimal resDec = arriba.divide(abajo, Main.PRECISION, RoundingMode.HALF_UP);
		
		BigDecimal lm = new BigDecimal(Lm.toString());
		BigDecimal min = new BigDecimal();
		
		return new Fraccion(Lm.add(resM),minimo);
	}
	
	private String calcularCodigo(Fraccion inf, Fraccion sup) {
		//TODO
		
		BigDecimal L = inf.getValor();
		BigDecimal H = sup.getValor();
		
		String Ls = String.valueOf(L);
		String Hs = String.valueOf(H);
		
		int i = 0;
		
		do {
			i++;
		}while(Ls.charAt(i) == Hs.charAt(i));
		i--;
		
		if(Hs.length() > i+1) {
			return Hs.substring(0, i+2);
		}//else {
		//TODO, QUEDA POR PROBAR ESTA PARTE DEL CODIGO A VER SI FUNCIONA
		
		do {
			i++;
		}while(Ls.charAt(i) == 9);
		
		StringBuffer result = new StringBuffer("");
		result.append(Ls.substring(0, i));
		result.append(Integer.valueOf(Ls.charAt(i))+1);
		
		return result.toString(); //implementar codigo de seleccion de valor
	}

}
