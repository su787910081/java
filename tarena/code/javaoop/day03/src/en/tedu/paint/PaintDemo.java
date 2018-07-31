package en.tedu.paint;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintDemo extends JPanel {
	public static void main(String[] args) {
		JFrame frame = new JFrame("»­Í¼°¸Àý");
		PaintDemo demo = new PaintDemo();
		frame.add(demo);
		
		frame.setSize(400, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public BufferedImage getImage() {
		try {
			BufferedImage image = ImageIO.read(
					this.getClass().getResource(
							"background.png"));
			
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(getImage(), 0, 0, null);
	}
	
//	public void paint(Graphics g) {
//		g.drawImage(1, x, y, observer)
//	}
}
