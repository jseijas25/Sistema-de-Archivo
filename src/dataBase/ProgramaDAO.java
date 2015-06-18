package dataBase;

import funciones._Con;
import modelos.Programa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramaDAO {

    private ProgramaDAO(){}

    public static boolean findAll(ArrayList<Programa> programas) {
        try {
            PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement("select * from  programa");
            ResultSet rs = ps.executeQuery();
            Programa programa = new Programa();
            while (rs.next()) {
                programa.setId(rs.getInt("id"));
                programa.setName(rs.getString("name"));
                programa.setActive(rs.getBoolean("active"));
                programa.setRecordCount(rs.getInt("recordCount"));

                programas.add(programa);
            }
            _Con.getInstance().closeConnectionDB();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean create (Programa programa){
        try {
            PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement
                    ("insert into programa (id, name, active, recordCount) values (?,?,?,?)");
            ps.setInt(1, programa.getId());
            ps.setString(2, programa.getName());
            ps.setBoolean(3, programa.isActive());
            ps.setInt(4, programa.getRecordCount());

            ps.execute();
            _Con.getInstance().closeConnectionDB();
            return true;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
        Lee un programa según su Nombre
     */

    public static boolean read (Programa programa) {
        try {
            PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement("select * from  programa where name=?");
            ps.setString(1, programa.getName());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                programa.setId(rs.getInt("id"));
                programa.setActive(rs.getBoolean("active"));
                programa.setRecordCount(rs.getInt("recordCount"));
                _Con.getInstance().closeConnectionDB();
                return true;
            }else{
                _Con.getInstance().closeConnectionDB();
            }
        } catch (SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update (Programa programa) {
        try {
            PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement("update programa set name=?,active=?,recordCount=? where id=?");
            ps.setInt(4, programa.getId());
            ps.setString(1, programa.getName());
            ps.setBoolean(4, programa.isActive());
            ps.setInt(3, programa.getRecordCount());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete (int id){
        try {
            PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement("delete from programa where id=?");
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
