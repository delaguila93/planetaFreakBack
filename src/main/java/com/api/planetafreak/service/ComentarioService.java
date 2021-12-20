package com.api.planetafreak.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.planetafreak.models.dao.ComentarioDAO;

public class ComentarioService implements IComentarioService {
	
	@Autowired
	private ComentarioDAO comentarioDao;

}
