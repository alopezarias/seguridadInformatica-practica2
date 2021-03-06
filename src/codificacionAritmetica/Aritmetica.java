package codificacionAritmetica;

import java.util.ArrayList;
import java.util.Scanner;

import fuenteInformacion.Fuente;
import fuenteInformacion.Simbolo;
import vista.Main;

public class Aritmetica {
	
	Codificacion code = null;
	Decodificacion decode = null;
	ArrayList<Simbolo> fuenteLista = null;
	ArrayList<SimboloIntervalo> fuenteIntervalos = null;
	Fuente fuente = null;
	
	public Aritmetica(Fuente f) {
		this.fuente = f;
		this.fuenteLista = f.getFuente();
	}
	
	private void generarIntervalos() {
		
		int total = fuente.totalSimbolos();
		int sumaParcial = 0;
		SimboloIntervalo si = null;
		Fraccion fSup = null;
		Fraccion fInf = null;
		
		this.fuenteIntervalos = new ArrayList<SimboloIntervalo>();
						
		for(Simbolo s : this.fuenteLista) {
			fInf = new Fraccion(sumaParcial, total);
			fSup = new Fraccion(s.getNumero()+sumaParcial, total);
			si = new SimboloIntervalo(s, fInf, fSup);
			this.fuenteIntervalos.add(si);
			sumaParcial += s.getNumero();
		}
		
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
			if(opc == 4)			
				Main.opciones();
			else
				this.menu(opc);
			this.opciones();
		}else {
			System.exit(0);
		}
	}
	
	/**
	 * Recoge la opción y lanza el método adecuado con la opción seleccionada
	 * @param opcion Opcion que selecciona el cliente desde la terminal
	 */
	public void menu(int opcion) {
		
		if(this.fuenteIntervalos == null) {
			this.generarIntervalos();
		}
		
		switch(opcion) {
		case 1:
			System.out.println(codificar());
			break;
		case 2:
			System.out.println(decodificar());
			break;
		case 3:
			System.out.println(this.toString());
			break;
		default:
			System.out.println("OPCIÓN INVÁLIDA\n");
		}
	}
	
	private String codificar() {
		
		Scanner sc = Main.in;
		System.out.println("Introduce la cadena a codificar: ");
		code = new Codificacion(this.fuenteIntervalos);
		String cadena = sc.nextLine();
		if (code.verificarCadena(cadena)) {
			return code.code(cadena);
		}
		return "NO SE PUEDE CODIFICAR LA CADENA\nCadena inválida";
	}
	
	private String decodificar() {
		
		Scanner sc = Main.in;
		System.out.println("Introduce la cadena a decodificar: ");
		decode = new Decodificacion(this.fuenteIntervalos);
		String num = sc.nextLine();
		System.out.println("Introduce la longitud de la cadena a decodificar: ");
		int longitud = Integer.valueOf(sc.nextLine());
		return decode.decode(num, longitud);
	}
	
	/**
	 * Imprime el menu de opciones que incluye la clase Fuente para el usuario
	 * @return
	 */
	public String displayMenu() {
		StringBuffer cad = new StringBuffer("");
		
		cad.append("\n");
		cad.append("----------------------------------------\n");
		cad.append("-  OPCIONES CODIFICACION ARITMETICA    -");
		cad.append("\n");
		cad.append("----------------------------------------\n");
		cad.append("\n");
		
		cad.append("1) CODIFICAR");
		cad.append("\n");
		
		cad.append("2) DECODIFICAR");
		cad.append("\n");
		
		cad.append("3) INFORMACION INTERVALOS");
		cad.append("\n");
		
		cad.append("4) MENU GENERAL");
		cad.append("\n");
		
		cad.append("0) SALIR");
		cad.append("\n");
		
		cad.append("");
		cad.append("\n");
		
		return cad.toString();
	}
	
	public String toString() {
		StringBuffer c = new StringBuffer("");
		
		c.append("FUENTE - INTERVALOS");
		c.append("\n");
		c.append("------------------------------------");
		c.append("\n");
		c.append("FUENTE\tFRECUENCIA\tINTERVALO\t");
		c.append("\n");
		c.append("------------------------------------");
		c.append("\n");
		for (SimboloIntervalo si : this.fuenteIntervalos) {
			c.append(si.getSimbolo().getSimbolo() + "\t" +
					 si.getSimbolo().getNumero() + "\t\t" + 
					 si.intervaloToString());
			c.append("\n");
		}
		c.append("");
		c.append("\n");
		
		return c.toString();
	}
}
