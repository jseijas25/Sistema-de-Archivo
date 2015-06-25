import vistas.VistaLogin;
import vistas.VistaPrimera;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new VistaLogin();
		//new PestanaExpediente();
		//String home = ;
        //System.out.println(System.getProperty("user.home")+System.getProperty("file.separator")+"Desktop");
		//new VistaPrimera();
	}
}