package ui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageResize {
	private JLabel label;

	public ImageResize(JLabel label){
		this.label = label;
	}

	public JLabel resize(double resize){
		if(resize == 0){return label;}

		ImageIcon newIcon = (ImageIcon) label.getIcon();
		Image newImage = newIcon.getImage();
		double scale = 1 / resize;
		int width = newIcon.getIconWidth();
		int height = newIcon.getIconHeight();

		newIcon = new ImageIcon(newImage.getScaledInstance((int)(width/scale), (int)(height/scale), 0));

		label.setIcon(newIcon);

		return label;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}
}
