
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.sound.sampled.*;

public class MainScreen {
	
	private GameButton animals;
	private GameButton countries;
	private GameButton food;
	private GameButton clothing;
	private ArrayList<JButton> buttons = new ArrayList<>();
	private JPanel mainPanel = new JPanel();

	public MainScreen() {

		Font titleFont = getFont("WhiskeyBite-Regular.otf");
		titleFont = titleFont.deriveFont(Font.PLAIN, 100);

		Font subtitleFont = titleFont.deriveFont(Font.PLAIN, 75);

		mainPanel.setSize(1250, 780);
		mainPanel.setBackground(Color.white);
		mainPanel.setLayout(null);

		// creating image
		java.net.URL imgUrl = getClass().getResource("mascot.png");
		ImageIcon icon = new ImageIcon(imgUrl);
		JLabel label = new JLabel(icon);

		// creating JPanel for image
		JPanel imagePanel = new JPanel();
		imagePanel.setBackground(Color.white);
		label.setVisible(true);
		imagePanel.setBounds(520, 150, 209, 193); // position: x = 520, y = 0, image is 209x193 pixels
		
		imagePanel.add(label);
		mainPanel.add(imagePanel);
		
		

		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(125, 0, 1000, 200);
		titlePanel.setBackground(Color.white);
		JLabel titleLabel = new JLabel("THE HANGMAN GAME");
		titleLabel.setFont(titleFont);
		titleLabel.setForeground(Color.black);
		titlePanel.add(titleLabel);

		JPanel subtitlePanel = new JPanel();
		subtitlePanel.setBounds(125, 350, 1000, 100);
		subtitlePanel.setBackground(Color.white);
		JLabel subtitleLabel = new JLabel("Choose a category to play with: ");
		subtitleLabel.setFont(subtitleFont);
		subtitleLabel.setForeground(Color.black);
		subtitlePanel.add(subtitleLabel);
		
		mainPanel.add(titlePanel);
		mainPanel.add(subtitlePanel);
		
		//new GameButton(int width, int height, String text, int fontSize, int x, int y)
		
		animals = new GameButton(250, 115, "animals", 75, 355, 475);
		countries = new GameButton(250, 115, "countries", 75, 645, 475);
		food = new GameButton(250, 115, "food", 75, 355, 600);
		clothing = new GameButton(250, 115, "clothing", 75, 645, 600);
		
		buttons.add(animals.getButton());
	    	buttons.add(countries.getButton());
	    	buttons.add(food.getButton());
	    	buttons.add(clothing.getButton());
		
		mainPanel.add(animals.getPanel());
		mainPanel.add(countries.getPanel());
		mainPanel.add(food.getPanel());
		mainPanel.add(clothing.getPanel());

	}
	
	public ArrayList<JButton> getButtonsList(){
		return buttons;
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
