package paquete_temp;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;

	public class Fondo  implements Border{		
		BufferedImage image;

	    public Fondo(BufferedImage image ) {
	        this.image=image;
	        }

	    public boolean isBorderOpaque() {
	    	return true;
	   }

		public Insets getBorderInsets(Component arg0) {
			return new Insets(0,0,0,0);
		}
		  
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawImage(image, 0, 0, width, height, null);
		}
}
