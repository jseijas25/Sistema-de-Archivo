package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelos.Expediente;

public class ExpedienteDAO {

    //Previene la creación de objetos de este tipo, para que se force el uso de las funciones estaticas.
    private ExpedienteDAO(){}

    /*
    Busca todos los expedientes que contengan una cedula y los almacena en la lista
    que se pasa como parametro
     */

    public static boolean findAllWithID(ArrayList<Expediente> expedientes, int ci_estudiante) {
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement("select * from  expediente");
            ResultSet rs = ps.executeQuery();
            Expediente expediente = new Expediente();
            while (rs.next()) {
                if(rs.getInt("id_estudiante") == ci_estudiante){
                    expediente.setId(rs.getInt("id"));
                    expediente.setId_Estudiante(rs.getInt("id_estudiante"));
                    expediente.setId_Programa(rs.getInt("id_programa"));
                    expediente.setNumber(rs.getInt("number"));
                    expediente.setPicturesChecked(rs.getBoolean("picture"));
                    expediente.setCvChecked(rs.getBoolean("cv"));
                    expediente.setNegativePhotocopyChecked(rs.getBoolean("negativePhoto"));
                    expediente.setGradesChecked(rs.getBoolean("grades"));
                    expediente.setBirthCertificateChecked(rs.getBoolean("birthCertificate"));
                    expediente.setIdPhotocopyChecked(rs.getBoolean("idPhotocopy"));

                    expedientes.add(expediente);
                }
            }
            DBConnection.getInstance().closeConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    Busca todos los expedientes y los almacena en la lista que se pasa como parametro.
    */

    public static boolean findAll(ArrayList<Expediente> expedientes) {
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement("select * from  expediente");
            ResultSet rs = ps.executeQuery();
            Expediente expediente = new Expediente();
            while (rs.next()) {
                expediente.setId(rs.getInt("id"));
                expediente.setId_Estudiante(rs.getInt("id_estudiante"));
                expediente.setId_Programa(rs.getInt("id_programa"));
                expediente.setNumber(rs.getInt("number"));
                expediente.setPicturesChecked(rs.getBoolean("pictures"));
                expediente.setCvChecked(rs.getBoolean("cv"));
                expediente.setNegativePhotocopyChecked(rs.getBoolean("negativePhoto"));
                expediente.setGradesChecked(rs.getBoolean("grades"));
                expediente.setBirthCertificateChecked(rs.getBoolean("birthCertificate"));
                expediente.setIdPhotocopyChecked(rs.getBoolean("idPhotocopy"));

                expedientes.add(expediente);
            }
            DBConnection.getInstance().closeConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
        Crea un expediente según un objeto Expediente.
     */
    public static boolean create (Expediente expediente){
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement
                    ("insert into expediente (id, id_estudiante, id_programa, number, pictures, cv, negativePhotocopy, grades,birthCertificate, idPhotocopy) values (?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, expediente.getId());
            ps.setInt(2, expediente.getId_Estudiante());
            ps.setInt(3, expediente.getId_Programa());
            ps.setInt(4, expediente.getNumber());
            ps.setBoolean(5, expediente.isPicturesChecked());
            ps.setBoolean(6, expediente.isCvChecked());
            ps.setBoolean(7, expediente.isNegativePhotocopyChecked());
            ps.setBoolean(8, expediente.isGradesChecked());
            ps.setBoolean(9, expediente.isBirthCertificateChecked());
            ps.setBoolean(10, expediente.isIdPhotocopyChecked());

            ps.execute();
            DBConnection.getInstance().closeConnection();
            return true;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
        Lee un expediente, según el id del objeto Expediente que se pasa por parametro y lo termina de rellenar.
     */

    public static boolean read (Expediente expediente) {
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement("select * from  expediente where id=?");
            ps.setInt(1, expediente.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                expediente.setId_Estudiante(rs.getInt("id_estudiante"));
                expediente.setId_Programa(rs.getInt("id_programa"));
                expediente.setNumber(rs.getInt("number"));
                expediente.setPicturesChecked(rs.getBoolean("pictures"));
                expediente.setCvChecked(rs.getBoolean("cv"));
                expediente.setNegativePhotocopyChecked(rs.getBoolean("negativePhoto"));
                expediente.setGradesChecked(rs.getBoolean("grades"));
                expediente.setBirthCertificateChecked(rs.getBoolean("birthCertificate"));
                expediente.setIdPhotocopyChecked(rs.getBoolean("idPhotocopy"));
                DBConnection.getInstance().closeConnection();
                return true;
            }else{
                DBConnection.getInstance().closeConnection();
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error al conectarse a la base de datos");
        }
        return false;
    }

    public static boolean update (Expediente expediente) {
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement("update expediente set id_estudiante=?, id_programa=?, number=?, pictures=?, cv=?, negativePhotocopy=?, grades=?, birthCertificate=?, idPhotocopy=? where id=?");
            ps.setInt(10, expediente.getId());
            ps.setInt(1, expediente.getId_Estudiante());
            ps.setInt(2, expediente.getId_Programa());
            ps.setInt(3, expediente.getNumber());
            ps.setBoolean(4, expediente.isPicturesChecked());
            ps.setBoolean(5, expediente.isCvChecked());
            ps.setBoolean(6, expediente.isNegativePhotocopyChecked());
            ps.setBoolean(7, expediente.isGradesChecked());
            ps.setBoolean(8, expediente.isBirthCertificateChecked());
            ps.setBoolean(9, expediente.isIdPhotocopyChecked());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete (int id){
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement("delete from expediente where id=?");
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}