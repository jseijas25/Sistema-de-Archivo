package controladores;

import dataBase.ProgramaDAO;
import funciones.OperationType;
import funciones._Con;
import modelos.Programa;
import vistas.VistaBuscarPrograma;
import vistas.VistaPrograma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class ControladorPrograma extends BaseControlador {

    private Programa programa;
    private VistaPrograma vista;

    public ControladorPrograma(VistaPrograma vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource().equals(vista.getBtnCrear())){
            if(vista.getData(programa)){
                if(ProgramaDAO.create(programa)){
                    vista.setMessage("Programa creado con exito");
                    vista.dispose();
                    _Con.getInstance().setOperation(OperationType.READ);
                    new VistaPrograma();
                } else {
                    vista.setError("Programa no creado");
                }
            }
        }
        if(e.getSource().equals(vista.getBtnEditar())){
            if(vista.isEditable()){
                if(vista.getData(programa)){
                    if(ProgramaDAO.update(programa)){
                        vista.setMessage("Exito al actualizar al Programa");
                    }else{
                        vista.setError("Programa no actualizado");
                    }
                }
            }
            vista.setEditable();
        }
        if(e.getSource().equals(vista.getBtnBorrar())){
            int op = JOptionPane.showConfirmDialog(vista, "¿Está seguro que desea eliminar el Programa?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if(op == JOptionPane.YES_OPTION){
                if(ProgramaDAO.delete(programa.getId())){
                    vista.setMessage("Programa eliminado con exito");
                    vista.dispose();
                    new VistaBuscarPrograma();
                }else{
                    vista.setError("Programa no eliminado");
                }
            }
        }
        if(e.getSource().equals(vista.getBtnSalir())){
            if(vista.isEditable()){
                if(_Con.getInstance().getOperation()==OperationType.READ){
                    int op = JOptionPane.showConfirmDialog(vista, "¿Está seguro que desea cancelar la edición?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if(op == JOptionPane.YES_OPTION){
                        new VistaPrograma();
                    }
                }else if(_Con.getInstance().getOperation() == OperationType.CREATE){
                    int op = JOptionPane.showConfirmDialog(vista, "¿Está seguro que desea cancelar la creación\ndel nuevo programa?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if(op == JOptionPane.YES_OPTION){
                        new VistaBuscarPrograma();
                    }
                }
            }
            vista.dispose();
        }
        vista.showError();
        vista.showMessage();
    }


    @Override
    public void windowOpened(WindowEvent e) {
        super.windowOpened(e);
        programa = _Con.getInstance().getPrograma();
        vista.setData(programa);
    }
}
