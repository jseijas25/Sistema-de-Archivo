package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;


import dataBase.UsuarioDAO;
import funciones._Con;
import modelos.Usuario;
import vistas.VistaLogin;
import vistas.VistaPrincipal;

public class ControladorLogin extends BaseControlador {

    private VistaLogin vista;
    private Usuario usuario;

    public ControladorLogin(VistaLogin vistaLogin) {
        this.vista = vistaLogin;
        usuario = new Usuario();
    }


    public void ingresar() {
        vista.getData(usuario);
        if(vista.getError().isEmpty()){
            if(UsuarioDAO.read(usuario)){
                Usuario usuarioVista = new Usuario();
                vista.getData(usuarioVista);
                if(usuarioVista.getClave().equals(usuario.getClave())) {
                    vista.dispose();
                    JOptionPane.showMessageDialog(null, "Bienvenido al sistema: " + usuario.getNombre());
                    _Con.getInstance().setUsuario(usuario);
                    new VistaPrincipal();
                }else{
                    vista.setError("Contraseña incorrecta");
                }
            }else {
                vista.setError("Usuario no encontrado");
            }
        }
        vista.showError();
    }

    public void cancelar() {
        vista.dispose();
        vista.setMessage("Hasta luego");
        vista.showMessage();
        System.exit(0);
    }

    public void vaciar() {
        vista.setTxtUsuario(null);
        vista.setTxtClave(null);
        vista.getTxtUsuario().requestFocus();
    }

    public void actionPerformed(ActionEvent accion) {
        if (accion.getSource().equals(vista.getBtnAceptar())) {
            ingresar();
        }
        if (accion.getSource().equals(vista.getBtnCancelar())) {
            cancelar();
        }
        if (accion.getSource().equals(vista.getBtnVaciar()))
            vaciar();
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            if (event.getSource().equals(vista.getTxtUsuario())) {
                vista.getTxtClave().requestFocus();
            }
            if (event.getSource().equals(vista.getTxtClave())) {
                ingresar();
            }
        }
    }

}

