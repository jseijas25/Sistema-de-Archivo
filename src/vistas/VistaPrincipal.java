package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import modelos.*;

import controladores.ControladorVistaPrincipal;

public class VistaPrincipal extends JFrame{

	JLabel fondo,logo, logo2,fondo3,desarrollo;
	String ruta="../Sistema/imagenesSistema/";
	private Usuario xUsuario;
	private Expediente xExpediente;
	JMenuBar barraMenu;
	JMenu registro,reportes,otros;
	JMenuItem usuarios,expedientes,rep_usuarios,cerrar;
	private JPanel panelPrincipal,panelBotonera,panelAuxiliar,panelTitulo,panelDesarrollo;
	Icon imgLogin,imgUsP,imgRepP,imgSalP;
	Icon imgUsuarioG,imgSal,imgexpe,imgBanner,imgTipo; 
	JButton btnUs,btnExpediente,btnSal;
	JToolBar barraHerramientas;

	public VistaPrincipal(Usuario operador){
		
		imgUsP=new ImageIcon(ruta+"16x16/usuario.png");
		imgRepP=new ImageIcon(ruta+"16x16/pdf.png");
		imgSalP=new ImageIcon(ruta+"16x16/salir.png");
		// Iconos Grandes
		
		
		imgUsuarioG=new ImageIcon(ruta+"32x32/usuario.png");
		imgSal=new ImageIcon(ruta+"32x32/salir.png");
		imgexpe=new ImageIcon(ruta+"32x32/expe.png");
		imgBanner=new ImageIcon(ruta+"logo/Imagen1.png");
		// Botones 

	btnUs=new JButton ("   USUARIOS  ", imgUsuarioG);
	btnSal=new JButton("      SALIR        ", imgSal);
	btnExpediente=new JButton("EXPEDIENTES", imgexpe);

	//definicion de logos
	
	JLabel logo;
	logo=new JLabel();
	logo.setIcon(new ImageIcon(ruta+"logo/banner.png"));
	
	
	fondo=new JLabel();
	fondo.setIcon(new ImageIcon(ruta+"logo/centro.png"));
	fondo.setHorizontalAlignment(JLabel.CENTER);
	fondo.setVerticalAlignment(JLabel.CENTER);
	this.add(fondo);
	this.getContentPane().setBackground(Color.WHITE);
	
	this.setTitle("Archivo General Control de Estudio - Area de Postgrado");
	this.setIconImage (new ImageIcon(ruta+"logo/logo.png").getImage());
	
	// Barra de Menu
	barraMenu=new JMenuBar();
	
	//Menu Registro
	
	registro= new JMenu("REGISTRO");
	registro.setMnemonic('R');
	usuarios=new JMenuItem("Usuarios",imgUsP);
	usuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
	expedientes=new JMenuItem("expedientes");
	expedientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
	
	registro.add(usuarios);
	registro.add(expedientes);
		
	//Menu Reportes
	reportes=new JMenu("REPORTES");
	reportes.setMnemonic('P');
	rep_usuarios=new JMenuItem("Usuarios",imgRepP);
	rep_usuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
	reportes.add(rep_usuarios);
	
	//Menu Otros
	
	otros=new JMenu("OTROS");
	otros.setMnemonic('O');
	cerrar=new JMenuItem("Cerrar sesion",imgSalP);
	cerrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));
	
	otros.add(cerrar);
	
	barraMenu.add(registro);
	barraMenu.add(reportes);
	barraMenu.add(otros);
	this.setJMenuBar(barraMenu);
	
	
	
	//DEFINIR ETIQUETAS Y CAMPOS
	
	desarrollo=new JLabel("Sistema desarrollado por: Jose Luis Seijas - 2015");
	
	
	//PANELES
	
	panelPrincipal=new JPanel(new BorderLayout());
	this.setSize(600, 400);
	
	panelPrincipal.setBorder((Border) new LineBorder(Color.BLACK,3,true));
	
	panelBotonera = new JPanel();
	panelBotonera.setLayout((LayoutManager) new BoxLayout(panelBotonera, BoxLayout.Y_AXIS));
	panelBotonera.setBackground(Color.gray);
	panelBotonera.add(btnUs);
	panelBotonera.add(btnExpediente);
	panelBotonera.add(btnSal);
	
	
	
	//panel superior
	
	panelTitulo=new JPanel(new BorderLayout());

	
	this.setSize(300, 100);
	
	panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.CENTER));
	panelAuxiliar.add(logo);
	panelPrincipal.add(panelAuxiliar);
	panelTitulo.add(panelAuxiliar,BorderLayout.WEST);
	
	
	//Panel barra de menu
	panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.LEFT));
	panelAuxiliar.add(barraMenu);
	panelTitulo.add(panelAuxiliar,BorderLayout.SOUTH);
	
	//panel inferior
	
	panelDesarrollo=new JPanel(new FlowLayout());
	this.setSize(300, 100);
	
	panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.CENTER));
	panelPrincipal.setBorder((Border) new LineBorder(Color.BLACK,2,true));
	panelAuxiliar.add(desarrollo);
	panelDesarrollo.add(panelAuxiliar,BorderLayout.WEST);
	
	

	
	
	
	//-----------------------ENLACES CON CONTROLADOR<---------------------------
	
	
	ControladorVistaPrincipal eco=new ControladorVistaPrincipal(this,xUsuario,xExpediente);
	btnSal.addActionListener(eco);
	btnExpediente.addActionListener(eco);
	cerrar.addActionListener(eco);
	usuarios.addActionListener(eco);
	btnUs.addActionListener(eco);
	rep_usuarios.addActionListener(eco);
	
	
	// -----------------niveles de seguridad-----------------------
	
	if(operador.getNivel()==1){
		registro.setEnabled(true); 
		reportes.setEnabled(true); 
	}
	
	else if(operador.getNivel()==2){
		btnUs.setEnabled(false); 
		usuarios.setEnabled(false);
		rep_usuarios.setEnabled(false);
	} 
	
	

	this.add(fondo,BorderLayout.CENTER);
	this.add(panelTitulo,BorderLayout.NORTH);
	this.add(panelDesarrollo,BorderLayout.SOUTH);		
	this.add(panelBotonera,BorderLayout.WEST);
	this.setUndecorated(true);
	this.pack();
	
	this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	this.setVisible(true);
	
}

	
	//---------------------------->getters and setters<-------------------------------

	public JMenu getRegistro() {
		return registro;
	}

	public JMenu getReportes() {
		return reportes;
	}

	public JMenu getOtros() {
		return otros;
	}
	
		public JButton getBtnSal() {
		return btnSal;
	}

	
	public JButton getBtnExpediente() {
		return btnExpediente;
	}
	
	public JButton getBtnUs() {
		return btnUs;
	}

	public JMenuItem getCerrar() {
		return cerrar;
	}


	public JMenuItem getusuarios() {
		return usuarios;
	}
	
	public JMenuItem getrep_usuarios() {
		return rep_usuarios;
	}


}
