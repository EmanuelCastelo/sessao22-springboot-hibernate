package com.ec.sessao22_springboot_hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.sessao22_springboot_hibernate.entities.OrderItem;
import com.ec.sessao22_springboot_hibernate.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
