package com.api.planetafreak.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.planetafreak.models.entity.Producto;

public interface ProductoDAO extends CrudRepository<Producto, Long>{
	
	@Query(value = "SELECT p FROM Producto p WHERE p.categoria=?1")
	public List<Producto> buscaCategoria(String categoria);
	

	@Query(value="SELECT p FROM Producto p WHERE p.nombre LIKE %?1%")
	public List<Producto> buscaNombre(String nombre);

}
