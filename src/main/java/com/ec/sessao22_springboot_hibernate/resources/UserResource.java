package com.ec.sessao22_springboot_hibernate.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.sessao22_springboot_hibernate.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u1 = new User(1L, "Emanuel", "emanuel@jacarei.sp.gov.br", "39559200", "teste");
		return ResponseEntity.ok().body(u1);
	}

}
