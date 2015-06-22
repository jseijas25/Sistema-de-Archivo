package vistas;

import com.sun.org.apache.bcel.internal.generic.NEW;
import controladores.ControladorBuscarUsuario;
import funciones._Con;
import modelos.Estudiante;
import modelos.Usuario;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaBuscarUsuario extends BaseVista {
    private JButton btnBuscar, btnSalir;
    private JTextField txtNombreUsuario;

    public VistaBuscarUsuario() {
        super();
        JLabel lblNombreUsuario;
        JPanel pnlPrincipal, pnlNombre, pnlbotones;
        ControladorBuscarUsuario controlador;
        controlador = new ControladorBuscarUsuario(this);
        Icon icobuscar, icosalir;
        icobuscar         = new ImageIcon(_Con.RUTA + "32x32/buscar.png");
        icosalir         = new ImageIcon(_Con.RUTA + "32x32/salir.png");

        txtNombreUsuario = new JTextField(10);
        lblNombreUsuario = new JLabel("Nombre de Usuario:");

        btnBuscar = new JButton("Buscar", icobuscar);
        btnSalir = new JButton("Salir", icosalir);

        pnlPrincipal = new JPanel(new BorderLayout());
        pnlNombre =new JPanel(new FlowLayout());
        pnlNombre.setBorder(new EmptyBorder(10,10,10,10));
        pnlNombre.add(lblNombreUsuario);
        pnlNombre.add(txtNombreUsuario);

        pnlbotones =new JPanel(new FlowLayout());
        pnlbotones.setBorder(new EmptyBorder(10,10,10,10));
        pnlbotones.add(btnBuscar);
        pnlbotones.add(btnSalir);

        pnlPrincipal.add(pnlNombre, BorderLayout.CENTER);
        pnlPrincipal.add(pnlbotones, BorderLayout.SOUTH);

        btnSalir.addActionListener(controlador);
        btnBuscar.addActionListener(controlador);

        this.add(pnlPrincipal);
        this.setResizable(false);
        this.setTitle("Buscar Usuario");
        this.setVisible(true);
        this.setSize(280,150);
        this.setLocationRelativeTo(null);
    }

    public boolean getData(Usuario usuario){
        System.out.println(txtNombreUsuario.getText().length());
        if (txtNombreUsuario.getText().isEmpty()){
            setError("El campo cedula no puede estar vacio");
            return false;
        }else
            usuario.setNombre(txtNombreUsuario.getText());

        return true;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }
}
