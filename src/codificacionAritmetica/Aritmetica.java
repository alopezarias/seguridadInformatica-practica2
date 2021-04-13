package codificacionAritmetica;

import java.util.ArrayList;
import java.util.Scanner;

import fuenteInformacion.Fuente;
import fuenteInformacion.Simbolo;
import vista.Main;

/**
 * La clase Aritmetica implementa todos los mecanismos necesarios para codificar
 * aritméticamente y decodificar cualquier codigo dado. En caso de que la
 * precisión decimal sea un problema se puede configurar en el Main de la app.
 * 
 * @author angel
 *
 */
public class Aritmetica {

	/**
	 * Objeto de codificación para codificar cadenas de texto
	 */
	Codificacion code = null;

	/**
	 * Objeto decodificacion para decodificar numeros con longitudes
	 */
	Decodificacion decode = null;

	/**
	 * Lista de caracteres de la fuente con sus frecuencias
	 */
	ArrayList<Simbolo> fuenteLista = null;

	/**
	 * Lista de simbolos con sus frecuencias e intervalos correspondientes
	 */
	ArrayList<SimboloIntervalo> fuenteIntervalos = null;

	/**
	 * Objeto Fuente para poder trabajar con la fuente de información dada
	 */
	Fuente fuente = null;

	/**
	 * Constructor de la clase, con la fuente recibida del archivo
	 * 
	 * @param f La fuente recibida por medio de la lectura del archivo
	 */
	public Aritmetica(Fuente f) {
		this.fuente = f;
		this.fuenteLista = f.getFuente();
	}

	/**
	 * Metodo para generar los intervalos que nos facilitan el trabajo con la
	 * codificacion y decodificacion aritmetica, en funcion de las frecuencias
	 */
	private void generarIntervalos() {

		int total = fuente.totalSimbolos();
		int sumaParcial = 0;
		SimboloIntervalo si = null;
		Fraccion fSup = null;
		Fraccion fInf = null;

		this.fuenteIntervalos = new ArrayList<SimboloIntervalo>();

		for (Simbolo s : this.fuenteLista) {
			fInf = new Fraccion(sumaParcial, total);
			fSup = new Fraccion(s.getNumero() + sumaParcial, total);
			si = new SimboloIntervalo(s, fInf.getValor(), fSup.getValor());
			this.fuenteIntervalos.add(si);
			sumaParcial += s.getNumero();
		}

	}

	/**
	 * Opciones que se pueden realizar en el programa con respecto a las
	 * codificaciones y decodificaciones aritmeticas
	 */
	public void opciones() {
		System.out.println(displayMenu());
		String menu = Main.in.nextLine();
		int opc = Integer.valueOf(menu);

		if (opc != 0) {
			if (opc == 4)
				Main.opciones();
			else
				this.menu(opc);
			this.opciones();
		} else {
			System.exit(0);
		}
	}

	/**
	 * Recoge la opción y lanza el método adecuado con la opción seleccionada
	 * 
	 * @param opcion Opcion que selecciona el cliente desde la terminal
	 */
	public void menu(int opcion) {

		if (this.fuenteIntervalos == null) {
			this.generarIntervalos();
		}

		switch (opcion) {
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

	/**
	 * Metodo que nos sirve para codificar un texto que recibamos por pantalla
	 * 
	 * @return El numero decimal en forma de texto ya codificado
	 */
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

	/**
	 * Metodo para decodificar un codigo decimal dada una fuente con sus intervalos
	 * 
	 * @return La cadena de texto resultante
	 */
	private String decodificar() {

		Scanner sc = Main.in;
		System.out.println("Introduce la cadena a decodificar: ");
		decode = new Decodificacion(this.fuenteIntervalos);
		String num = sc.nextLine();
		System.out.println("Introduce la longitud de la cadena a decodificar: ");
		int longitud = Integer.valueOf(sc.nextLine());
		return decode.decode(num, longitud);
	}
	
	public String decodificar(String num) {
		
		if (this.fuenteIntervalos == null) {
			this.generarIntervalos();
		}
		
		decode = new Decodificacion(this.fuenteIntervalos);
		int longitud = 6;
		return decode.decode(num, longitud);
	}

	/**
	 * Imprime el menu de opciones que incluye la clase Fuente para el usuario
	 * 
	 * @return String que representa el menu en sí
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

	/**
	 * Pasa a string la información de la fuente y sus intervalos
	 */
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
			c.append(si.getSimbolo().getSimbolo() + "\t" + si.getSimbolo().getNumero() + "\t\t"
					+ si.intervaloToString());
			c.append("\n");
		}
		c.append("");
		c.append("\n");

		return c.toString();
	}
}
