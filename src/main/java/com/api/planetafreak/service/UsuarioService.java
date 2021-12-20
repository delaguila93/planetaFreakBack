package com.api.planetafreak.service;

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
import org.springframework.transaction.annotation.Transactional;

import com.api.planetafreak.models.dao.UsuarioDAO;
import com.api.planetafreak.models.entity.Usuario;



public class UsuarioService implements UserDetailsService {
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private UsuarioDAO usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null) {
			logger.error("Error: No existe el usuario " + username + " en el sistema");
			throw new UsernameNotFoundException("Error: No existe el usuario " + username + " en el sistema");
		}
			List<GrantedAuthority> authorities = usuario.getRole()
					.stream()
					.map(role -> new SimpleGrantedAuthority(role.getNombre()))
					.peek(authority -> logger.info("Role: " + authority.getAuthority()))
					.collect(Collectors.toList());
		
		
		
		return new User(usuario.getUsername(), usuario.getPassword(), true,true, true, true, authorities);
	}
	

}
