package br.com.pessoas.model;

import java.util.Date;

/**
 * Classe pai que padroniza as cria��o das classes funcionais(Pessoa e Aluno)
 * 
 * @author Adao Vieira
 *
 */
public abstract class Individuo implements Comparable<Individuo> {

	// definicao das propriedades da classe
	private Integer id = null;
	private String name;
	private String phone;
	private Date birthdate;
	private Double finalGrade = null;
	private Date registerDate;
	private Date lastUpdate;

	/**
	 * Construtor padr�o da classe Individuo
	 * 
	 * @param name
	 * @param phone
	 * @param birthdate
	 * @param registerDate
	 */
	public Individuo(String name, String phone, Date birthdate) {
		super();
		this.name = name;
		this.phone = phone;
		this.birthdate = birthdate;
		this.registerDate = new Date();
		this.lastUpdate = new Date();
	}

	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date fileUpdate) {
		this.lastUpdate = fileUpdate;
	}

	public double getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(double finalGrade) {
		this.finalGrade = finalGrade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * Sobrescricao do metodo "compareTo" para permitir que os objetos armazenados
	 * sejam classificados em ordem alfabetica tendo os nomes como parametro de
	 * classificacao
	 */
	@Override
	public int compareTo(Individuo ind) {
		return this.name.compareTo(ind.getName());		
	}
}
