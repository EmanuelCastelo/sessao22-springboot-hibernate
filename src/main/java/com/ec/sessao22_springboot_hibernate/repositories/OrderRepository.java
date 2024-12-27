package com.ec.sessao22_springboot_hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.sessao22_springboot_hibernate.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
