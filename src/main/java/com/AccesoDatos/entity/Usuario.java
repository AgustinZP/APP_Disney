package com.AccesoDatos.entity;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private long usuarioId;
	
	private String nombre;
	
	private String apellidos;
	
	@Column(unique = true)
	private String email;
	
	@Column(name="anyoNacimiento")
	private int anyoNacimiento;
	
	private String password;
	
	private String telefono;
	
	@OneToMany(mappedBy="usuario")
	private List<PersonajeGuardado> personajeGuardado;
	
	@OneToMany(mappedBy= "usuarioCompartio", fetch = FetchType.LAZY)
	private List<PersonajeCompartido> personajesCompartidos;
	
	
	//constructors
	public Usuario(long usuarioId, String nombre, String apellidos, String email, int anyoNacimiento, String password,
			String telefono, List<PersonajeCompartido> personajesCompartidos) {
		super();
		this.usuarioId = usuarioId;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.anyoNacimiento = anyoNacimiento;
		this.password = password;
		this.telefono = telefono;
		this.personajesCompartidos = personajesCompartidos;
	}

	public Usuario() {
		super();
	}
	
	//getters and setters
	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAnyoNacimiento() {
		return anyoNacimiento;
	}

	public void setAnyoNacimiento(int anyoNacimiento) {
		this.anyoNacimiento = anyoNacimiento;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<PersonajeCompartido> getPersonajesCompartidos() {
		return personajesCompartidos;
	}

	public void setPersonajesCompartidos(List<PersonajeCompartido> personajesGuardados) {
		this.personajesCompartidos = personajesGuardados;
	}


	@Override
	public String toString() {
	    return "Usuario [usuarioId=" + usuarioId + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email="
	            + email + ", anyoNacimiento=" + anyoNacimiento + ", password=" + password + ", telefono=" + telefono + "]";
	} 
}
