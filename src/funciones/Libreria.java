package funciones;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Libreria {
	//----------->encriptar<---------------
	public String Encriptar(String xcadena){
		String salida;
		xcadena=xcadena.trim();
		int[] valor=new int[xcadena.length()+1];
		int aleatorio=(int) (Math.random()*9)+1;//aleatorio
		char devolverCadena;
		
		//convertir en Ascii
		
		valor[0]=aleatorio;
		for(int i=0;i<xcadena.length();i++){
			valor[i+1]=((int)xcadena.charAt(i))+aleatorio;
		}
		valor[xcadena.length()]=
				((int)xcadena.charAt(xcadena.length()-1))+aleatorio;
		
		salida=String.valueOf(valor[0]);
		for(int i=1;i<valor.length;i++){
			devolverCadena=(char)valor[i];
			salida+=devolverCadena;
		}
		salida=salida.trim();
		return salida;
		
	}
	//----------->desencriptar<---------------
	public  String Desencriptar(String xcadena){
		String salida=null;
		xcadena=xcadena.trim();
		int[] valor=new int[xcadena.length()-1];
		int aleatorio=Integer.parseInt(xcadena.substring(0,1));//aleatorio
		char devolverCadena;
		
		//convertir en Ascii
		
	
		for(int i=1;i<xcadena.length();i++){
			valor[i-1]=((int)xcadena.charAt(i))-aleatorio;
		}
		
		
		
		for(int i=0;i<valor.length;i++){
			if(i==0) salida=String.valueOf((char)valor[i]);
			else{
				salida+=(char)valor[i];
			}
			
		}
		salida=salida.trim();
		return salida;
		
	}
	//---------> cambiar formato de fecha d/m/a <-------------
	public String CambiarFecha(String xfecha){
		String fechaActual;
		String[] dat1= xfecha.split("-");
		String dia,mes,anno;
		mes=dat1[1];
		dia=dat1[2];
		anno=dat1[0];
			
		fechaActual=dia+"/"+mes+"/"+anno;
			
			
		return fechaActual;
			
	}
	//---------> cambiar formato de fecha a/m/d <-------------
	public String CambiarFechaAmericano(String xfecha){
		String fechaActual;
		String[] dat1= xfecha.split("/");
		String dia,mes,anno;
		mes=dat1[1];
		dia=dat1[0];
		anno=dat1[2];
			
		fechaActual=anno+"-"+mes+"-"+dia;
			
			
		return fechaActual;
			
	}


}
