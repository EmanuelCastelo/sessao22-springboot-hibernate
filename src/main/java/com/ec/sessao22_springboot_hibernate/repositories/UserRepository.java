package com.ec.sessao22_springboot_hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.sessao22_springboot_hibernate.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
