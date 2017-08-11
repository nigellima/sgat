package integra;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
	public static boolean isBlankOrNull(String str) {
	    return (str == null || "".equals(str.trim()));
	}
	/**
	 * Converte uma string data formato padrão "dd/MM/yyyy HH:MM:SS" para o tipo Date
	 * @param valor 	A string que representa uma data no formato "dd/MM/yyyy HH:mm:ss"
	 * @param formato
	 * @return Retorna a data convertida para o tipo Date ou null caso não seja possível
	 */

	public static Date getDate(String valor) {		
		String formato = "dd/MM/yyyy HH:mm:SS";
		return Utils.getDate(valor, formato);
	}
	/**
	 * Converte uma string data formato "formato para o tipo Date
	 * @param valor 	A string que representa uma data no formato "formato"
	 * @param formato	O formato da data que está na string valor, por exemplo: "dd/MM/yyyy HH:mm:ss"
	 * @return Retorna a data convertida para o tipo Date ou null caso não seja possível
	 */

	public static Date getDate(String valor, String formato) {
		Date data = null;
		try {
		    SimpleDateFormat sdf = new SimpleDateFormat(formato);
		    sdf.setLenient(false);
		    data = sdf.parse(valor);
//		    if (!valor.equals(sdf.format(data))) {
//		        data = null;
//		    }
		} catch (ParseException ex) {
		    data = null;
		}
		return data;
	}
}
