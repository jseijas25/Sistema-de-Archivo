package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controladores.ControladorUsuarios;


public class VistaUsuarios extends JDialog {

	private JTextField txtNombre;
	private JPasswordField pwdClave;
    private JCheckBox active;
	private JButton btnGuardar,btnBuscar,btnLimpiar,btnEliminar,btnRetornar,btnModificar,btnImprimir;
	private JComboBox cmbNivel;

	public VistaUsuarios(){

		String ruta="../Sistema/imagenesSistema/";
        JLabel lblNombre, lblClave, lblNivel, lblTitulo,lblTitulo2;
        Icon imgGuardar,imgBuscar,imgLimpiar,imgEliminar,imgRetornar,imgImprimir,imgModificar, imgUsuariosG;
        JPanel pnlPrincipal,pnlForm,pnlBotonera,pnlAuxiliar;

        imgGuardar=new ImageIcon(ruta+"32x32/USB.png");
		imgBuscar=new ImageIcon(ruta+"32x32/buscar.png");
		imgLimpiar=new ImageIcon(ruta+"32x32/Delete.png");
		imgEliminar=new ImageIcon(ruta+"32x32/eliminar.png");
		imgRetornar=new ImageIcon(ruta+"32X32/agt_home.png");
		imgImprimir=new ImageIcon(ruta+"32x32/pdf.png");
		imgModificar=new ImageIcon(ruta+"32X32/Pen 3.png");
		imgUsuariosG=new ImageIcon(ruta+"32X32/usuario.png");

		btnBuscar=new JButton("BUSCAR",imgBuscar);
		btnBuscar.setToolTipText("Buscar Usuario por cedula.");
		
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
		
		lblTitulo=new JLabel(imgUsuariosG);
		lblTitulo2=new JLabel("REGISTRO DE USUARIOS");

		lblNombre=new JLabel("Nombre de usuario");
		txtNombre=new JTextField(10);
		
		lblClave=new JLabel("Clave");
		pwdClave=new JPasswordField(10);
		
		lblNivel=new JLabel("Nivel:");
		String[] lista2={"1","2"};
		cmbNivel=new JComboBox(lista2);
		cmbNivel.setSelectedIndex(-1);

		active=new JCheckBox(" Usuario Activo");

		pnlPrincipal=new JPanel(new BorderLayout());
		this.setSize(600, 400);
		//pnlPrincipal.setBorder((Border) new LineBorder(Color.BLACK,3,true));

		pnlForm=new JPanel(new GridLayout(4,1));
		pnlAuxiliar=new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlAuxiliar.add(lblTitulo);
		pnlAuxiliar.add(lblTitulo2);
		pnlForm.add(pnlAuxiliar);

		pnlAuxiliar=new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlAuxiliar.add(lblNombre);
		pnlAuxiliar.add(txtNombre);
		pnlAuxiliar.add(btnBuscar);
		pnlForm.add(pnlAuxiliar);
		
		pnlAuxiliar=new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlAuxiliar.add(lblClave);
		pnlAuxiliar.add(pwdClave);
        pnlAuxiliar.add(lblNivel);
        pnlAuxiliar.add(cmbNivel);
		pnlForm.add(pnlAuxiliar);

		pnlAuxiliar=new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlAuxiliar.add(active);
		pnlForm.add(pnlAuxiliar);
		

		pnlBotonera = new JPanel();
		pnlBotonera.setLayout(new GridLayout(5,1));
		pnlBotonera.add(btnGuardar);
		pnlBotonera.add(btnModificar);
		pnlBotonera.add(btnEliminar);
		pnlBotonera.add(btnLimpiar);
		pnlBotonera.add(btnImprimir);
	//	pnlBotonera.add(btnRetornar);

		ControladorUsuarios eco=new ControladorUsuarios(this);
		btnRetornar.addActionListener(eco);
		btnLimpiar.addActionListener(eco);
		btnGuardar.addActionListener(eco);
		btnModificar.addActionListener(eco);
		btnBuscar.addActionListener(eco);
		btnEliminar.addActionListener(eco);
		btnImprimir.addActionListener(eco);
		txtNombre.addKeyListener(eco);

		pnlPrincipal.add(pnlForm,BorderLayout.CENTER);
		pnlPrincipal.add(pnlBotonera,BorderLayout.EAST);
		

		this.add(pnlPrincipal);


        this.setLocationRelativeTo(null);
		 this.pack();
		 this.setAlwaysOnTop(true);
		 this.setVisible(true);

	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JPasswordField getPwdClave() {return pwdClave;}

	public JComboBox getCmbNivel() {
		return cmbNivel;
	}

    public JCheckBox getActive()  {
        return active;
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

	public JButton getBtnEliminar() {return btnEliminar;}

	public JButton getBtnRetornar() {
		return btnRetornar;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public void setTxtNombre(String txtNombre) {
		this.txtNombre.setText(txtNombre);
	}

	public void setPwdClave(String pwdclave) {this.pwdClave.setText(pwdclave);}

	public void setCmbNivel(int valor) {this.cmbNivel.setSelectedIndex(valor);}

    public void setActive(JCheckBox active) {this.active = active;}
}

