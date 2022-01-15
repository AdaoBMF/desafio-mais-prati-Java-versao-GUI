package br.com.pessoas.controller;

import br.com.pessoas.UiControl.Gui;
import br.com.pessoas.model.Individuo;

/**
 * Classe responsavel pro controlar a interacao com o usuario, 
 * disponibilizando as opcoes e feedbacks via menu e recebendo os inputs do usuario
 * 
 * @author Adao Vieira
 *
 */
public class Menu {
	
	private static String warning = "Aviso!\nOpção Inválida \nDigite apenas o número da Operação desejada";
	
	/**
	 * Metodo que executa a interface principalde interacao do usuario com as funcoes CRUD 
	 * 
	 * @return int
	 */
	public static int menuPrincipal() {
		int op = 0;
		while(op == 0) {
			try {
				int entry = Gui.getInt(
						"\nEscolha a Operação desejada\n"
							+ "\n  [1] Adicionar Novo Registro "
							+ "[2] Listar(Todos/Pessoas/Alunos) "
							+ "[3] Buscar (Editar/Excluir) "
							+ "[4] Sair \n"
							+ "\nOperação: "
							);
				if(entry >= 1 && entry <= 4) {
					if(entry == 2) {
						op = menuListar();
					}else {
						op = entry;
					}
				}else {
					Gui.showTxt(warning);
				}
			}catch(Exception e) {
				Gui.showTxt(warning);
			}
		}
		return op;
	}
	
	/**
	 * Menu auxiliar para definicao do tipo de objeto que deve ser listado
	 * 
	 * @return int
	 */
	private static int menuListar() {
		
		int op = 0;
		while(op == 0) {
			try {
				int entry = Gui.getInt(
						"LIstar: "
								+" [1] Todos "
								+"[2] Pessoas "
								+"[3] Alunos "
								+"[4] Voltar "
								+"\nOpção: "
						);
				if(entry >=1 && entry <= 4) {
					op = entry == 1 ? 5 : entry == 2 ? 6 : entry == 3 ? 7 : 8 ;
				}else {
					Gui.showTxt(warning);
				}
			}catch(Exception e) {
				Gui.showTxt(warning);
			}
		}
		return op;
	}
	
	/**
	 * Menu auxiliar para definicao da funcao a ser executada no objeto pesquisado
	 * 
	 * @return int
	 */
	public static int menuEdit() {
		int op = 0;
		while(op == 0 ) {
			try {
				int entry = Gui.getInt(
						"Opções: "
						+" [1] Editar(Nome/Telefone/Data de Nascimento/Nota Final) "
						+"[2] Excluír "
						+"[3] Voltar "
						+"\nOpção: "
						);
				if(entry >= 1 && entry <= 3) {
					op = entry; 
				}else {
					Gui.showTxt(warning);					
				}
			}catch(Exception e) {
				Gui.showTxt(warning);
			}
		}
		return op;
	}
	
	/**
	 * Menu auxiliar para definicao do tipo da propriedade que sera editada
	 * 
	 * @return
	 */
	public static int menuProp() {
		
		int op =0;
		while(op == 0) {
			try {
				int entry = Gui.getInt(
					"Opções: "
					+" [1] Editar Nome "
					+" [2] Editar Telefone "
					+" [3] Editar Data de Nascimento "
					+" [4] Editar Nota Final "
					+" [5] Sair "
					+"\nOpção: "						
						);
				if(entry >= 1 && entry <= 5) {
					op = entry;
				}else {
					Gui.showTxt(warning);					
				}
			}catch(Exception e) {
				Gui.showTxt(warning);				
			}
		}
		return op;
	}
		
	/**
	 * Metodo que pergunta se sera inserida uma nota e valida a resposta
	 * 
	 * @return boolean
	 */
	public static boolean gradeCheck() {
		//indentifica a primeira execucao
		String check = "first";
		while(!check.equalsIgnoreCase("S") && !check.equalsIgnoreCase("N")){
			
			//se n�o for a primeira execucao mostra um warning
			if(!check.equals("first")) Gui.showTxt("Aviso!\nDigite apenas S ou N ");
			check = Gui.getTxt("Inserir nota? S/N ");			
		}
		if(check.equalsIgnoreCase("S")) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Metodo para confirmar a finalizacao da secao
	 * 
	 * @return boolean
	 */
	public static boolean sessionAlert() {
		
		//indentifica a primeira execucao
		String check = "first";
		while(!check.equalsIgnoreCase("S") && !check.equalsIgnoreCase("N")){
			
			//se n�o for a primeira execucao mostra um warning
			if(!check.equals("first")) Gui.showTxt("Aviso!\nDigite apenas S ou N ");
			
			check = Gui.getTxt(
					"Aviso!"
					+"\nAo sair da sessão, todos os dados serão perdidos"
					+ "\nDeseja finalizar a sessão? S/N "
					);
		}
		if(check.equalsIgnoreCase("S")) {
			return false;
		}else {
			return true;
		}
	}
	public static boolean deleteAlert(Individuo ind) {
		
		//indentifica a primeira execucao
		String check = "first";
		while(!check.equalsIgnoreCase("S") && !check.equalsIgnoreCase("N")){
			
			//se n�o for a primeira execucao mostra um warning
			if(!check.equals("first")) Gui.showTxt("Aviso!\nDigite apenas S ou N ");
			
			check = Gui.getTxt(
					"Aviso!"
					+"O Registro " + ind.getName() + " Será Excluído permanentemente"
					+ "\nDeseja Confirmar a Exclusão? S/N "
					);
		}
		if(check.equalsIgnoreCase("S")) {
			return true;
		}else {
			return false;
		}
	}

}
