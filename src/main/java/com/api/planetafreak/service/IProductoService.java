package com.api.planetafreak.service;

import java.util.List;

import com.api.planetafreak.models.entity.Producto;
import com.api.planetafreak.models.entity.Usuario;

public interface IProductoService {
	
	public List<Producto> findAll();

	public Producto findById(Long id);
	
	public List<Producto> buscarNombre(String nombre);
	
	public List<Producto> buscarCategoria(String categoria);
	
	public Producto save(Producto usuario);
	
	public void delete(Long id);

	public Producto comprar(Producto p,String username);
	
}
