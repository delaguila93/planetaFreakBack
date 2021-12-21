package com.api.planetafreak.service;

import java.util.List;

import com.api.planetafreak.models.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();

	public Usuario findById(Long id);
	
	public Usuario actualizar(Usuario usuario);
	
	public Usuario crearUsuario(Usuario usuario);
	
	public void actualizarCompra(Usuario u);
	
	public void delete(Long id);
	
	public Usuario buscarNombre(String username);

}
