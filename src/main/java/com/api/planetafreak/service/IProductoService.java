package com.api.planetafreak.service;

import java.util.List;

import com.api.planetafreak.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();

	public Producto findById(Long id);
	
	public Producto save(Producto usuario);
	
	public void delete(Long id);

}
