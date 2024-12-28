package com.ec.sessao22_springboot_hibernate.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.ec.sessao22_springboot_hibernate.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Classe que representa um pedido realizado por um usuário. Contém informações
 * como o status do pedido, data e hora de criação e o cliente que fez o pedido.
 * 
 * A anotação @Entity define que a classe é uma entidade JPA, representando uma
 * tabela no banco de dados.
 */
@Entity
@Table(name = "tb_order") // Define o nome da tabela no banco de dados
public class Order implements Serializable {

	/**
	 * Serial version UID para garantir a compatibilidade entre diferentes versões
	 * da classe.
	 */
	private static final long serialVersionUID = 1L;

	/** Identificador único do pedido (chave primária). */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // A chave primária será gerada automaticamente pelo banco de
														// dados
	private Long id;

	/** Momento em que o pedido foi realizado. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;

	/** Status atual do pedido, representado por um código numérico. */
	private Integer orderStatus;

	/**
	 * Cliente que fez o pedido. Relacionamento de muitos para um com a classe User.
	 */
	@ManyToOne
	@JoinColumn(name = "client_id") // Define a coluna de chave estrangeira para o cliente
	private User client;
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;

	/**
	 * Construtor padrão da classe Order. Utilizado para criar uma instância de
	 * pedido sem valores iniciais.
	 */
	public Order() {
	}

	/**
	 * Construtor para criar um pedido com valores iniciais.
	 * 
	 * @param id          O identificador único do pedido.
	 * @param moment      O momento em que o pedido foi realizado.
	 * @param orderStatus O status do pedido.
	 * @param client      O cliente que fez o pedido.
	 */
	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	/**
	 * Retorna o identificador único do pedido.
	 * 
	 * @return O identificador do pedido.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o identificador único do pedido.
	 * 
	 * @param id O identificador do pedido.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retorna o momento em que o pedido foi realizado.
	 * 
	 * @return O momento do pedido.
	 */
	public Instant getMoment() {
		return moment;
	}

	/**
	 * Define o momento em que o pedido foi realizado.
	 * 
	 * @param moment O momento do pedido.
	 */
	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	/**
	 * Retorna o status do pedido como um valor da enum OrderStatus.
	 * 
	 * @return O status do pedido.
	 */
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	/**
	 * Define o status do pedido com base em um valor da enum OrderStatus.
	 * 
	 * @param orderStatus O status do pedido.
	 */
	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	/**
	 * Retorna o cliente que fez o pedido.
	 * 
	 * @return O cliente do pedido.
	 */
	public User getClient() {
		return client;
	}

	/**
	 * Define o cliente que fez o pedido.
	 * 
	 * @param client O cliente que fez o pedido.
	 */
	public void setClient(User client) {
		this.client = client;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Double getTotal() {
		double sum = 0.0;
		for (OrderItem orderItem : items) {
			sum += orderItem.getSubTotal();
		}
		return sum;
	}

	/**
	 * Método hashCode para garantir o cálculo correto do código de hash para a
	 * comparação de objetos.
	 * 
	 * @return O código de hash baseado no identificador do pedido.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Método equals para comparar dois objetos Order com base no identificador
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id); // Compara os ids dos pedidos
	}
}
