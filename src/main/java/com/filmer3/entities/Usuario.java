package com.filmer3.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String username;
	@NotEmpty(message = "El campo nombre contraseña no puede estar vacío")
	private String password;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuario_rol", joinColumns = @JoinColumn(name="usuario_id"), 
	inverseJoinColumns =  @JoinColumn(name="rol_id"))
	private Set<Rol> roles = new HashSet<Rol>();

	public Usuario(Long id, String username, String password, Set<Rol> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	public Usuario() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	


}
