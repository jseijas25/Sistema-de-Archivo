package funciones;

import modelos.Estudiante;
import modelos.Expediente;
import modelos.Programa;
import modelos.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class _Con {

    public static String RUTA = "../Sistema/imagenesSistema/";

    private static _Con instance = null;

    public static _Con getInstance(){
        if(instance==null){
            synchronized (_Con.class) {
                if (instance == null) {
                    instance = new _Con();
                }
            }
            instance = new _Con();
        }
        return instance;
    }

    private Usuario usuario;
    private Estudiante estudiante;
    private Programa programa;
    private ArrayList<Expediente> expedientes;
    private OperationType operation;

    private Connection connection;

    private _Con() {}

    public Connection getConnectionDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
            System.out.println("Connection done");
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            connection = null;
        }
        return connection;
    }

    public void closeConnectionDB(){
        try {
            connection.close();
            System.out.println("Connection close");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public ArrayList<Expediente> getExpedientes() {
        return expedientes;
    }

    public void setExpedientes(ArrayList<Expediente> expedientes) {
        this.expedientes = expedientes;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }
}
