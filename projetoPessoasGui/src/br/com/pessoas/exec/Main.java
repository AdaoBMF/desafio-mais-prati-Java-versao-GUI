package br.com.pessoas.exec;

import java.util.Date;

import br.com.pessoas.controller.ActionControll;
import br.com.pessoas.model.Pessoa;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {

		//lista para testes => precisa mudar individuos para public
//		for(int i = 0; i<= 15; i++) {
//			ActionControll.individuos
//			.add(new Pessoa("Pessoa"+i,"12345678", new Date()));
//		}
		
		ActionControll.start();
	}
}
