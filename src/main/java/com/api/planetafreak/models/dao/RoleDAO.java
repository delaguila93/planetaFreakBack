package com.api.planetafreak.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.api.planetafreak.models.entity.Role;

public interface RoleDAO extends CrudRepository<Role, Long> {

}
