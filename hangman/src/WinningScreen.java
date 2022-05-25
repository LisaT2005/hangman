import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.sound.sampled.*;

public class WinningScreen {

	private GameButton playAgain;
	private JPanel mainPanel = new JPanel();

	public WinningScreen() {

		Font titleFont = getFont("WhiskeyBite-Regular.otf");
		titleFont = titleFont.deriveFont(Font.PLAIN, 100);

		Font subtitleFont = titleFont.deriveFont(Font.PLAIN, 75);

		mainPanel.setSize(1250, 780);
		mainPanel.setBackground(Color.white);
		mainPanel.setLayout(null);

		// creating image
		java.net.URL imgUrl = getClass().getResource("win.png");
		ImageIcon icon = new ImageIcon(imgUrl);
		JLabel label = new JLabel(icon);

		// creating JPanel for image
		JPanel imagePanel = new JPanel();
		imagePanel.setBackground(Color.white);
		label.setVisible(true);
		imagePanel.setBounds(475, 125, 300, 300); // position: x = 520, y = 0, image is 209x193 pixels

		imagePanel.add(label);
		mainPanel.add(imagePanel);

		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(125, 0, 1000, 200);
		titlePanel.setBackground(Color.white);
		JLabel titleLabel = new JLabel("GAME OVER...");
		titleLabel.setFont(titleFont);
		titleLabel.setForeground(Color.black);
		titlePanel.add(titleLabel);

		JPanel subtitlePanel = new JPanel();
		subtitlePanel.setBounds(125, 450, 1000, 80);
		subtitlePanel.setBackground(Color.white);
		JLabel subtitleLabel = new JLabel("YOU WIN!");
		subtitleLabel.setFont(subtitleFont);
		subtitleLabel.setForeground(Color.black);
		subtitlePanel.add(subtitleLabel);

		mainPanel.add(titlePanel);
		mainPanel.add(subtitlePanel);

		//new GameButton(int width, int height, String text, int fontSize, int x, int y)
		playAgain = new GameButton(300, 115, "play again", 75, 480, 575);

		mainPanel.add(playAgain.getPanel());


	}

	public JButton getPlayAgainButton() {
		return playAgain.getButton();
	}

	public JPanel getPanel() {
		return mainPanel;
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