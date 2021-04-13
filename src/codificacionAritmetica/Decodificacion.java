package codificacionAritmetica;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import vista.Main;

/**
 * La clase decodificacion me permite encontrar un valor en forma de cadena de
 * caracteres para un conjunto de simbolos e intervalos extraidos de una fuente,
 * y a partir de un numero decimal entre 0 y 1, dada la longitud de la cadena
 * final
 * 
 * @author angel
 *
 */
public class Decodificacion {

	/**
	 * El array con los simbolos frecuencias e intervalos de los simbolos
	 */
	ArrayList<SimboloIntervalo> finter = new ArrayList<SimboloIntervalo>();

	/**
	 * Constructor de la clase, que me permite utilizar el array que le pasa la
	 * clase Aritmetica ya configurado
	 * 
	 * @param fuenteIntervalos
	 */
	public Decodificacion(ArrayList<SimboloIntervalo> fuenteIntervalos) {
		this.finter = fuenteIntervalos;
	}

	/**
	 * Metodo que nos permite decodificar un numero decimal dada su longitud de
	 * caracteres.
	 * 
	 * @param num Numero decimal a decodificar
	 * @param l   Longitud de la cadena final
	 * @return Cadena de texto decodificada
	 */
	public String decode(String num, int l) {

		BigDecimal f = new BigDecimal(num);
		
		StringBuffer cadena = new StringBuffer("");
		SimboloIntervalo si;

		while (l > 0) {
			si = encontrar(f);
			cadena.append(si.getSimbolo().getSimbolo());
			f = normalizar(si, f);
			l--;
		}
		return cadena.toString();
	}

	/**
	 * Metodo encontrar, que nos permite buscar el símbolo al que corresponde un
	 * valor, mirando dentro de su intervalo para decidir cual es
	 * 
	 * @param f Numero decimal
	 * @return Simbolo al que corresponde
	 */
	private SimboloIntervalo encontrar(BigDecimal f) {

		for (SimboloIntervalo si : this.finter) {
			if (si.isIn(f)) {
				return si;
			}
		}
		return null;
	}

	/**
	 * Metodo normalizar que nos normaliza el numero decimal dado ya el intervalo al
	 * que pertenece para subir de caracter
	 * 
	 * @param si  Simbolo al que pertenece el numero
	 * @param num Numero decimal
	 * @return Numero decimal normalizado
	 */
	private BigDecimal normalizar(SimboloIntervalo si, BigDecimal num) {

		BigDecimal inferior = si.getInferior();
		BigDecimal superior = si.getSuperior();

		// H - L
		BigDecimal h_l = superior.subtract(inferior);

		// F - L
		BigDecimal f_l = num.subtract(inferior);

		// re1 / res2
		return f_l.divide(h_l, Main.PRECISION, RoundingMode.HALF_EVEN);
	}

}
