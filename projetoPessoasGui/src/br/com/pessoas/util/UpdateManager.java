package br.com.pessoas.util;

import java.util.Date;

import br.com.pessoas.UiControl.Gui;
import br.com.pessoas.model.Individuo;

public class UpdateManager {
	
	public static void updateName(Individuo target) {
		String name = Gui.getTxt("Nome: ");
		target.setName(name);
	}
	
	public static void updatePhone(Individuo target) {
		String phone = Gui.getTxt("Telefone: ");
		target.setPhone(phone);
	}
	
	public static void updateBirthdate(Individuo target) {
		Date date = DateManager.inputDate();
		target.setBirthdate(date);
	}
	
	public static void updateFinalGrade(Individuo target) {
		Double grade = GradeManager.getGrade();
		target.setFinalGrade(grade);
	}
	
	public static void updateLastUpdate(Individuo target) {
		Date date = new Date();
		target.setLastUpdate(date);
	}
	
}
