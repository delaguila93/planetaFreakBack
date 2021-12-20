package com.api.planetafreak.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.planetafreak.models.entity.Producto;

public interface ProductoDAO extends CrudRepository<Producto, Long>{

}
