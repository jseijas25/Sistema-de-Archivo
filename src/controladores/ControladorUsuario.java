package controladores;

import dataBase.UsuarioDAO;
import funciones.OperationType;
import funciones._Con;
import modelos.Usuario;
import vistas.VistaBuscarUsuario;
import vistas.VistaUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class ControladorUsuario extends BaseControlador {

    private Usuario usuario;
    private VistaUsuario vista;

    public ControladorUsuario(VistaUsuario vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource().equals(vista.getBtnCrear())){
            if(vista.getData(usuario)){
                if(UsuarioDAO.create(usuario)){
                    vista.setMessage("Usuario creado con exito");
                    vista.dispose();
                    _Con.getInstance().setOperation(OperationType.READ);
                    new VistaUsuario();
                } else {
                    vista.setError("Usuario no creado");
                }
            }
        }
        if(e.getSource().equals(vista.getBtnEditar())){
            if(vista.isEditable()){
                if(vista.getData(usuario)){
                    if(UsuarioDAO.update(usuario)){
                        vista.setMessage("Exito al actualizar al Usuario");
                    }else{
                        vista.setError("Usuario no actualizado");
                    }
                }
            }
            vista.setEditable();
        }
        if(e.getSource().equals(vista.getBtnBorrar())){
            int op = JOptionPane.showConfirmDialog(vista, "¿Está seguro que desea eliminar el Usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if(op == JOptionPane.YES_OPTION){
                if(UsuarioDAO.delete(usuario.getId())){
                    vista.setMessage("Usuario eliminado con exito");
                    vista.dispose();
                    new VistaBuscarUsuario();
                }else{
                    vista.setError("Usuario no eliminado");
                }
            }
        }
        if(e.getSource().equals(vista.getBtnSalir())){
            if(vista.isEditable()){
                if(_Con.getInstance().getOperation()==OperationType.READ){
                    int op = JOptionPane.showConfirmDialog(vista, "¿Está seguro que desea cancelar la edición?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if(op == JOptionPane.YES_OPTION){
                        new VistaUsuario();
                    }
                }else if(_Con.getInstance().getOperation() == OperationType.CREATE){
                    int op = JOptionPane.showConfirmDialog(vista, "¿Está seguro que desea cancelar la creación\ndel nuevo usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if(op == JOptionPane.YES_OPTION){
                        new VistaBuscarUsuario();
                    }
                }
            }
            vista.dispose();
        }
        vista.showError();
        vista.showMessage();
    }


    @Override
    public void windowOpened(WindowEvent e) {
        super.windowOpened(e);
        usuario = _Con.getInstance().getUsuario();
        vista.setData(usuario);
    }
}
