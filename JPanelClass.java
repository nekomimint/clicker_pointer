package paquete_temp;

import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class JPanelClass extends JPanel {
 public JPanelClass(LayoutManager Layout_Panel ,String Fondo_Panel) {
	 this.setLayout(Layout_Panel);
	 try {
		 File image=new File (getClass().getResource(""+Fondo_Panel).getFile());
		 BufferedImage image_buffered= ImageIO.read(image);
		 this.setBorder(new Fondo(image_buffered));
	 }catch (Exception e) {
		System.err.println("Ha habido un error al cargar la imagen del panel: \n"+e.getMessage());
	}
 }
}
