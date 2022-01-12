package br.com.pessoas.UiControll;

import javax.swing.JOptionPane;

import br.com.pessoas.util.EntryCheck;

/**
 * Classe responsavel por controlar a interface grafica
 * 
 * @author Adao Vieira
 *
 */
public class Gui {
	
	private static String warning = "Aviso! \nInsira uma Entrada V�lida: ";
	
	/**
	 * Metodo que exibe uma janela de dialogo para coletar uma entrada(String) do usuario
	 * 
	 * @param msg
	 * @return String 
	 */
	public static String getTxt(String msg) {
		boolean start = true;
		String entry = "";
		while(true) {
			if(start !=true) Gui.showTxt(warning); 
			entry = JOptionPane.showInputDialog(msg).trim();
			if(!entry.equals("")) {
				return entry;
			}
			start =false;
		}
	
	}
	
	/**
	 * Metodo que exibe uma janela de dialogo para coletar 
	 * e validar uma String alfabetica 
	 * 
	 * @param msg
	 * @return
	 */
	public static String getAlpha(String msg) {
		Boolean first = true;
		String entry = "";
		while(true) {
			if(!first) {
				Gui.showTxt(warning + "(apenas letras)");
			}
			entry = Gui.getTxt(msg);
			if(EntryCheck.alphaCheck(entry)) {
				return entry;
			}
			first = false;
		}
	}
	
	/**
	 * Metodo que exibe uma janela de dialogo para coletar 
	 * e validar numero de telefone com 10 a 12 digitos
	 * 
	 * @return string numerica
	 */
	public static String getPhone(String msg) {
		boolean first = true;
		String entry = "";
		while (true) {
			if (!first) {
				Gui.showTxt(warning + "(apenas n�meros de 8 a 15 digitos)");
			}
			entry = Gui.getTxt(msg);
			if (!entry.equals("") && EntryCheck.PhoneCheck(entry)) {
				return entry;
			}
			first = false;
		}
	}	

	/**
	 * Metodo que exibe uma janela de dialogo para coletar uma entrada(int) do usuario
	 * 
	 * @param msg
	 * @return int 
	 */
	public static int getInt(String msg) {
		return Integer.parseInt(JOptionPane.showInputDialog(msg));
	}
	
	/**
	 * Metodo que exibe uma janela de dialogo para coletar uma entrada(double) do usuario
	 * 
	 * @param msg
	 * @return double 
	 */
	public static double getDouble(String msg) {
		return Double.parseDouble(JOptionPane.showInputDialog(msg).replace(",", "."));
	}
	
	/**
	 * Metodo que cria uma janela de dialogo para exibir um output destinado ao usuario
	 * 
	 * @param txt
	 */
	public static void showTxt(String txt){
		JOptionPane.showMessageDialog(null, txt);
	}

}
