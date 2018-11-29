package panelFlota;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;

public class PanelFondoFlota extends javax.swing.JPanel {
	
	private static final long serialVersionUID = -5864268171275789898L;
	
	public PanelFondoFlota(){
		this.setSize(736,552);
	}	
	
	public void paintComponent (Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setComposite(AlphaComposite.SrcOver.derive(0.9f));
		Dimension tamanio = getSize();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/fondoflota/guerra2.jpg"));
		g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}