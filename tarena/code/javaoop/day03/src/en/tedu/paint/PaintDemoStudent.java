package en.tedu.paint;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintDemoStudent extends JPanel {
	public static void main(String[] args) {
		PaintDemoStudent pds = new PaintDemoStudent();
		
		JFrame frame = new JFrame("°¸Àý»­Í¼Ñ§Ï°");
		
		frame.add(pds);
		frame.setSize(400, 700);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private BufferedImage getImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(this.getClass().getResource(
					"background.png"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return image;
	}
	
	@Override
	public void paint(Graphics g) {
//		super.paint(g);
		g.drawImage(getImage(), 0, 0, null);
	}
}
