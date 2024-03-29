package br.com.pessoas.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Pattern;

import br.com.pessoas.UiControl.Gui;
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
	private static Integer idCounter = 1;
	
	/**
	 * metodo que cria um objeto Individuo(Pessoa/Aluno) e o armazena na Array
	 * individuos
	 */
	private static void register() {
		Individuo ind = null;
		String name = Gui.getAlpha("Nome: ");
		String phone = Gui.getPhone("Telefone: ");
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
			ind.setId(idCounter++);
			Gui.showTxt(ind.toString());
		} catch (Exception e) {
			Gui.showTxt("Não foi possível concluir a inclusão" + "\nErro: " + e.toString());
		}

	}

	/**
	 * Metodo que retorna um individuo pesquisado pelo ID
	 * 
	 * @param Integer id
	 * @return Individuo
	 */
	private static Individuo getIndById(Integer id) {
		Individuo ind = null;
		for (Individuo obj: individuos) {
			if (obj.getId() == id) ind = obj;
		}
		return ind;
	}
	
	private static void search() {
		
		Individuo ind = null;
		int op = 0;
		int prop =0;
		String target = Gui.getTxt("Insira o ID do Registro");
		int id = Pattern.matches("[0-9]", target) ? Integer.parseInt(target) : 0;
		try {
			ind = getIndById(id);
			Gui.showTxt(ind.toString());
		}catch(Exception e) {
			Gui.showTxt("Registro não encontrado");
			System.out.println(e.toString());
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
				return individuos.toString().replace(",", "\n");			
			}catch(Exception e) {
				return "Nenhum Registro Encontrado";
			}
		}else {
			return "O Cadastro Está Vazio";
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
			Gui.showTxt("Exclusão Cancelada");
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
					Gui.showList(listIndividuos());
					break;
				case 6:
					Gui.showList(listPessoas());
					break;
				case 7:
					Gui.showList(listAlunos());
					break;
				case 8:
					continue;
			}
		}
		Gui.showTxt(
				"Fim da Sessão (⌐■_■)"
				);
	}
}
