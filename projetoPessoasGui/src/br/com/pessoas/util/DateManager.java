package br.com.pessoas.util;

import java.text.*;
import java.util.*;

import br.com.pessoas.UiControll.Gui;

/**
 * Classe auxiliar para gerenciar datas
 * 
 * @author Adao Vieira
 *
 */
public class DateManager {
	
	//obj usados para auxuliar a formatacao das datas nos metodos
	private static DateFormat dayF = new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat dayTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	
	
	public static String printDate(Date entry) {		
		return dayF.format(entry);
	}
	public static String printDateTime(Date entry) {		
		return dayTime.format(entry);
	}
	
	/**
	 * Metodo auxiliar que recebe e valida a entrada do dia que ira compor a data
	 * 
	 * @return String
	 */
	private static String inputDay() {
		String day = "";
		Boolean check = false;
		while (day.length() == 0) {
			if (check == true) {
				Gui.showTxt("Aviso!\nEntrada inv�lida\nDigite apenas n�meros de 1 a 31");
			}
			try {
				Integer entry = Gui.getInt("Digite o dia do m�s ");
				if (entry > 0 && entry <= 31) {
					day = entry > 10 ? "0" + entry : day + entry;
				}
			} catch (Exception e) {}

			check = true;
		}
		return day;
	}

	/**
	 * Metodo auxiliar que recebe e valida a entrada do mes(numeral) que ira compor a data
	 * 
	 * @return String
	 */
	private static String inputMonth() {
		String month = "";
		Boolean check = false;
		while (month.length() == 0) {
			if (check == true) {
				Gui.showTxt("Aviso!\nEntrada inv�lida\nDigite apenas n�meros de 1 a 12");
			}
			try {
				Integer entry = Gui.getInt("Digite o n� do m�s ");
				if (entry > 0 && entry <= 12) {
					month = entry > 10 ? "0" + entry : month + entry;
				}
			} catch (Exception e) {}
			check = true;
		}
		return month;
	}

	/**
	 * Metodo auxiliar que recebe e valida a entrada do ano que ira compor a data 
	 * 
	 * @return String
	 */
	private static String inputYear() {
		int validYear = Calendar.getInstance().get(Calendar.YEAR);
		String year = "";
		Boolean check = false;
		while (year.length() == 0) {
			if (check == true) {
				Gui.showTxt("Aviso!\nEntrada inv�lida\nDigite apenas n�meros ");
			}
			try {
				Integer entry = Gui.getInt("Digite o ano ");
				if (entry >= (validYear-100) && entry <= validYear) {
					year += entry;
				}
			} catch (Exception e) {}
			check = true;
		}
		return year;
	}

	/**
	 * Metodo que recebe a entrada do usuario valida, formata e retorna em formato Date 
	 * 
	 * @return Date
	 */
	public static Date inputDate() {
		Date date = null;
		String day = inputDay();
		String month = inputMonth();
		String year = inputYear();
		String str = String.format("%s/%s/%s", day, month, year);
		try {
			date = dayF.parse(str);
		} catch (ParseException e) {
			Gui.showTxt("N�o foi possivel armazenar a data");
			Gui.showTxt(e.toString());
		}
		return date;
	}

}