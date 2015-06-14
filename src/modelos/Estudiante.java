package modelos;

public class Estudiante {
    private int cedula;
    private String nombre;
    private String apellido;
    private int genero;
    private boolean solvente;

    public Estudiante() {
    }

    public Estudiante(int cedula, String nombre, String apellido, int genero, boolean solvente) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.solvente = solvente;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public boolean isSolvente() {
        return solvente;
    }

    public void setSolvente(boolean solvente) {
        this.solvente = solvente;
    }
}
