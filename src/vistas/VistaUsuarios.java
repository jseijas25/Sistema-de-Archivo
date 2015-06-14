package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import modelos.Usuario;

import controladores.ControladorVistaUsuarios;


public class VistaUsuarios extends JDialog {
	private String ruta="../Sistema/imagenesSistema/";
	private Usuario xusuario;

	private JPanel panelPrincipal,panelCampos,panelBotonera,panelimagen,panelAuxiliar, paneltitulo;


	private JLabel lblCed,lblNombre,lblApellido,lblGenero,lblNombreUsuario, lblClave,lblStatus,
	 				lblNivel, lblTitulo,lblTitulo2;


	private JTextField txtCed,txtNombre,txtApellido,
						txtNombreUsuario,txtStatus;

	private JPasswordField txtClave;
	
	private JComboBox comboGenero,comboNivel;

	private Icon imgGuardar,imgBuscar,imgLimpiar,imgEliminar,imgRetornar,
			imgImprimir,imgModificar, imgUsuariosG;


	private JButton btnGuardar,btnBuscar,btnBuscar2,btnLimpiar,btnEliminar,
			btnRetornar,btnModificar,btnImprimir;


	public VistaUsuarios(Usuario xUsuario){
		this.xusuario=xUsuario;
		
		//imagenes
		

		imgGuardar=new ImageIcon(ruta+"32x32/USB.png");
		imgBuscar=new ImageIcon(ruta+"32x32/buscar.png");
		imgLimpiar=new ImageIcon(ruta+"32x32/Delete.png");
		imgEliminar=new ImageIcon(ruta+"32x32/eliminar.png");
		imgRetornar=new ImageIcon(ruta+"32X32/agt_home.png");
		imgImprimir=new ImageIcon(ruta+"32x32/pdf.png");
		imgModificar=new ImageIcon(ruta+"32X32/Pen 3.png");
		imgUsuariosG=new ImageIcon(ruta+"32X32/usuario.png");
		
		//botones

		
		JLabel logo;
		logo=new JLabel();
		logo.setIcon(new ImageIcon(ruta+"logo/banne2.png"));


		btnBuscar=new JButton("BUSCAR",imgBuscar);
		btnBuscar.setToolTipText("Buscar Usuario por cedula.");
		
		btnBuscar2=new JButton("BUSCAR",imgBuscar);
		btnBuscar.setToolTipText("Buscar Usuario por Nombre de usuario.");
		
		
		btnGuardar=new JButton("REGISTRAR",imgGuardar);
		btnGuardar.setToolTipText("Registrar un nuevo Usuario.");
		
		btnModificar=new JButton("MODIFICAR",imgModificar);
		btnModificar.setToolTipText("Modificar datos un Usuario registrado.");
		
		btnEliminar=new JButton("ELIMINAR", imgEliminar);
		btnEliminar.setToolTipText("Eliminar un Usuario.");
		
		btnLimpiar=new JButton("LIMPIAR", imgLimpiar);
		btnLimpiar.setToolTipText("Limpiar campos");
		
		btnRetornar=new JButton("REGRESAR", imgRetornar);
		btnRetornar.setToolTipText("regresar a la pantalla incial");
		
		btnImprimir=new JButton("LISTA DE USUARIOS",imgImprimir);
		btnImprimir.setToolTipText("Imprimir Lista de Usuarios en pdf");
		
		

		//definir campos y etiquetas
		
		lblTitulo=new JLabel(imgUsuariosG);
		lblTitulo2=new JLabel("REGISTRO DE USUARIOS");

		lblCed=new JLabel("Cedula:");
		txtCed=new JTextField(8);
		
		lblNombre=new JLabel("Nombre:");
		txtNombre=new JTextField(23);
		
		lblApellido=new JLabel("Apellido:");
		txtApellido=new JTextField(23);
		
		lblGenero=new JLabel("Genero:");
		String[] lista1={"F","M"};
		comboGenero=new JComboBox(lista1);
		comboGenero.setSelectedIndex(-1);

		lblStatus=new JLabel("Estado");
		txtStatus=new JTextField(1);
		txtStatus.setEditable(false);
								
		lblNombreUsuario=new JLabel("Login");
		txtNombreUsuario=new JTextField(10);
		



		lblClave=new JLabel("Contrase�a");
		txtClave=new JPasswordField(6);
		
		lblNivel=new JLabel("Nivel:");
		String[] lista2={"1","2"};
		comboNivel=new JComboBox(lista2);
		comboNivel.setSelectedIndex(-1);

		

		//paneles
		
		panelPrincipal=new JPanel(new BorderLayout());
		this.setSize(600, 400);
		this.setLocation(300,200);
		panelPrincipal.setBorder((Border) new LineBorder(Color.BLACK,3,true));
		
		//panel titulo
		paneltitulo=new JPanel(new GridLayout(1,1));
	
		paneltitulo.setBackground(Color.LIGHT_GRAY);
		this.setLocation(280,240);
		
		panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelAuxiliar.add(logo);
		paneltitulo.add(panelAuxiliar);
	
		
		//panel campos
		panelCampos=new JPanel(new GridLayout(7,1));
	
		panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelAuxiliar.add(lblTitulo);
		panelAuxiliar.add(lblTitulo2);
		panelCampos.add(panelAuxiliar);
	
		
		
		panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelAuxiliar.add(lblCed);
		panelAuxiliar.add(txtCed);
		panelAuxiliar.add(btnBuscar);
		panelCampos.add(panelAuxiliar);
		
		panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelAuxiliar.add(lblNombre);
		panelAuxiliar.add(txtNombre);
		panelCampos.add(panelAuxiliar);
		
		
		panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		panelAuxiliar.add(lblApellido);
		panelAuxiliar.add(txtApellido);
		panelCampos.add(panelAuxiliar);
		
		panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		panelAuxiliar.add(lblGenero);
		panelAuxiliar.add(comboGenero);
	
		panelCampos.add(panelAuxiliar);
		
			
	
						
		panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelAuxiliar.add(lblNombreUsuario);
		panelAuxiliar.add(txtNombreUsuario);
		panelAuxiliar.add(btnBuscar2);
		panelAuxiliar.add(lblNivel);
		panelAuxiliar.add(comboNivel);

		panelCampos.add(panelAuxiliar);
					

		panelAuxiliar=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelAuxiliar.add(lblClave);
		panelAuxiliar.add(txtClave);
		panelAuxiliar.add(lblStatus);
		panelAuxiliar.add(txtStatus);
		

		panelCampos.add(panelAuxiliar);
		

	
	
	

		//panel botonera
		
		panelBotonera = new JPanel();
		panelBotonera.setLayout(new GridLayout(6,1));		
		panelBotonera.add(btnGuardar);
		panelBotonera.add(btnModificar);
		panelBotonera.add(btnEliminar);
		panelBotonera.add(btnLimpiar);
		panelBotonera.add(btnImprimir);
		panelBotonera.add(btnRetornar);

		
		
		
		//----------->enlaces<-----------
		ControladorVistaUsuarios eco=new ControladorVistaUsuarios(this,xusuario);
		btnRetornar.addActionListener(eco);
		btnLimpiar.addActionListener(eco);
		btnGuardar.addActionListener(eco);
		btnModificar.addActionListener(eco);
		btnBuscar.addActionListener(eco);
		btnBuscar2.addActionListener(eco);
		btnEliminar.addActionListener(eco);
		btnImprimir.addActionListener(eco);
		txtCed.addKeyListener(eco);
		txtNombre.addKeyListener(eco);
		txtApellido.addKeyListener(eco);
		comboGenero.addKeyListener(eco);
		txtNombreUsuario.addKeyListener(eco);
		comboNivel.addKeyListener(eco);
		txtClave.addKeyListener(eco);
		
		
		panelPrincipal.add(panelCampos,BorderLayout.CENTER);
		panelPrincipal.add(panelBotonera,BorderLayout.EAST);
		
	
		
		
		this.add(panelPrincipal);
	
		
		this.setUndecorated(true);
		 this.pack();
		 this.setAlwaysOnTop(true);
		 this.setVisible(true);

	}
	
	
	
	
	
	
	
	//------------------->getters and setters<-------------------------
	

	
	
	
	public JTextField getTxtCed() {
		return txtCed;
	}

	public JButton getBtnBuscar2() {
		return btnBuscar2;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public JTextField getTxtNombreUsuario() {
		return txtNombreUsuario;
	}


	public JPasswordField getTxtContrasena() {
		return txtClave;
	}


	public JComboBox getComboGenero() {
		return comboGenero;
	}

	public JComboBox getComboTipo() {
		return comboNivel;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}


	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}


	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JButton getBtnRetornar() {
		return btnRetornar;
	}


	public JButton getBtnModificar() {
		return btnModificar;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	
	public void setTxtCed(String txtCed) {
		this.txtCed.setText(txtCed);
	}

	public void setTxtNombre(String txtNombre) {
		this.txtNombre.setText(txtNombre);
	}

	public void setTxtApellido(String txtApellido) {
		this.txtApellido.setText(txtApellido);
	}

	public void setTxtNombreUsuario(String txtNombreUsuario) {
		this.txtNombreUsuario.setText(txtNombreUsuario);
	}


	public void setTxtContrasena(String txtContrasena) {
		this.txtClave.setText(txtContrasena);
	}

	public void setComboGenero(int valor) {
		this.comboGenero.setSelectedIndex(valor);
	}

	public void setComboTipo(int valor) {
		this.comboNivel.setSelectedIndex(valor);
	}

	public JTextField getTxtEstado() {
		return txtStatus;
	}

	public void setTxtEstado(String txtEstado) {
		this.txtStatus.setText(txtEstado);
	}

}

