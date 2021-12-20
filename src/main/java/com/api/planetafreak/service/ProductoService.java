package com.api.planetafreak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.planetafreak.models.dao.ProductoDAO;
import com.api.planetafreak.models.entity.Producto;

public class ProductoService implements IProductoService{
	
	@Autowired
	private ProductoDAO productoDao;

	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return (List<Producto>)productoDao.findAll();
	}

	@Override
	public Producto findById(Long id) {
		// TODO Auto-generated method stub
		return (Producto)productoDao.findById(id).orElse(null);
	}

	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return productoDao.save(producto);
	}

	@Override
	public void delete(Long id) {
		productoDao.deleteById(id);
		
	}

}
