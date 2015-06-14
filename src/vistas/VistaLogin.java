package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controladores.ControladorVistaLogin;
import modelos.Usuario;

public class VistaLogin extends JDialog{
	private Icon imgLogin,imgUsuario,imgAceptar,imgCancelar,imgVaciar,imgSecurity2,imgclave;
	private String ruta="../Sistema/imagenesSistema/";
	private JLabel lblUsuario,lblImgUsuario,lblClave,lblImgClave,lblImagenSecurity,lblSecurity2;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
    private String error;
	
	
	private JPanel paneltitulo,panelPrincipal,panelAuxiliar,PanelClave,panelSecurity,panelBotonera;
	
	
	private JButton btnAceptar,btnCancelar,btnVaciar;
	
	public VistaLogin(){	
		
		error = "";

		//imagenes
		imgUsuario=new ImageIcon(ruta+"32x32/usuario.png");	
		imgLogin=new ImageIcon(ruta+"logo/imagen.png");
		imgclave=new ImageIcon(ruta+"32x32/clave.png");
		imgAceptar=new ImageIcon(ruta+"32x32/button_ok.png");
		imgCancelar=new ImageIcon(ruta+"32x32/Copia de Close.png");
			
		//icono principal  de la aplicacion
		this.setIconImage (new ImageIcon(ruta+"logo/logop.png").getImage());

		imgSecurity2=new ImageIcon();
		lblImagenSecurity=new JLabel(imgLogin);

	
		lblImgUsuario=new JLabel(imgUsuario);
		lblImgClave=new JLabel(imgclave);
		
		lblUsuario= new JLabel("Usuario: ",JLabel.RIGHT);
		lblClave= new JLabel("Clave: ",JLabel.RIGHT);
		txtUsuario=new JTextField(10);
		txtClave=new JPasswordField(10);
		
		
	
		//botones
		btnAceptar=new JButton("Aceptar",imgAceptar);
		btnCancelar=new JButton("Cancelar",imgCancelar);
		


		JLabel logo;
		logo=new JLabel();
		logo.setIcon(new ImageIcon(ruta+"logo/nombre.png"));

        //paneles
		panelPrincipal=new JPanel(new BorderLayout());
		panelPrincipal.setBorder((Border) new LineBorder(Color.BLACK,2,true));
		this.setLocation(500,200);

		paneltitulo=new JPanel(new GridLayout(1,1));
		paneltitulo.setBackground(Color.WHITE);
		
		panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelAuxiliar.add(logo);
		
		
		//panelAuxiliar.setBackground(Color.getHSBColor(65, 536, 56));
		paneltitulo.add(panelAuxiliar);
		
		
		PanelClave=new JPanel(new GridLayout(2,1));
		PanelClave.setBorder(new TitledBorder("INGRESAR AL SISTEMA"));
		PanelClave.setBackground(Color.WHITE);
		
		
		panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelAuxiliar.add(lblImgUsuario);
		panelAuxiliar.add(lblUsuario);
		panelAuxiliar.add(txtUsuario);
		panelAuxiliar.setBackground(Color.white);
		PanelClave.add(panelAuxiliar);
		
		panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelAuxiliar.add(lblImgClave);
		panelAuxiliar.add(lblClave);
		panelAuxiliar.add(txtClave);
		panelAuxiliar.setBackground(Color.white);
		PanelClave.add(panelAuxiliar);
		
		//LOGO SECURITY

		panelSecurity=new JPanel(new FlowLayout());
		panelSecurity.setBackground(Color.white);
		panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelAuxiliar.add(lblImagenSecurity);
		panelAuxiliar.setBackground(Color.white);
		panelSecurity.add(panelAuxiliar);
		
		//panel Botonera
		panelBotonera=new JPanel(new FlowLayout());
		panelBotonera.setBackground(Color.white);
		panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelAuxiliar.add(btnAceptar);
		panelAuxiliar.add(btnCancelar);
		panelAuxiliar.setBackground(Color.WHITE);
		panelBotonera.add(panelAuxiliar);
		
	//	panelPrincipal.add(paneltitulo,BorderLayout.NORTH);
		panelPrincipal.add(panelBotonera,BorderLayout.SOUTH);
		panelPrincipal.add(PanelClave,BorderLayout.CENTER);
	    panelPrincipal.add(panelSecurity,BorderLayout.WEST);
	    this.add(panelPrincipal);
	    this.setUndecorated(true);
		
		//enlazar
		
		ControladorVistaLogin eco=new ControladorVistaLogin(this);
		
		btnAceptar.addActionListener(eco);
		btnCancelar.addActionListener(eco);
		txtUsuario.addKeyListener(eco);
		txtClave.addKeyListener(eco);
		
		this.setAlwaysOnTop(true);
		this.pack();
		this.setVisible(true);		
}

//------------------->getters and setters<--------------------------

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}
	
	public JLabel getlblSecurity(){
		return lblImagenSecurity;
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

	public JButton getBtnVaciar() {
		return btnVaciar;
	}

	public void setTxtUsuario(String txtUsuario) {
		this.txtUsuario.setText(txtUsuario);
	}

	public void setTxtClave(String txtClave) {
		this.txtClave.setText(txtClave);
	}

    public String getError(){
        return error;
    }

    public void setError (String error){
        this.error = error;
    }

    public void getData(Usuario usuario) {
        if (txtUsuario.getText().isEmpty()) {
            error = "El campo cedula no puede estar vacio";
            return;
        }
        if (txtClave.getPassword().length == 0){
            error = "El campo contraseña no puede estar vacio";
            return;
        }

        usuario.setNombre(txtUsuario.getText());
        usuario.setClave(String.valueOf(txtClave.getPassword()));
    }

    public void showError(){
        if(!error.isEmpty()){
            JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
        }
        error = "";
    }

}