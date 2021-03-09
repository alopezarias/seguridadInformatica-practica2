package vista;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import codificacionAritmetica.Aritmetica;
import fuenteInformacion.Fuente;
 
/**
 * Clase inicial que realiza la captura de texto y la impresion por pantalla
 * de las cosas relevantes, tales como la captura de texto, la impresion del menu
 * las distintas opciones del programa y su finalización.
 * @author angel
 *
 */
public class Main {
	
	public static int PRECISION = 100;
	
	/**
	 * Objeto de la clase Fuente, que contiene todas las operaciones que realizará el programa
	 */
	private static Fuente fuente;
	
	private static Aritmetica aritmetica;
	
	/**
	 * Para poder leer elementos por consola
	 */
	public static Scanner in = new Scanner(System.in);
	
	/**
	 * El método main de todo el programa
	 * @param args
	 */
	public static void main(String args[]) {
		
		System.out.println(inicioPrograma());
		String texto;
		try {
			texto = introducirArchivo();
			fuente = new Fuente(texto);
			aritmetica = new Aritmetica(fuente);
			int n = escogerSplit();
			fuente.run(n);
			opciones();
			System.out.println(finPrograma());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Mensaje de inicio del programa
	 * @return Texto de bienvenida
	 */
	private static String inicioPrograma() {
		StringBuffer cad = new StringBuffer("");
		cad.append("FUENTE DE INFORMACIÓN ASOCIADA A UN TEXTO");
		cad.append("\n");
		return cad.toString();
	}
	
	/**
	 * Metodo para introducir la ruta del archivo y capturar el texto del mismo
	 * @return texto contenido en el archivo
	 * @throws IOException
	 */
	private static String introducirArchivo() throws IOException{
		System.out.println("\nINTRODUCE LA RUTA ABSOLUTA DEL ARCHIVO CON EL TEXTO:\n");
		String ruta = in.nextLine();
		String linea;
		StringBuffer contenido = new StringBuffer("");
		String texto;
		
		try {
			FileReader f = new FileReader(ruta/*, StandardCharsets.US_ASCII*/);
		    BufferedReader b = new BufferedReader(f);
		    while((linea = b.readLine())!=null) {
		    	
		    	contenido.append(linea);
		    	contenido.append("\n");
		    	
		    }
		    b.close();
		}catch (IOException e) {
			throw e;
		}
		
		texto = contenido.substring(contenido.indexOf("\"") + 1, contenido.lastIndexOf("\""));
		return texto;
	}
	
	/**
	 * Metodo que nos permite elegir el split que le haremos al texto
	 * @return Numero de caracteres que tendrá el simbolo
	 */
	private static int escogerSplit() {
		System.out.println("ESCOGE LA LONGITUD DE LOS SÍMBOLOS: \n");
		String l = in.nextLine();
		boolean b = false;
		while(!b) {
			try {
	            Integer.parseInt(l);
	            b = true;
	        } catch (NumberFormatException excepcion) {
	            System.out.println("INTRODUCE UN NUMERO, POR FAVOR: \n");
	            l = in.nextLine();
	        }
		}
		
		return Integer.valueOf(l);
	}
	
	/**
	 * Opciones que se pueden realizar en el programa
	 */
	public static void opciones() {
		//CAMBIAR MENÚ
		imprimirMenu();
		String menu = in.nextLine();
		int opc = Integer.valueOf(menu);
		
		if (opc != 0) {
			if(opc == 1)			
				fuente.opciones();
			else if(opc == 2)			
				aritmetica.opciones();
			else {
				System.out.println(" - OPCIÓN INVÁLIDA!! ");
				opciones();
			}
		}else {
			System.exit(0);
		}
	}
	
	private static void imprimirMenu() {
		System.out.println("------------");
		System.out.println("MENU GENERAL");
		System.out.println("------------");
		System.out.println("1) FUENTE");
		System.out.println("2) ARITMETICA");
		System.out.println("0) PARA SALIR");
		System.out.println("-------------");
	}
	 
	/**
	 * Cadena de despedida del programa
	 * @return Texto de despedida del programa
	 */
	private static String finPrograma() {
		StringBuffer cad = new StringBuffer("");
		cad.append("\n");
		cad.append("--------------------------\n");
		cad.append("- FIN EJECUCIÓN PROGRAMA -");
		cad.append("\n");
		cad.append("--------------------------\n");
		cad.append("\n");
		return cad.toString();
	}
	
}
