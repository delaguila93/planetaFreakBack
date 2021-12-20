package com.api.planetafreak.service;

import java.util.List;

import com.api.planetafreak.models.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();

	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void delete(Long id);

}
