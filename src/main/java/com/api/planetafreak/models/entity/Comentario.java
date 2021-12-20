package com.api.planetafreak.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "comentarios")
public class Comentario implements Serializable {
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_comentario")
	private Long id;
	
	@Column(name = "comentario")
	private String Cometario;
	
	@Column(name="fecha_comentario")
	@Temporal(TemporalType.DATE)
	private Date fechaComentario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	@JsonIgnoreProperties({"hibernateLazyInitializer" , "handler"})
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producto")
	@JsonIgnoreProperties({"hibernateLazyInitializer" , "handler"})
	private Producto producto;
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getCometario() {
		return Cometario;
	}



	public void setCometario(String cometario) {
		Cometario = cometario;
	}



	public Date getFechaComentario() {
		return fechaComentario;
	}



	public void setFechaComentario(Date fechaComentario) {
		this.fechaComentario = fechaComentario;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Producto getProducto() {
		return producto;
	}



	public void setProducto(Producto producto) {
		this.producto = producto;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
