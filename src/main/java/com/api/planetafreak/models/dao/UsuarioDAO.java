package com.api.planetafreak.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.api.planetafreak.models.entity.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario,Long> {
	
	public Usuario findByUsername (String username);

}
