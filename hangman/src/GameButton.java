import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class GameButton {
	private JButton button;
	private JPanel panel;

	public GameButton(int width, int height, String text, int fontSize, int x, int y) {

		Font font = getFont("WhiskeyBite-Regular.otf");
		font = font.deriveFont(Font.PLAIN, fontSize);

		Color defaultColor = new Color(255, 223, 55);
		Color pressedColor = new Color(255, 191, 0);

		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBounds(x, y, width, height);

		button = new JButton(text);
		button.setFont(font);
		button.setPreferredSize(new Dimension(width, height));
		button.setOpaque(true);
		button.setBorderPainted(false);
		button.setBackground(defaultColor);

		button.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				button.setBackground(pressedColor);
			}
			public void mouseExited(MouseEvent e) {
				button.setBackground(defaultColor);
			}
			public void mouseClicked(MouseEvent e) {

			}
			public void mousePressed(MouseEvent e) {

			}
			public void mouseReleased(MouseEvent e) {

			}
		});

		panel.add(button);

	}

	public JPanel getPanel() {
		return panel;
	}

	public JButton getButton() {
		return button;
	}

	private static Font getFont(String name) {

		final Font SERIF_FONT = new Font("serif", Font.PLAIN, 24);
		Font font = null;
		if (name == null) {
			return SERIF_FONT;
		}

		try {

			String fName = name;
			File fontFile = new File(fName);
			font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

		} catch (Exception ex) {
			System.out.println(name + " not loaded.  Using serif font.");
			font = SERIF_FONT;
		}
		return font;
	}

}
