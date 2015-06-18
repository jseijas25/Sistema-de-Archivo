package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;

import funciones.AccessLevel;
import funciones._Con;
import modelos.*;

import controladores.ControladorPrincipal;

public class VistaPrincipal extends JFrame {

	private JMenuItem itmUsuario, itmExpedientes, itmReportes, itmCerrar;
	private JButton btnUsuario, btnExpediente, btnSalir;

    public VistaPrincipal() {
        JLabel lblAutor, logo, fondo;
        JPanel pnlBotonera, pnlAuxiliar, pnlTitulo, pnlDesarrollo, pnlPrincipal;
        Icon icoUsuarioP, imgReporteP, imgSalirP;
        Icon imgUsuarioG, imgSalirG, imgExpedienteG, imgBanner, imgTipo;
        ControladorPrincipal controladorPrincipal;
        JMenuBar barMenu;
        JMenu menRegistro, menReportes, menOtros;

        icoUsuarioP         = new ImageIcon(_Con.RUTA + "16x16/usuario.png");
        imgReporteP         = new ImageIcon(_Con.RUTA + "16x16/pdf.png");
        imgSalirP           = new ImageIcon(_Con.RUTA + "16x16/salir.png");

        imgUsuarioG         = new ImageIcon(_Con.RUTA + "32x32/usuario.png");
        imgSalirG           = new ImageIcon(_Con.RUTA + "32x32/salir.png");
        imgExpedienteG      = new ImageIcon(_Con.RUTA + "32x32/expe.png");
        imgBanner           = new ImageIcon(_Con.RUTA + "logo/Imagen1.png");

        btnUsuario          = new JButton("USUARIOS", imgUsuarioG);
        btnSalir            = new JButton("SALIR", imgSalirG);
        btnExpediente       = new JButton("EXPEDIENTES", imgExpedienteG);

        logo                = new JLabel();
        logo.setIcon(new ImageIcon(_Con.RUTA + "logo/banner.png"));

        fondo               = new JLabel();
        fondo.setIcon(new ImageIcon(_Con.RUTA + "logo/centro.png"));


        barMenu             = new JMenuBar();

        menRegistro         = new JMenu("REGISTRO");
        menRegistro.setMnemonic('R');
        itmUsuario          = new JMenuItem("Usuarios", icoUsuarioP);
        itmUsuario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
        itmExpedientes      = new JMenuItem("Expedientes");
        itmExpedientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));

        menReportes         = new JMenu("REPORTES");
        menReportes.setMnemonic('P');
        itmReportes         = new JMenuItem("Usuarios", imgReporteP);
        itmReportes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

        menOtros            = new JMenu("OTROS");
        menOtros.setMnemonic('O');
        itmCerrar           = new JMenuItem("Salir", imgSalirP);
        itmCerrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));

        menOtros.add(itmCerrar);
        menReportes.add(itmReportes);
        barMenu.add(menRegistro);
        barMenu.add(menReportes);
        barMenu.add(menOtros);


        //DEFINIR ETIQUETAS Y CAMPOS

        lblAutor = new JLabel("Sistema desarrollado por: Jose Luis Seijas - 2015");


        //PANELES

        pnlPrincipal = new JPanel(new BorderLayout());
        pnlPrincipal.setBorder(new LineBorder(Color.BLACK, 3, true));

        pnlBotonera = new JPanel();
        pnlBotonera.setLayout(new BoxLayout(pnlBotonera, BoxLayout.Y_AXIS));
        pnlBotonera.setBackground(Color.gray);

        //panel superior

        pnlTitulo = new JPanel(new BorderLayout());

        pnlAuxiliar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlAuxiliar.add(logo);
        pnlPrincipal.add(pnlAuxiliar);                                  //¿Añades el panel auxiliar a dos paneles distintos?
        pnlTitulo.add(pnlAuxiliar, BorderLayout.WEST);


        //Panel barra de menu
        pnlAuxiliar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlAuxiliar.add(barMenu);
        pnlTitulo.add(pnlAuxiliar, BorderLayout.SOUTH);

        //panel inferior

        pnlDesarrollo = new JPanel(new FlowLayout());

        pnlAuxiliar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlPrincipal.setBorder(new LineBorder(Color.BLACK, 2, true));
        pnlAuxiliar.add(lblAutor);
        pnlDesarrollo.add(pnlAuxiliar, BorderLayout.WEST);

        // -----------------niveles de seguridad-----------------------

        if (_Con.getInstance().getUsuario().getNivel().equals(AccessLevel.ADMIN)){
            pnlBotonera.add(btnUsuario);
            menRegistro.add(itmUsuario);
        }
        pnlBotonera.add(btnExpediente);
        pnlBotonera.add(btnSalir);
        menRegistro.add(itmExpedientes);

        //-----------------------ENLACES CON CONTROLADOR<---------------------------

        controladorPrincipal = new ControladorPrincipal(this);
        btnSalir.addActionListener(controladorPrincipal);
        btnExpediente.addActionListener(controladorPrincipal);
        btnUsuario.addActionListener(controladorPrincipal);
        itmCerrar.addActionListener(controladorPrincipal);
        itmUsuario.addActionListener(controladorPrincipal);
        itmReportes.addActionListener(controladorPrincipal);
        itmExpedientes.addActionListener(controladorPrincipal);

        this.setJMenuBar(barMenu);
        this.add(fondo, BorderLayout.CENTER);
        this.add(pnlTitulo, BorderLayout.NORTH);
        this.add(pnlDesarrollo, BorderLayout.SOUTH);
        this.add(pnlBotonera, BorderLayout.WEST);
        this.setUndecorated(true);
        this.pack();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);

        fondo.setHorizontalAlignment(JLabel.CENTER);
        fondo.setVerticalAlignment(JLabel.CENTER);
        this.add(fondo);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Archivo General Control de Estudio - Area de Postgrado");
        this.setIconImage(new ImageIcon(_Con.RUTA + "logo/logo.png").getImage());
    }

    public JMenuItem getItmUsuario() {
        return itmUsuario;
    }

    public JMenuItem getItmExpedientes() {
        return itmExpedientes;
    }

    public JMenuItem getItmReportes() {
        return itmReportes;
    }

    public JMenuItem getItmCerrar() {
        return itmCerrar;
    }

    public JButton getBtnUsuario() {
        return btnUsuario;
    }

    public JButton getBtnExpediente() {
        return btnExpediente;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public void salir() {
        int fuera = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea salir?", "Salir del Sistema", JOptionPane.YES_NO_OPTION);
        if (fuera == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(this, "Hasta Luego", "Salir del Sistema", 1);
            System.exit(0);// finaliza un programa
        }
    }
}
