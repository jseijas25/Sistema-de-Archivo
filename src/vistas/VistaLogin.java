package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controladores.ControladorLogin;
import funciones._Con;
import modelos.Usuario;

public class VistaLogin extends BaseVista {

	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JButton btnAceptar,btnCancelar;

	public VistaLogin(){
		super();
        ControladorLogin controlador;
        controlador = new ControladorLogin(this);

		Icon icoAceptar,icoCancelar;
		JLabel lblUsuario,lblClave;
		JPanel pnlPrincipal,pnlClave,pnlBotonera;

        icoAceptar=new ImageIcon(_Con.RUTA_IMAGENES +"32x32/aceptar.png");
		icoCancelar=new ImageIcon(_Con.RUTA_IMAGENES +"32x32/cancelar.png");

		lblUsuario= new JLabel("  Usuario:");
		lblClave= new JLabel("  Clave:");

        txtUsuario=new JTextField(10);
		txtClave=new JPasswordField(10);

		btnAceptar=new JButton("Aceptar", icoAceptar);
		btnCancelar=new JButton("Cancelar", icoCancelar);

		pnlPrincipal=new JPanel(new BorderLayout());
        pnlPrincipal.setBorder(new EmptyBorder(10,10,10,10));

        pnlClave=new JPanel(new GridLayout(2,2));
		pnlClave.setBorder(new EmptyBorder(10,10,10,10));
        pnlClave.add(lblUsuario);
		pnlClave.add(txtUsuario);
        pnlClave.add(lblClave);
		pnlClave.add(txtClave);

		pnlBotonera=new JPanel(new FlowLayout());
		pnlBotonera.add(btnAceptar);
		pnlBotonera.add(btnCancelar);

		pnlPrincipal.add(pnlBotonera, BorderLayout.SOUTH);
		pnlPrincipal.add(pnlClave, BorderLayout.CENTER);
		this.add(pnlPrincipal);

        btnAceptar.addActionListener(controlador);
		btnCancelar.addActionListener(controlador);
		txtUsuario.addKeyListener(controlador);
		txtClave.addKeyListener(controlador);

		this.setAlwaysOnTop(true);
        this.pack();
        this.setTitle("Ingresar");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JPasswordField getTxtClave() {
		return txtClave;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public boolean getData(Usuario usuario) {
		if (txtUsuario.getText().isEmpty()) {
			setError("El campo usuario no puede estar vacio");
			return false;
		}
		if (txtClave.getPassword().length == 0){
			setError("El campo contraseña no puede estar vacio");
			return false;
		}
		usuario.setNombre(txtUsuario.getText());
		usuario.setClave(String.valueOf(txtClave.getPassword()));
		return true;
	}

	public void vaciar(){
		txtUsuario.setText(null);
		txtClave.setText(null);
		txtUsuario.requestFocus();
	}
}