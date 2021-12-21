package com.api.planetafreak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.planetafreak.models.dao.ProductoDAO;
import com.api.planetafreak.models.dao.UsuarioDAO;
import com.api.planetafreak.models.entity.Producto;
import com.api.planetafreak.models.entity.Usuario;

@Service
public class ProductoService implements IProductoService{
	
	@Autowired
	private ProductoDAO productoDao;
	

	@Autowired
	private IUsuarioService usuarioService;
	
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
	 @Override
	 public List<Producto> buscarCategoria(String categoria){
		 return (List<Producto>) productoDao.buscaCategoria(categoria);
	 }

	 @Override
	 public List<Producto> buscarNombre(String nombre){
		 return (List<Producto>) productoDao.buscaNombre(nombre);
	 }

	@Override
	@Transactional
	public Producto comprar(Producto p, String username) {
		// TODO Auto-generated method stub
		
		p.setCantidad(p.getCantidad()-1);
		if(p.getCantidad() == 0) {
			p.setEstado("Agotado");
		}
		Usuario u = usuarioService.buscarNombre(username);
		u.getProductosComprados().add(p);
		usuarioService.actualizar(u);
		return productoDao.save(p);
		
	}

}
