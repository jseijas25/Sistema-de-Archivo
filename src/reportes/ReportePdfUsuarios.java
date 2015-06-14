package reportes;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import com.itextpdf.text.pdf.PdfWriter;

import dataBase.UsuarioDAO;
import modelos.Usuario;


public class ReportePdfUsuarios {
	public static final String resultado = "../Sistema/ReporteUsuario.pdf";
	private String ruta="../Sistema/imagenesSistema/logo/";
	 public static final String titulo= "REPORTE DE USUARIOS";
	
	 
	 public static final String IMAGE = "IMAGE";
	 public static Paragraph detalle;
	 public static final String TABLE = "TABLE";
	 public static final String CHUNK = "CHUNK";
	 public static final int nLineas=0;
	
	 
	 
	//Textos
	 public static final String SEPARADOR = "______________________________________";
	 
	 public ReportePdfUsuarios() {
		 Date fechaActual = new Date();

		 SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		 String hoy = formato.format(fechaActual);
		 String fecha = null;

		 String[] fechaHoy = hoy.split("/");
		 int anos = Integer.parseInt(fechaHoy[2]);
		 int mes = Integer.parseInt(fechaHoy[1]);
		 int dia = Integer.parseInt(fechaHoy[0]);

		 fecha = "Fecha: " + fechaHoy[0] + "/" + fechaHoy[1] + "/" + fechaHoy[2];

		 try {
			 Document document = new Document(PageSize.LETTER, 50, 50, 50, 50);
			 //Al documento se le puede a�adir cierta metaInformacion
			 document.addAuthor("POSTGARDO");
			 document.addTitle("EJEMPLO1");
			 PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(resultado));
			 writer.setInitialLeading(16);
			 Rectangle rct = new Rectangle(36, 54, 559, 788);
			 writer.setBoxSize("art", rct);
			 document.open();
			 //-----------> logo <-------------------------


			 Image fondo = Image.getInstance(ruta + "usuarios.png");
			 fondo.scaleToFit(230, 230);
			 fondo.setAlignment(Chunk.ALIGN_LEFT);
			 //  fondo.scaleToFit(50,50);//escala de la imagen
			 //fondo.setAlignment(Image.MIDDLE | Image.UNDERLYING);
			 document.add(fondo);
			 //-----------> fecha<-------------------------
			 detalle = new Paragraph(fecha, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.BLACK));

			 document.add(detalle);

			 Date tiempo = new Date();
			 String xtiempo = "Hora:" + tiempo.getHours() + ":" + tiempo.getMinutes();
			 detalle = new Paragraph(xtiempo, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.BLACK));
			 document.add(detalle);
			 //-----------> titulo<-------------------------
			 detalle = new Paragraph(titulo, FontFactory.getFont(FontFactory.HELVETICA, 14, Font.UNDERLINE, BaseColor.BLACK));

			 detalle.setAlignment(Element.ALIGN_CENTER);
			 //Lo a�adimos al documento
			 document.add(detalle);

			 document.add(Chunk.NEWLINE);
			 //**************************************************************
			 //Ejemplos de TABLE


			 //A�adir tabla 3 columnas
			 PdfPTable table = new PdfPTable(5);
			 //A�adir CABECERA
			 PdfPCell cell = new PdfPCell(new Phrase("CEDULA", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK)));
			 cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 table.addCell(cell);

			 cell = new PdfPCell(new Phrase("NOMBRES", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK)));
			 cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 table.addCell(cell);

			 cell = new PdfPCell(new Phrase("APELLIDOS", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK)));
			 cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 table.addCell(cell);

			 cell = new PdfPCell(new Phrase("LOGIN", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK)));
			 cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 table.addCell(cell);

			 cell = new PdfPCell(new Phrase("NIVEL", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, BaseColor.BLACK)));
			 cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 table.addCell(cell);


			 ArrayList<Usuario> usuarios = new ArrayList<>();
			 UsuarioDAO.findAll(usuarios);
             for (Usuario usuario: usuarios) {
                 if (usuario.isActive()) {

                     cell = new PdfPCell(new Phrase(String.valueOf(usuario.getId()), FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK)));
                     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                     // cell.setBorder(PdfPCell.NO_BORDER);
                     table.addCell(cell);


                     cell = new PdfPCell(new Phrase(usuario.getNombre(), FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK)));
                     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                     //cell.setBorder(PdfPCell.NO_BORDER);
                     table.addCell(cell);

                     cell = new PdfPCell(new Phrase(String.valueOf(usuario.getNivel()), FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK)));
                     cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                     //cell.setBorder(PdfPCell.NO_BORDER);
                     table.addCell(cell);


                 }
             }

			 document.add(table);


			 //FIN Ejemplos de TABLE
			 //numero de p�gina

			 Rectangle rect = writer.getBoxSize("art");
			 //numero de pagina
			 ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(String.format("pagina %d", writer.getPageNumber())),
					 (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);

			 document.close();

		 } catch (Exception ex) {

		 }

		 try {
			 //abrirlo por pantalla
			 File path = new File("ReporteUsuario.pdf");
			 Desktop.getDesktop().open(path);
		 } catch (IOException ex) {
			 ex.printStackTrace();

		 }


	 }
}
