package client.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class TopPanel extends JPanel {
	private ImageIcon icon = new ImageIcon("./logo.GIF");
	private Image img = icon.getImage(); // 이미지 객체

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		g.drawImage(img, 0, 0, d.width, d.height, null);
	}
}
