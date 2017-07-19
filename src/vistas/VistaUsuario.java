package vistas;

import controladores.ControladorUsuario;
import funciones.AccessLevel;
import funciones._Con;
import modelos.Usuario;
import javax.swing.*;
import java.awt.*;

public class VistaUsuario extends BaseVista {
    private JButton btnCrear, btnEditar, btnBorrar, btnSalir;
    private JTextField txtNombreUsuario;
    private JPasswordField pwdClave;
    private JComboBox cmbNivel;
    private JCheckBox active;
    private boolean editable;

    public VistaUsuario(){
        JLabel lblNombreUsuario, lblClave, lblNivel;
        JPanel pnlDatosUsuario, pnlLateral, pnlPrincipal;
        Icon icoCrear,icoBorrar,icoSalir,icoEditar;

        icoCrear         = new ImageIcon(_Con.RUTA_IMAGENES + "32x32/crear.png");
        icoBorrar=new ImageIcon(_Con.RUTA_IMAGENES + "32x32/borrar.png");
        icoSalir=new ImageIcon(_Con.RUTA_IMAGENES + "32x32/salir.png");
        icoEditar=new ImageIcon(_Con.RUTA_IMAGENES + "32x32/editar.png");

        ControladorUsuario controlador;
        controlador = new ControladorUsuario(this);
        editable = true;

        lblNombreUsuario = new JLabel("Nombre de Usuario: ");
        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setEnabled(false);

        lblClave = new JLabel("Clave: ");
        pwdClave = new JPasswordField();
        pwdClave.addActionListener(controlador);

        lblNivel = new JLabel("Nivel: ");
        cmbNivel=new JComboBox<>();

        cmbNivel.addItem(AccessLevel.USER);
        cmbNivel.addItem(AccessLevel.ADMIN);
        cmbNivel.addActionListener(controlador);

        active=new JCheckBox("Usuario Activo");

        pnlDatosUsuario = new JPanel(new GridLayout(4,2));
        pnlDatosUsuario.add(lblNombreUsuario);
        pnlDatosUsuario.add(txtNombreUsuario);
        pnlDatosUsuario.add(lblClave);
        pnlDatosUsuario.add(pwdClave);
        pnlDatosUsuario.add(lblNivel);
        pnlDatosUsuario.add(cmbNivel);
        pnlDatosUsuario.add(active);

        pnlLateral = new JPanel();
        switch (_Con.getInstance().getOperation()){
            case READ:
                pnlLateral.setLayout(new GridLayout(3,1));
                btnEditar = new JButton("Editar", icoEditar);
                btnEditar.addActionListener(controlador);
                btnBorrar = new JButton("Borrar", icoBorrar);
                btnBorrar.addActionListener(controlador);
                pnlLateral.add(btnEditar);
                pnlLateral.add(btnBorrar);
                setEditable();
                break;
            case CREATE:
                pnlLateral.setLayout(new GridLayout(2,1));
                btnCrear = new JButton("Crear", icoCrear);
                btnCrear.addActionListener(controlador);
                pnlLateral.add(btnCrear);
                break;
        }

        btnSalir = new JButton("Salir", icoSalir);
        btnSalir.addActionListener(controlador);
        pnlLateral.add(btnSalir);

        pnlPrincipal = new JPanel(new BorderLayout());
        pnlPrincipal.add(pnlDatosUsuario, BorderLayout.CENTER);
        pnlPrincipal.add(pnlLateral, BorderLayout.EAST);

        this.addWindowListener(controlador);
        this.add(pnlPrincipal);
        this.setVisible(true);
        this.pack();
        this.setTitle("Usuario");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
    }

    public boolean getData(Usuario usuario){
        if(pwdClave.getPassword().length == 0){
            setError("El campo \"Clave\" no puede estar vacio");
            return false;
        }
        usuario.setClave(String.valueOf(pwdClave.getPassword()));
        usuario.setNivel((AccessLevel) cmbNivel.getSelectedItem());
        usuario.setActive(active.isSelected());
        return true;
    }

    public void setData(Usuario usuario){
        txtNombreUsuario.setText(usuario.getNombre());
        pwdClave.setText(usuario.getClave());
        cmbNivel.setSelectedItem(usuario.getNivel());
        active.setSelected(usuario.isActive());

    }

    public void setEditable() {
        if(editable){
            editable = false;
            btnEditar.setText("Editar");
        }else{
            int op = JOptionPane.showConfirmDialog(this, "¿Está seguro de habilitar edición?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if(op == JOptionPane.YES_OPTION){
                editable = true;
                btnEditar.setText("Guardar");
            }
        }
        pwdClave.setEnabled(editable);
        active.setEnabled(editable);
        btnBorrar.setEnabled(!editable);
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnBorrar() {
        return btnBorrar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public boolean isEditable() {return editable;}
}
