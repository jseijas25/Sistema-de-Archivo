package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelos.Usuario;

public class UsuarioDAO {

	private UsuarioDAO(){}

    public static boolean findAll(ArrayList<Usuario> usuarios) {
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement("select * from  estudiante");
            ResultSet rs = ps.executeQuery();
            Usuario usuario = new Usuario();
            while (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setClave(rs.getString("clave"));
                usuario.setNivel(rs.getInt("nivel"));
                usuario.setActive(rs.getBoolean("active"));

                usuarios.add(usuario);
            }
            DBConnection.getInstance().closeConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	public static boolean create (Usuario usuario){
		try {
			PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement
					("insert into usuario (id, nombre, clave, nivel, active) values (?,?,?,?,?)");
			ps.setInt(1, usuario.getId());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getClave());
			ps.setInt(4, usuario.getNivel());
            ps.setBoolean(5, usuario.isActive());

			ps.execute();
			DBConnection.getInstance().closeConnection();
			return true;
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
			return false;
		}
	}

    /*
        Lee un Usuario según el nombre de usuario.
     */

	public static boolean read (Usuario usuario) {
		try {
			PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement("select * from  usuario where nombre=?");
			ps.setString(1, usuario.getNombre());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				usuario.setId(rs.getInt("id"));
				usuario.setClave(rs.getString("clave"));
                usuario.setNivel(rs.getInt("nivel"));
                usuario.setActive(rs.getBoolean("active"));
				DBConnection.getInstance().closeConnection();
				return true;
			}else{
				DBConnection.getInstance().closeConnection();
				return false;
			}
		} catch (SQLException | NullPointerException e){
            e.printStackTrace();
			System.out.println("Error al conectarse a la base de datos");
		}
		return false;
	}

	public static boolean update (Usuario usuario) {
		try {
			PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement("update usuario set nombre=?, clave=?, nivel=?,active=? where id=?");
			ps.setInt(5, usuario.getId());
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getClave());
			ps.setInt(3, usuario.getNivel());
            ps.setBoolean(4, usuario.isActive());
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean delete (int id){
		try {
			PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement("delete from usuario where id=?");
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}



}
