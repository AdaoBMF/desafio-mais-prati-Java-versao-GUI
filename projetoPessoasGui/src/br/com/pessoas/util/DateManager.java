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
	private static Integer inputDay() {
		Integer day = 0;
		Boolean first = true;
		while (day == 0) {
			if (!first) {
				Gui.showTxt("Aviso!\nEntrada inválida\n \nDigite apenas números de 1 a 31");
			}
			try {
				Integer entry = Gui.getInt("Digite o dia do mês ");
				if (entry > 0 && entry <= 31) {
					day = entry;
				}
			} catch (Exception e) {}

			first = false;
		}
		return day;
	}

	/**
	 * Metodo auxiliar que recebe e valida a entrada do mes(numeral) que ira compor a data
	 * 
	 * @return String
	 */
	private static Integer inputMonth() {
		Integer month = 0;
		Boolean first = true;
		while (month == 0) {
			if (!first) {
				Gui.showTxt("Aviso!\nEntrada inválida\n \nDigite apenas números de 1 a 12");
			}
			try {
				Integer entry = Gui.getInt("Digite o nº do mês ");
				if (entry > 0 && entry <= 12) {
					month = entry;
				}
			} catch (Exception e) {}
			first = false;
		}
		return month;
	}

	/**
	 * Metodo auxiliar que recebe e valida a entrada do ano que ira compor a data 
	 * 
	 * @return String
	 */
	private static Integer inputYear() {
		int validYear = Calendar.getInstance().get(Calendar.YEAR);
		Integer year = 0;
		Boolean first = true;
		while (year == 0) {
			if (!first) {
				Gui.showTxt("Aviso!\nEntrada inválida\n \nDigite um ano válido ");
			}
			try {
				Integer entry = Gui.getInt("Digite o ano ");
				if (entry >= (validYear-100) && entry <= validYear) {
					year = entry;
				}
			} catch (Exception e) {}
			first = false;
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
		String str = "";
		Boolean check = true;
		while(check) {
			Integer day = inputDay();
			Integer month = inputMonth();
			Integer year = inputYear();
			if(EntryCheck.dateCheck(day, month, year)) {
				str = String.format("%s/%s/%s", day, month, year);
				check = false;
			}else {
				Gui.showTxt("Aviso! \nData Inválida \n");
			}
		}
		try {
			date = dayF.parse(str);
		} catch (ParseException e) {
			Gui.showTxt("Não foi possivel armazenar a data");
			Gui.showTxt(e.toString());
		}
		return date;
	}

}
