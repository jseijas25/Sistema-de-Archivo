package controladores;

import dataBase.UsuarioDAO;
import funciones.OperationType;
import funciones._Con;
import modelos.Usuario;
import vistas.VistaBuscarUsuario;
import vistas.VistaUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ControladorBuscarUsuario extends BaseControlador {
    private Usuario usuario;
    private VistaBuscarUsuario vista;

    public ControladorBuscarUsuario(VistaBuscarUsuario vista) {
        this.vista = vista;
        this.usuario = new Usuario();
    }

    private void buscarUsuario() {
        if(vista.getData(usuario)) {
            _Con.getInstance().setUsuario(usuario);
            if (UsuarioDAO.read(usuario)) {
                _Con.getInstance().setOperation(OperationType.READ);
                new VistaUsuario();
                vista.dispose();
            } else {
                int response = JOptionPane.showConfirmDialog(null, "Usuario no encontrado\n¿Desea crearlo?", "No encontrado",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                switch (response){
                    case JOptionPane.YES_OPTION:
                        _Con.getInstance().setOperation(OperationType.CREATE);
                        new VistaUsuario();
                        vista.dispose();
                        break;
                    default:
                        break;
                }
            }
        } else {
            vista.showError();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource().equals(vista.getBtnBuscar())){
            buscarUsuario();
        }
        if(e.getSource().equals(vista.getBtnSalir())){
            vista.dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            //TODO
        }
    }
}
