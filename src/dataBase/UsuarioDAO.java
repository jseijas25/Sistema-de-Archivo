package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import funciones.AccessLevel;
import funciones._Con;
import modelos.Usuario;

public class UsuarioDAO {

	private UsuarioDAO(){}

    public static boolean findAll(ArrayList<Usuario> usuarios) {
        try {
            PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement("select * from  usuario");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setClave(rs.getString("clave"));
                usuario.setNivel(AccessLevel.valueOf(rs.getString("nivel")));
                usuario.setActive(rs.getBoolean("active"));

                usuarios.add(usuario);
            }
            _Con.getInstance().closeConnectionDB();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	public static boolean create (Usuario usuario){
		try {
			PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement
					("insert into usuario (id, nombre, clave, nivel, active) values (?,?,?,?,?)");
			ps.setInt(1, usuario.getId());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getClave());
			ps.setString(4, usuario.getNivel().toString());
            ps.setBoolean(5, usuario.isActive());

			ps.execute();
			_Con.getInstance().closeConnectionDB();
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
			PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement("select * from  usuario where nombre=?");
			ps.setString(1, usuario.getNombre());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				usuario.setId(rs.getInt("id"));
				usuario.setClave(rs.getString("clave"));
                usuario.setNivel(AccessLevel.valueOf(rs.getString("nivel")));
                usuario.setActive(rs.getBoolean("active"));
				_Con.getInstance().closeConnectionDB();
				return true;
			}else{
				_Con.getInstance().closeConnectionDB();
				return false;
			}
		} catch (SQLException | NullPointerException e){
            e.printStackTrace();
		}
		return false;
	}

	public static boolean update (Usuario usuario) {
		try {
			PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement("update usuario set nombre=?, clave=?, nivel=?,active=? where id=?");
			ps.setInt(5, usuario.getId());
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getClave());
			ps.setString(3, usuario.getNivel().toString());
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
			PreparedStatement ps = _Con.getInstance().getConnectionDB().prepareStatement("delete from usuario where id=?");
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean delete (Usuario usuario) {
		return read(usuario) && delete(usuario.getId());
	}
}
