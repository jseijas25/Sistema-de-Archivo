package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controladores.ControladorExpediente;

public class VistaExpedientes extends JDialog {

	private JPanel pestana1;
	private JPanel panelCampos;
	private JPanel panelBotonera;
	private JPanel panelAuxiliar;
	private JPanel paneltitulo;
	private JPanel panelprograma;
	private JLabel lblci,lblNombre,lblApellido,lblExpediente,lblprograma,lblTitulo,lblTitulo2;
	private JTextField txtci,txtNombre,txtApellido,txtExpediente;
	private JComboBox programa;
	private Icon imgGuardar,imgBuscar,imgLimpiar,imgEliminar,imgRetornar,
			imgImprimir,imgModificar, imgExpeG;
	private JButton btnGuardar,btnBuscar,btnLimpiar,btnEliminar,
			btnRetornar,btnModificar,btnImprimir;
	private JCheckBox fotos, curriculo, fondonegro, notas;
	private JTabbedPane pestanas;

	public VistaExpedientes() {

        //imagenes

        String ruta = "../Sistema/imagenesSistema/";
        imgGuardar = new ImageIcon(ruta + "32x32/USB.png");
        imgBuscar = new ImageIcon(ruta + "32x32/buscar.png");
        imgLimpiar = new ImageIcon(ruta + "32x32/Delete.png");
        imgEliminar = new ImageIcon(ruta + "32x32/eliminar.png");
        imgRetornar = new ImageIcon(ruta + "32X32/agt_home.png");
        imgImprimir = new ImageIcon(ruta + "32x32/pdf.png");
        imgModificar = new ImageIcon(ruta + "32X32/Pen 3.png");
        imgExpeG = new ImageIcon(ruta + "32X32/expe.png");

        //botones

        btnBuscar = new JButton("BUSCAR", imgBuscar);
        btnBuscar.setToolTipText("Buscar Expediente por codigo.");

        btnGuardar = new JButton("REGISTRAR", imgGuardar);
        btnGuardar.setToolTipText("Registrar un nuevo Expediente.");

        btnModificar = new JButton("MODIFICAR", imgModificar);
        btnModificar.setToolTipText("Modificar datos un Expediente registrado.");

        btnEliminar = new JButton("ELIMINAR", imgEliminar);
        btnEliminar.setToolTipText("Retirar un Expediente.");

        btnLimpiar = new JButton("LIMPIAR", imgLimpiar);
        btnLimpiar.setToolTipText("Limpiar campos");

        btnRetornar = new JButton("REGRESAR", imgRetornar);
        btnRetornar.setToolTipText("regresar a la pantalla incial");

        btnImprimir = new JButton("SOLVENCIA", imgImprimir);
        btnImprimir.setToolTipText("Imprimir Solvencia de Expediente en pdf");

        //		btnImprimir.setEnabled(false);

        //definir campos y etiquetas

        lblTitulo = new JLabel(imgExpeG);
        lblTitulo2 = new JLabel("REGISTRO DE EXPEDIENTES");


        lblci = new JLabel("Cedula");
        txtci = new JTextField(10);


        lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(25);

        lblApellido = new JLabel("Apellido:");
        txtApellido = new JTextField(25);

        lblExpediente = new JLabel("Expediente:");
        txtExpediente = new JTextField(8);

        lblprograma = new JLabel("Programa");
        String[] lista1 = {"INVESTIGACION EDUCATIVA", "GERENCIA ADMINISTRATIVA", "DERECHO LABLORAL", "DESARROLLO COMUNITARIO"};
        programa = new JComboBox<>(lista1);
        programa.setSelectedIndex(-1);

        fotos = new JCheckBox(" 2 Fotos Tipo Carnet");
        curriculo = new JCheckBox("Resumen Curricular");
        fondonegro = new JCheckBox("Fondo negro de Titulo de Pregrado");
        notas = new JCheckBox("Notas de Pregrado Certificadas");
        //paneles

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        this.setSize(600, 400);
        this.setLocation(300, 200);
        panelPrincipal.setBorder((Border) new LineBorder(Color.BLACK, 3, true));


        //panel campos
        panelCampos = new JPanel(new GridLayout(4, 1));
        panelAuxiliar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelAuxiliar.add(lblTitulo);
        panelAuxiliar.add(lblTitulo2);
        panelCampos.add(panelAuxiliar);


        panelAuxiliar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAuxiliar.add(lblci);
        panelAuxiliar.add(txtci);
        panelAuxiliar.add(btnBuscar);
        panelCampos.add(panelAuxiliar);

        panelAuxiliar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAuxiliar.add(lblNombre);
        panelAuxiliar.add(txtNombre);
        panelCampos.add(panelAuxiliar);


        panelAuxiliar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAuxiliar.add(lblApellido);
        panelAuxiliar.add(txtApellido);
        panelCampos.add(panelAuxiliar);


        //panel botonera

        panelBotonera = new JPanel();
        panelBotonera.setLayout(new GridLayout(5, 1));
        panelBotonera.add(btnGuardar);
        panelBotonera.add(btnModificar);
        panelBotonera.add(btnEliminar);
        panelBotonera.add(btnLimpiar);
        panelBotonera.add(btnRetornar);


        //pesta�as

        JTabbedPane pestanas = new JTabbedPane();

        JPanel pestana1 = new JPanel();
        pestana1.setPreferredSize(new Dimension(100, 100));
        pestana1.setLayout(new BorderLayout());


        JPanel panelcheck = new JPanel();
        panelcheck.setLayout(new GridLayout(5, 1));
        panelcheck.add(fotos);
        panelcheck.add(curriculo);
        panelcheck.add(fondonegro);
        panelcheck.add(notas);


        panelprograma = new JPanel(new GridLayout(2, 1));

        panelAuxiliar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAuxiliar.add(lblprograma);
        panelAuxiliar.add(programa);
        panelprograma.add(panelAuxiliar);

        panelAuxiliar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAuxiliar.add(btnImprimir);
        panelprograma.add(panelAuxiliar);

        pestana1.add(panelprograma, BorderLayout.EAST);
        pestana1.add(panelcheck, BorderLayout.WEST);

        pestanas.addTab("Expediente", pestana1);


        //----------->enlaces<-----------
        ControladorExpediente eco = new ControladorExpediente(this);
        btnRetornar.addActionListener(eco);
        btnLimpiar.addActionListener(eco);
        btnGuardar.addActionListener(eco);
        btnModificar.addActionListener(eco);
        btnBuscar.addActionListener(eco);
        btnEliminar.addActionListener(eco);
        btnImprimir.addActionListener(eco);
        txtExpediente.addKeyListener(eco);
        txtci.addKeyListener(eco);
        txtNombre.addKeyListener(eco);
        txtApellido.addKeyListener(eco);


        panelPrincipal.add(panelCampos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotonera, BorderLayout.EAST);
        panelPrincipal.add(pestanas, BorderLayout.SOUTH);


        this.add(panelPrincipal);


        this.setUndecorated(true);
        this.pack();
        this.setAlwaysOnTop(true);
        this.setVisible(true);

    }
				
				
	public JTextField getTxtci() {
		return txtci;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public JTextField getTxtExpediente() {
		return txtExpediente;
	}

	
	public JComboBox getPrograma() {
		return programa;
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


	public void setTxtExpediente(String txtExpediente) {
		this.txtExpediente.setText(txtExpediente);
	}


	public void setTxtci(String txtci) {
		this.txtci.setText(txtci);
	}

	public void setTxtNombre(String txtNombre) {
		this.txtNombre.setText(txtNombre);
	}

	
	public void setTxtApellido(String txtApellido) {
		this.txtApellido.setText(txtApellido);
	}

	public void setPrograma(int valor) {
		this.programa.setSelectedIndex(valor);
	}


	public JLabel getLblExpediente() {
		return lblExpediente;
	}


	public void setLblExpediente(String lblExpediente) {
		this.lblExpediente.setText(lblExpediente);
	}

}
