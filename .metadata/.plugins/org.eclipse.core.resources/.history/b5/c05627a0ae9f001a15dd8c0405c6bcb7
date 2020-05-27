package com.sumset.biblioteca.modelos.entidades;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "identificacion no puede estar vacio")
	@Size(min = 5,max = 10)
	@Column(name = "identificacion")
	private String identificacion;

	@NotEmpty(message = "nombres no puede estar vacio")
	@Size(min = 2,max = 12)
	@Column(name="nombres")
	private String nombres;
	
	@NotEmpty(message = "apellidos no puede estar vacio")
	@Size(min = 2,max = 20)
	@Column(name="apellidos")
	private String apellidos;
	
	
	@NotEmpty(message = "fecha nacimiento no puede estar vacia")
	@Column(name="fechaNacimiento")
	private Date fechaNacimiento;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "usuario")
	@JsonIgnore 
	private List<Prestamo> prestamos;
	
	public Usuario() {
		super();
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	
	private static final long serialVersionUID = 1L;

}
