package br.com.pessoas.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import br.com.pessoas.UiControll.Gui;
import br.com.pessoas.model.Aluno;
import br.com.pessoas.model.Individuo;
import br.com.pessoas.model.Pessoa;
import br.com.pessoas.util.DateManager;
import br.com.pessoas.util.GradeManager;
import br.com.pessoas.util.UpdateManager;

/**
 * Classe que contem os metodos que efetuam as demandas(CRUD) do usuario
 * 
 * @author Adao Vieira
 *
 */
public class ActionControll {
	// Array que ira armazenar os objetos criados durante a sessao
	private static ArrayList<Individuo> individuos = new ArrayList<>();

	/**
	 * metodo que cria um objeto Individuo(Pessoa/Aluno) e o armazena na Array
	 * individuos
	 */
	private static void register() {
		Individuo ind = null;
		String name = Gui.getTxt("Nome: ");
		String phone = Gui.getTxt("Telefone: ");
		Gui.showTxt("Data de nascimento: (dd/mm/aaaa) ");
		Date birthdate = DateManager.inputDate();
		Double finalGrade = Menu.gradeCheck() ? GradeManager.getGrade() : null;

		try {
			if (finalGrade == null) {
				ind = new Pessoa(name, phone, birthdate);
				individuos.add(ind);
			} else {
				ind = new Aluno(name, phone, birthdate, finalGrade);
				individuos.add(ind);
			}
			Gui.showTxt(ind.toString());
		} catch (Exception e) {
			Gui.showTxt("N�o foi possivel concluir a inclus�o" + "\nErro: " + e.toString());
		}

	}

	/**
	 * Metodo que retorna um individuo pesquisado pelo nome
	 * 
	 * @param nome
	 * @return Individuo
	 */
	private static Individuo getIndByName(String search) {
		Individuo ind = null;
		for (Individuo obj : individuos) {
			if (obj.getName().equalsIgnoreCase(search)) {
				ind = obj;
			}
		}
		return ind;
	}
	
	private static void search() {
		
		Individuo ind = null;
		int op = 0;
		int prop =0;
		
		String target = Gui.getTxt("Insira o nome completo do Registro: ");
		try {
			ind = getIndByName(target);
			Gui.showTxt(ind.toString());
		}catch(Exception e) {
			Gui.showTxt("Registro n�o encontrado");
		}
		if(ind != null) {
			op = Menu.menuEdit();
			switch(op){
				case 1:
					prop = Menu.menuProp();
					if(prop == 5)
						break;
					updateIndividuo(ind, prop);
					break;
				case 2:
					deleteIndividuo(ind);
					break;
				case 3:
					break;
			}
		}
	}


	/**
	 * Metodo que lista todos os Individuos armazenados
	 */
	private static String listIndividuos() {
		if(individuos.size() > 0) {
			try {
				Collections.sort(individuos);
				return individuos.toString().replace(",", "");			
			}catch(Exception e) {
				return "Nenhum Registro Encontrado";
			}
		}else {
			return "O Cadastro Est� Vazio";
		}
	}

	/**
	 * Metodo que lista todos Alunos dastrados
	 * 
	 * @return String
	 */
	private static String listAlunos() {
		ArrayList<Aluno> arr = new ArrayList<>();
		Individuo ind = null;
		try {
			for (int i = 0; i < individuos.size(); i++) {
				ind = individuos.get(i);
				if (ind instanceof Aluno) {
					arr.add((Aluno) ind);
				}
			}
			if(arr.size() > 0) {
				Collections.sort(arr);
				return arr.toString().replace(",", "");				
			}else {
				return "Nenhum Aluno registrado";
			}
		}catch(Exception e) {
			return "Nenhum Registro Encontrado";
		}
	}

	/**
	 * Metodo que lista todas pessoas cadastradas
	 * 
	 * @return String
	 */
	private static String listPessoas() {
		ArrayList<Pessoa> arr = new ArrayList<>();
		Individuo ind = null;
		try {
			for (int i = 0; i < individuos.size(); i++) {
				ind = individuos.get(i);
				if (ind instanceof Pessoa) {
					arr.add((Pessoa) ind);
				}
			}
			if(arr.size() > 0) {
				Collections.sort(arr);
				return arr.toString().replace(",", "");				
			}else {
				return "Nenhuma Pessoa registrada";
			}
		}catch(Exception e) {
			return "Nenhum Registro Encontrado";
		}
	}

	/**
	 * Metotdo que realiza a edicao de um Individuo especifico
	 * 
	 * @param target
	 * @param prop
	 */
	private static void updateIndividuo(Individuo target, int prop) {
		Individuo ind = null;
		if (target instanceof Pessoa) {
			ind = (Pessoa) target;
		} else if (target instanceof Aluno) {
			ind = (Aluno) target;
			if (prop == 4)
				UpdateManager.updateFinalGrade(ind);
		}
		if (ind != null) {
			switch (prop) {
			case 1:
				UpdateManager.updateName(ind);
				break;
			case 2:
				UpdateManager.updatePhone(ind);
				break;
			case 3:
				UpdateManager.updateBirthdate(ind);
				break;
			}
			UpdateManager.updateLastUpdate(ind);
		}
		Gui.showTxt(
				"Cadastro atualizado\n"
				+ind.toString()
				);
	}
	
	/**
	 * Metodo que exclui um individuo especifico
	 * 
	 * @param target
	 */
	private static void deleteIndividuo(Individuo target) {
		boolean confirm = Menu.deleteAlert(target);
		if(confirm == true) {
			String name = target.getName();
			individuos.remove(target);
			Gui.showTxt(name +" removido com sucesso.");		
		}else {
			Gui.showTxt("Exclus�o Cancelada");
		}
	}
	
	/**
	 * 
	 */
	public static void start() {
		
		
		boolean session = true;
		Integer op;
		Gui.showTxt(
				"Bem vindo!\n"
				+ "\nSistema de Cadastro Foobar " + DateManager.printDateTime(new Date())
				);
		while(session == true) {
			op = Menu.menuPrincipal();
			switch(op) {
				case 1:
					register();
					break;
				case 3:
					search();
					break;
				case 4:
					session = Menu.sessionAlert();
					break;
				case 5:
					Gui.showTxt(listIndividuos());
					break;
				case 6:
					Gui.showTxt(listPessoas());
					break;
				case 7:
					Gui.showTxt(listAlunos());
					break;
				case 8:
					continue;
			}
		}
		Gui.showTxt(
				"Fim da Sess�o"
				);
	}
}
