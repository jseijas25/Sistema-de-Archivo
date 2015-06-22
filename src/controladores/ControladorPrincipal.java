package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import reportes.ReportePdfUsuarios;
import vistas.*;

public class ControladorPrincipal implements ActionListener {

	private VistaPrincipal vista;

	public ControladorPrincipal(VistaPrincipal vistaPrincipal) {
		this.vista = vistaPrincipal;
	}

	public void actionPerformed(ActionEvent event) {
		if ((event.getSource().equals(vista.getBtnSalir())) ||
				(event.getSource().equals(vista.getItmCerrar()))) {
			vista.salir();
		}
		if ((event.getSource().equals(vista.getBtnUsuario())) ||
				(event.getSource().equals(vista.getItmUsuario())))
			new VistaBuscarUsuario();
		if(event.getSource().equals(vista.getItmReportes()))
			new ReportePdfUsuarios();
		if(event.getSource().equals(vista.getBtnExpediente()) ||
                (event.getSource().equals(vista.getItmExpedientes())))
			new VistaBuscarEstudiante();
        if(event.getSource().equals(vista.getBtnPrograma()) ||
                (event.getSource().equals(vista.getItmProgramas())))
            new VistaBuscarPrograma();
    }
}
