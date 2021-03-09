package fuenteInformacion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import vista.Main;

/**
 * Clase Fuente, donde se encuentran los principales metodos relacionados
 * con la obtención de información de la fuente con la que trabajamos
 * @author angel
 *
 */
public class Fuente {

	/**
	 * El texto que tomamos como referencia para investigar la fuente
	 */
	private String texto;
	
	/**
	 * Array list que contiene todos los simbolos encontrados y su
	 * frecuencia en el texto.
	 */
	private ArrayList<Simbolo> simbolos = new ArrayList<Simbolo>();
	
	/**
	 * Constructor de la fuente con el texto
	 * @param texto
	 */
	public Fuente (String texto) {
		this.texto = texto;
	}
	
	/**
	 * Metodo que incia el calculo de parametros de la fuente
	 */
	public void run(int simTam) {
		this.simbolSplit(this.texto, simTam);
	}
	
	
	/**
	 * Metodo que separa los simbolos de uno en uno y los almacena en espacios
	 * diferentes para cada simbolo distinto, alamcenando tambien la frecuencia
	 * de los mismos.
	 * @param text Texto origen de la fuente
	 */
	private void simbolSplit(String text, int n) {
		
		Simbolo s;
		int indice;
		char c;
		StringBuffer finalText = new StringBuffer("");
		
		for(int i=0; i<text.length(); i++) {
			c = text.charAt(i);
			if(c == '\n')
				finalText.append("  ");
			else
				finalText.append(c);
		}
		
		this.texto = finalText.toString();
		finalText = new StringBuffer("");
		
		for(int i=1; i<=texto.length(); i++) {
			
			finalText.append(texto.charAt(i-1));
			if(i!=0 && i%n == 0 && i!=texto.length()) {
				finalText.append("\t");
			}
			
		}
		
		this.texto = finalText.toString();
		finalText = new StringBuffer("");
		
		String[] simbDif = this.texto.split("\t");
		
		for(String simb : simbDif) {
			
			indice = this.buscar(simb);
			
			if(indice != -1) {
				s = this.simbolos.get(indice);
				s.addNum();
			}else {
				s = new Simbolo(simb, 1);
				this.simbolos.add(s);
			}
		}
		
		int i=0;
	}
	
	/**
	 * Metodo que separa los simbolos de n en n y los almacena en espacios
	 * diferentes para cada simbolo distinto, alamcenando tambien la frecuencia
	 * de los mismos.
	 * @param text Texto origen de la fuente
	 */
//	private void simbolSplit(String text, int n) {
//		
//		Simbolo s;
//		int indice;
//		String c;
//		
//		for(int i=0; i<text.length(); i++) {
//			
//			c = text.charAt(i);
//			
//			if(c == '\n') {
//				c = Character.valueOf(' ');
//				indice = this.buscar(c);
//				
//				if(indice != -1) {
//					s = this.simbolos.get(indice);
//					s.addNum();
//					s.addNum();
//				}else {
//					s = new Simbolo(c, 1);
//					this.simbolos.add(s);
//				}
//				
//			}else {
//				indice = this.buscar(c);
//				
//				if(indice != -1) {
//					s = this.simbolos.get(indice);
//					s.addNum();
//				}else {
//					s = new Simbolo(c, 1);
//					this.simbolos.add(s);
//				}
//			}
//			
//		}
//	}
	
	/**
	 * Metodo que nos permite buscar el indice de un simbolo almacenado
	 * en el array list de todos los simbolos
	 * @param c Simbolo que queremos buscar
	 * @return Indice dentro del arraylist
	 */
	private int buscar(String c) {
		
		Simbolo s = new Simbolo(c,0);
		
		for(Simbolo simb : this.simbolos) {
			if(simb.compareSimbolo(s)) {
				return this.simbolos.indexOf(simb);
			}
		}
		return -1;
		
	}
	
	/**
	 * Opciones que se pueden realizar en el programa
	 */
	public void opciones() {
		//CAMBIAR MENÚ
		System.out.println(displayMenu());
		String menu = Main.in.nextLine();
		int opc = Integer.valueOf(menu);
		
		if (opc != 0) {
			if(opc == 8)			
				Main.opciones();
			else
				this.menu(opc);
			this.opciones();
		}else {
			System.exit(0);
		}
	}
	
	/**
	 * Imprime el menu de opciones que incluye la clase Fuente para el usuario
	 * @return
	 */
	public String displayMenu() {
		StringBuffer cad = new StringBuffer("");
		
		cad.append("\n");
		cad.append("--------------------------\n");
		cad.append("-  OPCIONES DE FUENTE    -");
		cad.append("\n");
		cad.append("--------------------------\n");
		cad.append("\n");
		
		cad.append("1) FRECUENCIA DE UN SÍMBOLO");
		cad.append("\n");
		
		cad.append("2) FRECUENCIA DE LA FUENTE");
		cad.append("\n");
		
		cad.append("3) PROBABILIDAD DE UN SÍMBOLO");
		cad.append("\n");
		
		cad.append("4) PROBABILIDAD DE LA FUENTE");
		cad.append("\n");
		
		cad.append("5) ENTROPÍA DE LA FUENTE");
		cad.append("\n");
		
		cad.append("6) ORDENAR FUENTE POR PROBABILIDADES");
		cad.append("\n");
		
		cad.append("7) DATOS DE LA FUENTE");
		cad.append("\n");
		
		cad.append("8) MENU GENERAL");
		cad.append("\n");
		
		cad.append("0) SALIR");
		cad.append("\n");
		
		cad.append("");
		cad.append("\n");
		
		return cad.toString();
	}
	
	/**
	 * Recoge la opción y lanza el método adecuado con la opción seleccionada
	 * @param opc Opcion que selecciona el cliente desde la terminal
	 */
	public void menu(int opc) {
		switch(opc) {
		case 1:
			frecuenciaSimbolo(null);
			break;
		case 2:
			frecuencias();
			break;
		case 3:
			probabilidadSimbolo(null);
			break;
		case 4:
			probabilidades();
			break;
		case 5:
			entropia();
			break;
		case 6:
			ordenarProbabilidades();
			break;
		case 7:
			System.out.println(toString());
			break;
		default:
			System.out.println("OPCIÓN INVÁLIDA\n");
		}
	}
	
	/**
	 * Metodo que imprime la frecuencia de un simbolo por pantalla
	 * @param s Simbolo en forma de string
	 */
	private void frecuenciaSimbolo(String s) {
		if (s == null) {
			Scanner sc = Main.in;
			System.out.println("INTRODUCE SÍMBOLO:\n");
			s = sc.nextLine();
			//sc.close();
		}
		
		int i = buscar(s);
		if (i != -1) {
			Simbolo sb = this.simbolos.get(i);
			System.out.println("SÍMBOLO: " + sb.getSimbolo() + " --> FRECUENCIA: " + sb.getNumero() + "\n\n");
		}else {
			System.out.println("El símbolo indicado no se encuentra en la fuente\n");
		}
	}
	
	/**
	 * Metodo que imprime las frecuencias de todos los simbolos por pantalla
	 */
	private void frecuencias() {
		for(Simbolo s : this.simbolos) {
			this.frecuenciaSimbolo(String.valueOf(s.getSimbolo()));
		}
	}
	 /**
	  * Metodo que imprime la probabilidad de un simbolo por pantalla
	  * @param s Simbolo en forma de string
	  */
	private void probabilidadSimbolo(String s) {
		if (s == null) {
			Scanner sc = Main.in;
			System.out.println("INTRODUCE SÍMBOLO:\n");
			s = sc.nextLine();
			//sc.close();
		}
		
		int i = buscar(s);
		Simbolo sb = this.simbolos.get(i);
		if (i != -1) {
			double prob = probSimb(sb);
			System.out.println("SÍMBOLO: " + sb.getSimbolo() + " --> PROBABILIDAD: " + prob + "\n\n");
		}else {
			System.out.println("El símbolo indicado no se encuentra en la fuente\n");
		}
	}
	
	/**
	 * Metodo que devuelve la probabilidad de un simbolo en particular
	 * @param sb Simbolo a examinar
	 * @return Double, probabilidad del simbolo en la fuente
	 */
	private double probSimb(Simbolo sb) {
		double num = Double.valueOf(sb.getNumero());
		return num/totalSimbolos();
	}
	
	/**
	 * Metodo que imprime todas las probabilidades de los simbolos de la fuente
	 */
	private void probabilidades() {
		for(Simbolo s : this.simbolos) {
			this.probabilidadSimbolo(String.valueOf(s.getSimbolo()));
		}
	}
	
	/*
	 * Metodo que imprime por pantalla la entropía en bits de nuestra fuente
	 */
	private void entropia() {
		
		int total = this.totalSimbolos();
		double entropia = 0;
		
		for(Simbolo s : this.simbolos) {
			
			double num = Double.valueOf(s.getNumero());
			double p = num / total;
			double n = 1 / p;
			double log = log(n, 2);
			
			entropia += (p*log);
		}
		
		System.out.println("ENTROPÍA DE LA FUENTE: " + entropia + "\n");
	}
	
	/**
	 * Metodo que realiza la operación de logaritmo para cualquier base
	 * @param num Numero del la funcion logaritmo
	 * @param base Base del logaritmo
	 * @return Double, resultado del logaritmo
	 */
	private static Double log(double num, int base) {
		return (Math.log10(num) / Math.log10(base));
	}
	 /**
	  * Metodo que aprovecha la funcionalidad de la interfaz Comparable, y ordena
	  * de mayor a menor los símbolos según su frecuencia
	  */
	private void ordenarProbabilidades() {
		Collections.sort(this.simbolos);
	}
	
	/**
	 * Metodo que nos devuelve el total de los símbolos que hay en nuestra fuente
	 * @return Total de los símbolos
	 */
	public int totalSimbolos() {
		int total = 0;
		for(Simbolo s : this.simbolos) {
			total += s.getNumero();
		}
		return total;
	}
	
	public ArrayList<Simbolo> getFuente(){
		return this.simbolos;
	}
	
	@Override
	/**
	 * Metodo que imprime por pantalla todos los datos de la fuente, sus frecuencias y sus probabilidades
	 */
	public String toString() {
		
		StringBuffer cad = new StringBuffer("");
		
		cad.append("FUENTE DE INFORMACIÓN");
		cad.append("\n");
		for(Simbolo s : this.simbolos) {
			cad.append(s.toString() + "\t" + this.probSimb(s) + "\n");
		}
		cad.append("\n");
		cad.append("TOTAL SIMBOLOS DIFERENTES: " + this.simbolos.size());
		cad.append("\n");
		cad.append("TOTAL SIMBOLOS: " + totalSimbolos());
		cad.append("\n");
		
		return cad.toString();
	}
	
}
