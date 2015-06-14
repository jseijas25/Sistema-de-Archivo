package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;


import dataBase.UsuarioDAO;
import modelos.Usuario;
import vistas.VistaLogin;
import vistas.VistaPrincipal;

public class ControladorVistaLogin implements ActionListener,KeyListener {

    private VistaLogin vista;
    private Usuario usuario;

    public ControladorVistaLogin(VistaLogin vistaLogin) {
        this.vista = vistaLogin;
        usuario = new Usuario();
    }


    public void actionPerformed(ActionEvent accion) {

        if (accion.getSource().equals(vista.getBtnAceptar())) {
            ingresar();
        }
        if (accion.getSource().equals(vista.getBtnCancelar())) {
            vista.dispose();
            JOptionPane.showMessageDialog(null, "Hasta luego");
            System.exit(0);
        }
        if (accion.getSource().equals(vista.getBtnVaciar()))
            this.vaciar();
    }

    public void keyPressed(KeyEvent event) {

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

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void ingresar() {
        vista.getData(usuario);
        if(vista.getError().isEmpty()){
            if(UsuarioDAO.read(usuario)){
                Usuario usuarioVista = new Usuario();
                vista.getData(usuarioVista);
                if(usuarioVista.getClave().equals(usuario.getClave())) {
                    vista.dispose();
                    JOptionPane.showMessageDialog(null, "Bienvenido al sistema...:" + usuario.getNombre());
                    new VistaPrincipal(usuario);
                }else{

                }
            }else {

            }
        }
        vista.showError();
    }

    public void vaciar() {
        vista.setTxtUsuario(null);
        vista.setTxtClave(null);
        vista.getTxtUsuario().requestFocus();
    }


}

