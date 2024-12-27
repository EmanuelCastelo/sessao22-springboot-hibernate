package com.ec.sessao22_springboot_hibernate.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Classe que representa um usuário do sistema. Contém informações como nome,
 * e-mail, telefone, senha e os pedidos realizados por esse usuário.
 * 
 * A anotação @Entity define que a classe é uma entidade JPA, representando uma
 * tabela no banco de dados.
 */
@Entity
@Table(name = "tb_user") // Define o nome da tabela no banco de dados
public class User implements Serializable {

	/**
	 * Serial version UID para garantir a compatibilidade entre diferentes versões
	 * da classe.
	 */
	private static final long serialVersionUID = 1L;

	/** Identificador único do usuário (chave primária). */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // A chave primária será gerada automaticamente pelo banco de
														// dados
	private Long id;

	/** Nome do usuário. */
	private String name;

	/** E-mail do usuário. */
	private String email;

	/** Número de telefone do usuário. */
	private String phone;

	/** Senha do usuário. */
	private String password;

	/** Lista de pedidos realizados pelo usuário. */
	@JsonIgnore // A anotação @JsonIgnore impede que a lista de pedidos seja serializada no JSON
	@OneToMany(mappedBy = "client") // Relacionamento de um usuário para muitos pedidos
	private List<Order> orders = new ArrayList<>(); // Lista de pedidos do usuário

	/**
	 * Construtor padrão da classe User. Utilizado para criar uma instância de
	 * usuário sem valores iniciais.
	 */
	public User() {
	}

	/**
	 * Construtor para criar um usuário com valores iniciais.
	 * 
	 * @param id       O identificador único do usuário.
	 * @param name     O nome do usuário.
	 * @param email    O e-mail do usuário.
	 * @param phone    O telefone do usuário.
	 * @param password A senha do usuário.
	 */
	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	/**
	 * Retorna o identificador único do usuário.
	 * 
	 * @return O identificador do usuário.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o identificador único do usuário.
	 * 
	 * @param id O identificador do usuário.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retorna o nome do usuário.
	 * 
	 * @return O nome do usuário.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define o nome do usuário.
	 * 
	 * @param name O nome do usuário.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retorna o e-mail do usuário.
	 * 
	 * @return O e-mail do usuário.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Define o e-mail do usuário.
	 * 
	 * @param email O e-mail do usuário.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna o número de telefone do usuário.
	 * 
	 * @return O telefone do usuário.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Define o número de telefone do usuário.
	 * 
	 * @param phone O telefone do usuário.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Retorna a senha do usuário.
	 * 
	 * @return A senha do usuário.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Define a senha do usuário.
	 * 
	 * @param password A senha do usuário.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Retorna a lista de pedidos realizados pelo usuário.
	 * 
	 * @return A lista de pedidos.
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * Método hashCode para garantir o cálculo correto do código de hash para a
	 * comparação de objetos.
	 * 
	 * @return O código de hash baseado no identificador do usuário.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Método equals para comparar dois objetos User com base no identificador
	 * único.
	 * 
	 * @param obj O objeto a ser comparado.
	 * @return True se os objetos forem iguais, baseado no identificador, false caso
	 *         contrário.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id); // Compara os ids dos usuários
	}
}
