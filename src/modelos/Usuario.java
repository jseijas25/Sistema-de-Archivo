package modelos;


import funciones.AccessLevel;

public class Usuario {
	private int id;
	private String nombre;
    private String clave;
    private AccessLevel nivel;
    private boolean active;

	public Usuario() {
	}

    public Usuario(int id, String nombre, String clave, AccessLevel nivel, boolean active) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.nivel = nivel;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public AccessLevel getNivel() {
        return nivel;
    }

    public void setNivel(AccessLevel nivel) {
        this.nivel = nivel;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
	
	
	
	
	
	
	
