package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import modelos.*;

import reportes.ReportePdfUsuarios;
import vistas.*;

public class ControladorVistaPrincipal implements ActionListener {

	private VistaPrincipal vista;
	private Usuario xusuario;
	private Expediente xexpediente;

	public ControladorVistaPrincipal(VistaPrincipal xvista, Usuario xUsuario, Expediente xExpediente) {
		this.vista = xvista;
		this.xusuario=xUsuario;
		this.xexpediente=xExpediente;
		
		
		
	}

	public void actionPerformed(ActionEvent accion) {
		int fuera;
		if ((accion.getSource().equals(vista.getBtnSal()))
				|| (accion.getSource().equals(vista.getCerrar()))) {
		fuera = JOptionPane.showConfirmDialog(null,	"¿Esta seguro que desea salir?", "Salir del Sistema", JOptionPane.YES_NO_OPTION);
			if (fuera == JOptionPane.OK_OPTION) {
				JOptionPane.showMessageDialog(vista, "Hasta Luego", "Salir del Sistema", 1);
				System.exit(0);// finaliza un programa
			}
		} 
		if ((accion.getSource().equals(vista.getBtnUs()))|| (accion.getSource().equals(vista.getusuarios())))
			new VistaUsuarios(xusuario);
		if(accion.getSource().equals(vista.getrep_usuarios()))
			new ReportePdfUsuarios();
		if(accion.getSource().equals(vista.getBtnExpediente()))
			new VistaExpedientes(xexpediente);
	
		
		}

	
	}
