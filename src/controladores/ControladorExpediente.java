package controladores;

import java.awt.event.ActionEvent;

import modelos.Expediente;
import vistas.PestanaExpediente;

public class ControladorExpediente extends BaseControlador {

    private Expediente expediente;
    private PestanaExpediente vista;

    public ControladorExpediente(PestanaExpediente vista) {
        this.vista = vista;
        this.expediente = new Expediente();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    super.actionPerformed(e);
    }
}