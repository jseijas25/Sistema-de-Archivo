package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelos.Expediente;
import reportes.ReportePdfSolvencia;
import vistas.VistaExpedientes;
import dataBase.ExpedienteDAO;

public class ControladorVistaExpediente implements ActionListener,KeyListener {

    private VistaExpedientes vista;
	private Expediente xexpediente;

	public ControladorVistaExpediente (VistaExpedientes xvista, Expediente xExpediente) {
		this.vista=xvista;
		this.xexpediente=xExpediente;
	}

	@Override

	public void actionPerformed(ActionEvent accion) {
	

		/*if(accion.getSource().equals(vista.getBtnRetornar()))
			vista.dispose();

		if(accion.getSource().equals(vista.getBtnImprimir())){
    		vista.dispose();
			new ReportePdfSolvencia(xexpediente);
        }

		else if(accion.getSource().equals(vista.getBtnLimpiar())){
			Vaciar();
			}

		else if(accion.getSource().equals(vista.getBtnBuscar())){
			if(VerificarCedula((vista.getTxtci().getText()))){
				JOptionPane.showMessageDialog(vista, "Cedula No Registrada", "Error", 0);
				vista.getTxtci().requestFocus();
			}
			else{
				vista.setTxtci(xexpediente.getCi());
				vista.setTxtNombre(xexpediente.getNombre());
				vista.setTxtApellido(xexpediente.getApellido());
				vista.setPrograma(xexpediente.getId_Programa()-1);
				
				vista.setLblExpediente(xexpediente.getRecord());
			}
		}
//------------------------------------Agregar-------------------------------------------------
				
		else if(accion.getSource().equals(vista.getBtnGuardar())){


			
			
			if(VerificarCedula(vista.getTxtci().getText()))	{
					ExpedienteDAO baseDatos=new ExpedienteDAO("archivopost");
					
					xexpediente.setCi(vista.getTxtci().getText());
					xexpediente.setNombre(vista.getTxtNombre().getText().toUpperCase());
					xexpediente.setApellido(vista.getTxtApellido().getText().toUpperCase());
					
					
						if((xexpediente.getCi().trim().length()>0)&&	
							(xexpediente.getNombre().trim().length()>0)&&
							(xexpediente.getApellido().trim().length()>0))
							{

							baseDatos.Conectar();
							try{
								if (baseDatos.AgregarExpediente(xexpediente)){
									JOptionPane.showMessageDialog(vista, "Expediente Registrado Exitosamente");
									Vaciar();
							
								}
						
							} catch (SQLException e) {
								
							}
						}
					else{
						JOptionPane.showMessageDialog(vista, "Debe llenar todos los campos","Error",0);
						vista.getTxtExpediente().requestFocus();
					}
						
				}
				else{
					JOptionPane.showMessageDialog(vista, "El numero de expediente o cedula ya existe","Error",0);
					vista.getTxtExpediente().requestFocus();
				}
			}//fin de agregar

		//------------------------------------Modificar----------------------------------------------
		
else if(accion.getSource().equals(vista.getBtnModificar())){
		if(!VerificarCedula(vista.getTxtci().getText()))	{
						
						
			ExpedienteDAO baseDatos=new ExpedienteDAO("archivopost");
			
			xexpediente.setCi(vista.getTxtci().getText());
			xexpediente.setNombre(vista.getTxtNombre().getText().toUpperCase());
			xexpediente.setApellido(vista.getTxtApellido().getText().toUpperCase());
			
			
				if((xexpediente.getCi().trim().length()>0)&&	
					(xexpediente.getNombre().trim().length()>0)&&
					(xexpediente.getApellido().trim().length()>0))
					{

								baseDatos.Conectar();
							
								if (baseDatos.ModificarExpediente(xexpediente)){
									JOptionPane.showMessageDialog(vista, "Expediente Modificado Exitosamente");
									
								
								}
							
							}
						else{
							JOptionPane.showMessageDialog(vista, "Debe llenar todos los campos","Error",0);
							vista.getTxtExpediente().requestFocus();
						}
							
					}
					else{
						JOptionPane.showMessageDialog(vista, "El numero de Expediente o cedula No existe","Error",0);
						vista.getTxtExpediente().requestFocus();
					}
					
					
			}//--------------fin de modificar---------------

//------------------------------------eliminar-----------------------------------------
		
//else if(accion.getSource().equals(vista.getBtnEliminar())){
//	if(!VerificarCedula(vista.getTxtCed().getText())&&
//			!VerificarUsuarioNombre(vista.getTxtNombreUsuario().getText()))	{
//		ControladorTablaUsuario baseDatos =new ControladorTablaUsuario("archivopost");
//		xusuario.setStatus("D");
		
		
		
//		if((xusuario.getCedula().trim().length()>0)&&
//				(xusuario.getApellido().trim().length()>0)
//				&&(xusuario.getNombre().trim().length()>0)
//				&&(xusuario.getNombreUsuario().trim().length()>0)
//				&&(xusuario.getClave().trim().length()>0)&&
//				(xusuario.getNivel()!=0))	{

//				baseDatos.Conectar();
			
//				if (baseDatos.ModificarUsuario(xusuario)){
//					JOptionPane.showMessageDialog(vista, "Usuario Desincorporado Exitosamente");
//					this.vaciar();
					
				
//				}
			
//			}
//		else{
//			JOptionPane.showMessageDialog(vista, "Debe llenar todos los campos","Error",0);
//			vista.getTxtCed().requestFocus();
//		}
			
//	}
//	else{
//		JOptionPane.showMessageDialog(vista, "La cedula o login No existe","Error",0);
//		vista.getTxtCed().requestFocus();
//	}
	
	
//}


		*/
}

	@Override
	public void keyPressed(KeyEvent accion) {
		/*if(accion.getSource().equals(vista.getTxtci())
				&&(accion.getKeyCode()== KeyEvent.VK_ENTER))
			
			if(VerificarCedula(vista.getTxtci().getText()))
				vista.getTxtNombre().requestFocus();
				else{
						JOptionPane.showMessageDialog(vista, "Cedula ya Registrada", "Error", 0);
						vista.setTxtci(xexpediente.getCi());
						vista.setTxtNombre(xexpediente.getNombre());
						vista.setTxtApellido(xexpediente.getApellido());
						
				}

		else if(accion.getSource().equals(vista.getTxtci())
				&&(accion.getKeyCode()== KeyEvent.VK_ENTER))
			
			if(accion.getSource().equals(vista.getTxtNombre())
				&&(accion.getKeyCode()== KeyEvent.VK_ENTER))
			vista.getTxtApellido().requestFocus();*/
		}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		
		
	//-------------> vaciar campos <-------------
	public void Vaciar(){
		vista.setTxtci(null);
		vista.setTxtNombre(null);
		vista.setTxtApellido(null);
		vista.getTxtci().requestFocus();
		
	}
	

}