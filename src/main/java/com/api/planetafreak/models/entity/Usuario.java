package com.api.planetafreak.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	


	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "usuario")
	private String username;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;

	@ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name="usuarios_roles", 
			   joinColumns = @JoinColumn(name="usuario_id"),
			   inverseJoinColumns = @JoinColumn(name = "role_id"),
			   uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","role_id"})})
	private List<Role> roles;
	
	

	@OneToMany(mappedBy = "usuario")
	private List<Comentario> comentarios;
	
	
	@ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name="usuarios_productos", 
			   joinColumns = @JoinColumn(name="usuario_id"),
			   inverseJoinColumns = @JoinColumn(name = "producto_id"),
			   uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","producto_id"})})
	private List<Producto> productosComprados;
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellidos() {
		return apellidos;
	}



	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public List<Role> getRole() {
		return roles;
	}



	public void setRole(List<Role> roles) {
		this.roles = roles;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
