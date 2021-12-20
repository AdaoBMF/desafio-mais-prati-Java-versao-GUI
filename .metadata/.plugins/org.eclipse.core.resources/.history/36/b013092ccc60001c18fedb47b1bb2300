package br.com.pessoas.util;

import br.com.pessoas.UiControll.*;

/**
 * Classe auxiliar para validacao e captacao do valor da nota
 * 
 * @author Adao Vieira
 *
 */
public class GradeManager {
	
	/**
	 * Metodo que valida e captura a nota final
	 * 
	 * @return Double
	 */
	public static Double getGrade() {
		Double grade = null;
		while(grade == null) {
			try {
				grade = Gui.getDouble("Digite a nota final: ");				
			}catch (Exception e) {
				grade = null;
				Gui.showTxt("Aviso!"
						+" \nEntrada invalida. "
						+"\nDigite apenas valores numéricos");
			}
		}
		return grade;
	}

}
