package vistas;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;

import funciones.AccessLevel;
import funciones._Con;
import modelos.*;

import controladores.ControladorPrincipal;

public class VistaPrincipal extends JFrame {

	private JMenuItem itmUsuario, itmExpedientes,itmProgramas, itmReportes, itmCerrar;
	private JButton btnUsuario, btnPrograma, btnExpediente, btnSalir;

    public VistaPrincipal() {
        JLabel lblAutor, lblLogo, lblFondo;
        JPanel pnlBotonera, pnlTitulo, pnlAutor;
        Icon icoUsuarioP, icoReporteP, icoSalirP;
        Icon icoUsuarioG, icoSalirG, icoExpedienteG, icoPrograma;
        ControladorPrincipal controladorPrincipal;
        JMenuBar barMenu;
        JMenu menRegistro, menReportes, menOtros;

        icoUsuarioP         = new ImageIcon(_Con.RUTA + "16x16/.png");
        icoReporteP         = new ImageIcon(_Con.RUTA + "16x16/.png");
        icoSalirP           = new ImageIcon(_Con.RUTA + "16x16/.png");

        icoUsuarioG         = new ImageIcon(_Con.RUTA + "32x32/usuario.png");
        icoSalirG           = new ImageIcon(_Con.RUTA + "32x32/salir.png");
        icoExpedienteG      = new ImageIcon(_Con.RUTA + "32x32/expediente.png");
        icoPrograma         = new ImageIcon(_Con.RUTA + "32x32/programa.png");

        btnUsuario          = new JButton("USUARIOS", icoUsuarioG);
        btnSalir            = new JButton("SALIR", icoSalirG);
        btnExpediente       = new JButton("ESTUDIANTES", icoExpedienteG);
        btnPrograma          = new JButton("PROGRAMAS", icoPrograma);

        lblLogo                = new JLabel();
        lblLogo.setIcon(new ImageIcon(_Con.RUTA + "logo/banner.png"));

        lblFondo               = new JLabel();
        lblFondo.setIcon(new ImageIcon(_Con.RUTA + "logo/centro.png"));


        barMenu             = new JMenuBar();
        menRegistro         = new JMenu("REGISTRO");
        menRegistro.setMnemonic('R');
        itmUsuario          = new JMenuItem("Usuarios", icoUsuarioP);
        itmUsuario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
        itmExpedientes      = new JMenuItem("Estudiantes");
        itmExpedientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        itmProgramas      = new JMenuItem("Programas");
        itmProgramas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));

        menReportes         = new JMenu("REPORTES");
        menReportes.setMnemonic('P');
        itmReportes         = new JMenuItem("Usuarios", icoReporteP);
        itmReportes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

        menOtros            = new JMenu("OTROS");
        menOtros.setMnemonic('O');
        itmCerrar           = new JMenuItem("Salir", icoSalirP);
        itmCerrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));

        menOtros.add(itmCerrar);
        menReportes.add(itmReportes);
        barMenu.add(menRegistro);
        barMenu.add(menReportes);
        barMenu.add(menOtros);

        lblAutor = new JLabel("Sistema desarrollado por: Jose Luis Seijas - 2015");

        pnlBotonera = new JPanel();
        pnlBotonera.setLayout(new BoxLayout(pnlBotonera, BoxLayout.Y_AXIS));
        pnlBotonera.setBackground(Color.gray);

        pnlTitulo = new JPanel(new BorderLayout());
        pnlTitulo.add(lblLogo,BorderLayout.CENTER);
        pnlTitulo.add(barMenu, BorderLayout.NORTH);

        pnlAutor = new JPanel(new FlowLayout());
        pnlAutor.add(lblAutor);

        if (_Con.getInstance().getUsuario().getNivel().equals(AccessLevel.ADMIN)){
            pnlBotonera.add(btnUsuario);
            menRegistro.add(itmUsuario);
        }
        pnlBotonera.add(btnExpediente);
        pnlBotonera.add(btnPrograma);
        pnlBotonera.add(btnSalir);
        menRegistro.add(itmProgramas);
        menRegistro.add(itmExpedientes);


        this.add(pnlTitulo, BorderLayout.NORTH);
        this.add(lblFondo, BorderLayout.CENTER);
        this.add(pnlAutor, BorderLayout.SOUTH);
        this.add(pnlBotonera, BorderLayout.WEST);

        controladorPrincipal = new ControladorPrincipal(this);
        btnSalir.addActionListener(controladorPrincipal);
        btnExpediente.addActionListener(controladorPrincipal);
        btnUsuario.addActionListener(controladorPrincipal);
        btnPrograma.addActionListener(controladorPrincipal);
        itmCerrar.addActionListener(controladorPrincipal);
        itmUsuario.addActionListener(controladorPrincipal);
        itmReportes.addActionListener(controladorPrincipal);
        itmExpedientes.addActionListener(controladorPrincipal);

        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        lblFondo.setHorizontalAlignment(JLabel.CENTER);
        lblFondo.setVerticalAlignment(JLabel.CENTER);
        this.add(lblFondo);
        this.getContentPane().setBackground(Color.WHITE);
        this.setIconImage(new ImageIcon(_Con.RUTA + "logo/logo.png").getImage());

    }

    public JMenuItem getItmUsuario() {
        return itmUsuario;
    }

    public JMenuItem getItmExpedientes() {
        return itmExpedientes;
    }

    public JMenuItem getItmProgramas() {
        return itmProgramas;
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

    public JButton getBtnPrograma() {
        return btnPrograma;
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
