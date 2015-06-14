package reportes;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import modelos.Estudiante;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;

public class ReportePdfSolvencia {
	private String ruta="../Sistema/imagenesSistema/logo/";
	 public static final String titulo= "SOLVENCIA DE EXPEDIENTE";

	 public static final String IMAGE = "IMAGE";
	 public static Paragraph detalle, encabezado, cuerpo1,cuerpo2, firma;
	 public static final String TABLE = "TABLE";
	 public static final String CHUNK = "CHUNK";
	 public static final int nLineas=0;
	
	 
	 
	//Textos
	 public static final String SEPARADOR = "______________________________________";

	public ReportePdfSolvencia(Estudiante estudiante){


		Date fechaActual = new Date();

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String hoy = formato.format(fechaActual);
		String fecha= null;
		String[] fechaHoy = hoy.split("/");
		int anos = Integer.parseInt(fechaHoy[2]);
		int mes = Integer.parseInt(fechaHoy[1]);
		int dia=Integer.parseInt(fechaHoy[0]);

		fecha="Fecha: "+fechaHoy[0]+"/"+fechaHoy[1]+"/"+fechaHoy[2];
		final String resultado = "../Sistema/"+ estudiante.getCedula()+"-"+fechaHoy[1]+"-"+fechaHoy[2]+".pdf";

		try{
			Document document = new Document(PageSize.LETTER, 50, 50, 50, 50);
			document.addAuthor("POSTGARDO");
			document.addTitle("EJEMPLO1");
			PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(resultado));
			writer.setInitialLeading(16);
			Rectangle rct = new Rectangle(36, 54, 559, 788);
			writer.setBoxSize("art", rct);
			document.open();

			Image fondo = Image.getInstance(ruta+"reporte.png");
			fondo.scaleToFit(230, 230);
			fondo.setAlignment(Chunk.ALIGN_LEFT);
			document.add(fondo);
			document.add(Chunk.NEWLINE);

			//-----------> fecha<-------------------------

			detalle=new Paragraph(fecha,FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.BLACK));
			document.add(detalle);
			Date tiempo = new Date();
			String xtiempo="Hora: "+tiempo.getHours()+":"+tiempo.getMinutes();
			detalle=new Paragraph(xtiempo,FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.BLACK));
			document.add(detalle);
			document.add(Chunk.NEWLINE);

			//-----------> titulo<-------------------------

			detalle=new Paragraph(titulo,FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLACK));
			detalle.setAlignment(Element.ALIGN_CENTER);
			document.add(detalle);
			document.add(Chunk.NEWLINE);

			//-----------> parrafo 1<-------------------------

			cuerpo1=new Paragraph(30,"      Quien Suscribe, Ing Elias Oswaldo Lopez Delgado encargado" +
					" de la Oficina de Grado, del area de Postgrado de Universidad Romulo " +
					"Gallegos, hace constar que a la(el) ciudadana (o): "+ estudiante.getNombre()+" " +
					""+ estudiante.getApellido()+ " ,titular de la " + "cedula de identidad: N� V- "
					+ estudiante.getCedula()+ " estudiante regular del programa de " +
					"Postgrado tiene el expediente" +
					" completo para poder tramitar la inscripcion del Trabajo Especial de Grado," +
					" Trabajo de Grado y/o Tesis Doctoral. ",FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL, BaseColor.BLACK));
			cuerpo1.setExtraParagraphSpace(2);
			cuerpo1.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(cuerpo1);

			//-----------> parrafo 2<-------------------------

			cuerpo2=new Paragraph(30,"      Constancia que se expide a peticion de parte interesada en la ciudad de San" +
					" Juan de los Morros, a los "+fechaHoy[0]+" dias del mes de "+fechaHoy[1]+" del a�o: "+fechaHoy[2]+".",FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL, BaseColor.BLACK));
			cuerpo2.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(cuerpo2);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);


			firma=new Paragraph(30,"ATENTAMENTE",FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL, BaseColor.BLACK));
			firma.setAlignment(Element.ALIGN_CENTER);
			document.add(firma);

			document.add(Chunk.NEWLINE);
			firma=new Paragraph(30,"ING. ELIAS OSWALDO DELGADO",FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL, BaseColor.BLACK));
			firma.setAlignment(Element.ALIGN_CENTER);
			document.add(firma);


			Rectangle rect = writer.getBoxSize("art");
			//numero de pagina
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(String.format("pagina %d", writer.getPageNumber())),
					(rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);

			document.close();

		}catch(Exception ex){

		}

		try {
			//abrirlo por pantalla
			File path =  new File(estudiante.getCedula()+"-"+fechaHoy[1]+"-"+fechaHoy[2]+".pdf");
			Desktop.getDesktop().open(path);
		}catch (IOException ex) {
			ex.printStackTrace();

		}

	}


}
