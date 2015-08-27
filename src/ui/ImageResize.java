package ui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * A utility class for taking a JLabel and resizing the image within
 *
 * @author Kieran Mckay
 *
 */
public class ImageResize {
	private JLabel label;

	/**
	 * Constructor for ImageResize
	 *
	 * @param label - the label to perform resize on
	 */
	public ImageResize(JLabel label){
		this.label = label;
	}

	/**
	 * Resizes the image within a label on a scale of 1.0 being 100%
	 *
	 * @param resize the size to factor the image in the label by
	 * @return the Jlabel after resizing of the image within
	 */
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

	/**
	 * returns the JLabel held in this class
	 */
	public JLabel getLabel() {
		return label;
	}
	/**
	 * sets a new JLable to perform resizes upon
	 * @param label
	 */
	public void setLabel(JLabel label) {
		this.label = label;
	}
}
