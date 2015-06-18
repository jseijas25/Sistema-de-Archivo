package dataBase;

import funciones._Con;
import modelos.Estudiante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstudianteDAO {

    private EstudianteDAO(){}

    public static boolean findAll(ArrayList<Estudiante> estudiantes) {
        try {
            PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement("select * from  estudiante");
            ResultSet rs = ps.executeQuery();
            Estudiante estudiante = new Estudiante();
            while (rs.next()) {
                estudiante.setCedula(rs.getInt("cedula"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setGenero(rs.getInt("genero"));
                estudiante.setSolvente(rs.getBoolean("solvente"));

                estudiantes.add(estudiante);
            }
            _Con.getInstance().closeConnectionDB();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
        Crea un expediente según un objeto Expediente.
     */
    public static boolean create (Estudiante estudiante){
        try {
            PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement
                    ("insert into estudiante (cedula, nombre, apellido, genero, solvente) values (?,?,?,?,?)");
            ps.setInt(1, estudiante.getCedula());
            ps.setString(2, estudiante.getNombre());
            ps.setString(3, estudiante.getApellido());
            ps.setInt(4, estudiante.getGenero());
            ps.setBoolean(5, estudiante.isSolvente());

            ps.execute();
            _Con.getInstance().closeConnectionDB();
            return true;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
        Lee un expediente, según el id del objeto Expediente que se pasa por parametro y lo termina de rellenar.
     */

    public static boolean read (Estudiante estudiante) {
        try {
            PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement("select * from  estudiante where cedula=?");
            ps.setInt(1, estudiante.getCedula());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setGenero(rs.getInt("genero"));
                estudiante.setSolvente(rs.getBoolean("solvente"));

                _Con.getInstance().closeConnectionDB();
                return true;
            }else{
                _Con.getInstance().closeConnectionDB();
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Error al conectarse a la base de datos");
        }
        return false;
    }

    public static boolean update (Estudiante estudiante) {
        try {
            PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement("update estudiante set nombre=?, apellido=?, genero=?, solvente=? where cedula=?");
            ps.setInt(5, estudiante.getCedula());
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setInt(3, estudiante.getGenero());
            ps.setBoolean(4, estudiante.isSolvente());

            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete (int cedula){
        try {
            PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement("delete from estudiante where cedula=?");
            ps.setInt(1, cedula);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
