package paquete_temp;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonClass extends JButton implements ActionListener, MouseListener{
	public ButtonClass(String Texto) {
//		this.addActionListener(this);
		ImageIcon normal = new ImageIcon(getClass().getResource("/img_boton/"+Texto+"_base.png"));
		ImageIcon seleccionado = new ImageIcon(getClass().getResource("/img_boton/"+Texto+"_seleccionado.png"));
		ImageIcon presionado = new ImageIcon(getClass().getResource("/img_boton/"+Texto+"_presionado.png"));
		this.setSize(new Dimension(200,30));
//		this.setFont(new Font("Arial", Font.PLAIN, 12));
		this.setIcon(normal);	
		this.setRolloverIcon(seleccionado);
		this.setPressedIcon(presionado);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusable(false);
		this.setRolloverEnabled(true);
//		this.setText(Texto);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
