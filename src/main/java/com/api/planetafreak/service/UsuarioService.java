package com.api.planetafreak.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.planetafreak.models.dao.RoleDAO;
import com.api.planetafreak.models.dao.UsuarioDAO;
import com.api.planetafreak.models.entity.Role;
import com.api.planetafreak.models.entity.Usuario;


@Service
public class UsuarioService implements UserDetailsService,IUsuarioService {
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private RoleDAO roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null) {
			logger.error("Error: No existe el usuario " + username + " en el sistema");
			throw new UsernameNotFoundException("Error: No existe el usuario " + username + " en el sistema");
		}
			List<GrantedAuthority> authorities = new ArrayList<>();
		
			authorities.add(new SimpleGrantedAuthority(usuario.getRoles().getNombre()));
		
		
		return new User(usuario.getUsername(), usuario.getPassword(), true,true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {		
		return (List<Usuario>)usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {		
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario actualizar(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public Usuario crearUsuario(Usuario usuario) {
		
		Role rol = new Role();
		rol.setId((long)1);
		usuario.setRoles(rol);
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioDao.save(usuario);
	}
	
	@Override
	public void delete(Long id) {
		usuarioDao.deleteById(id);
		
	}

	@Override
	public void actualizarCompra(Usuario u) {
		usuarioDao.save(u);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario buscarNombre(String username) {
		return usuarioDao.buscarNombre(username);
	}

}
