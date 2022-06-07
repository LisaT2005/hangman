import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class Game {

	private static String fileName;
	private static CardLayout cardLayout = new CardLayout();
	private static JPanel mainPanel;
	private static JPanel titlePanel;
	private static JPanel winningPanel;
	private static JPanel losingPanel;
	private static JLabel winningSubtitleLabel;
	private static JLabel losingSubtitleLabel;
	private static JLabel winningImageLabel;

	public static void main(String[] args) {
		new Game();
	}

	public Game() {
		JFrame mainFrame = new JFrame();
		mainPanel = new JPanel();
		mainPanel.setLayout(cardLayout);

		titlePanel = createTitleScreen();
		winningPanel = createWinningScreen();
		losingPanel = createLosingScreen();

		mainPanel.add(titlePanel, "titlePanel");
		mainPanel.add(winningPanel, "winningPanel");
		mainPanel.add(losingPanel, "losingPanel");
		cardLayout.show(mainPanel, "titlePanel");

		mainFrame.add(mainPanel);
		mainFrame.setSize(1250, 780);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setVisible(true);
	}

	public static void showWinningScreen(boolean nineAttempts, boolean snail) {
		updateWinningText(nineAttempts, snail);
		cardLayout.show(mainPanel, "winningPanel");
	}

	public static void showLosingScreen(String correct) {
		updateLosingText(correct);
		cardLayout.show(mainPanel, "losingPanel");
	}

	public static void showTitleScreen() {
		cardLayout.show(mainPanel, "titlePanel");
	}

	public static void updateWinningText(boolean nineAttempts, boolean snail) {
		if (nineAttempts) {
			winningSubtitleLabel.setText("WIN ON LAST ATTEMPT!");
		} else {
			winningSubtitleLabel.setText("WIN WITH TIME TO SPARE!");
		}
		
		if (snail) {
			ImageIcon i = new ImageIcon(Game.class.getResource("snail.png"));
			winningImageLabel.setIcon(i);
			winningSubtitleLabel.setText("RULER OF THE SNAILS!");
		}
	}
	
	
	public static void updateLosingText(String correct) {
		losingSubtitleLabel.setText("THE WORD WAS: " + correct);
	}

	public JPanel createWinningScreen() {

		//getting font
		Font titleFont = getFont("WhiskeyBite-Regular.otf");
		titleFont = titleFont.deriveFont(Font.PLAIN, 100);
		Font subtitleFont = titleFont.deriveFont(Font.PLAIN, 75);

		//main panel
		JPanel panel = new JPanel();
		panel.setSize(1250, 780);
		panel.setBackground(Color.white);
		panel.setLayout(null);

		// creating image
		java.net.URL imgUrl = getClass().getResource("win.png");
		ImageIcon icon = new ImageIcon(imgUrl);
		winningImageLabel = new JLabel(icon);

		// creating JPanel for imageLabel
		JPanel imagePanel = new JPanel();
		imagePanel.setBackground(Color.white);
		winningImageLabel.setVisible(true);
		imagePanel.setBounds(475, 125, 300, 300); 
		// position: x = 475, y = 125, image is 300x300 pixels

		imagePanel.add(winningImageLabel);
		panel.add(imagePanel);

		//title text
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(125, 0, 1000, 200);
		titlePanel.setBackground(Color.white);
		JLabel titleLabel = new JLabel("YOU WIN!");
		titleLabel.setFont(titleFont);
		titleLabel.setForeground(Color.black);
		titlePanel.add(titleLabel);

		//subtitle text
		JPanel subtitlePanel = new JPanel();
		subtitlePanel.setBounds(125, 450, 1000, 80);
		subtitlePanel.setBackground(Color.white);
		winningSubtitleLabel = new JLabel("WIN!");
		winningSubtitleLabel.setFont(subtitleFont);
		winningSubtitleLabel.setForeground(Color.black);
		subtitlePanel.add(winningSubtitleLabel);

		panel.add(titlePanel);
		panel.add(subtitlePanel);

		// new GameButton(int width, int height, String text, int fontSize, int x, int y)

		GameButton playAgain = new GameButton(300, 115, "play again", 75, 480, 575);
		panel.add(playAgain.getPanel());

		playAgain.getButton().addActionListener(e -> {
			winningImageLabel.setIcon(icon);
			showTitleScreen();
		});

		return panel;

	}

	public JPanel createLosingScreen() {

		//getting font
		Font titleFont = getFont("WhiskeyBite-Regular.otf");
		titleFont = titleFont.deriveFont(Font.PLAIN, 100);
		Font subtitleFont = titleFont.deriveFont(Font.PLAIN, 75);

		//main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setSize(1250, 780);
		mainPanel.setBackground(Color.white);
		mainPanel.setLayout(null);

		// creating image
		java.net.URL imgUrl = getClass().getResource("lose.png");
		ImageIcon icon = new ImageIcon(imgUrl);
		JLabel imageLabel = new JLabel(icon);

		// creating JPanel for imageLabel
		JPanel imagePanel = new JPanel();
		imagePanel.setBackground(Color.white);
		imageLabel.setVisible(true);
		imagePanel.setBounds(475, 125, 300, 300); // position: x = 520, y = 0, image is 209x193 pixels

		imagePanel.add(imageLabel);
		mainPanel.add(imagePanel);

		//title text
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(125, 0, 1000, 200);
		titlePanel.setBackground(Color.white);
		JLabel titleLabel = new JLabel("YOU LOSE!");
		titleLabel.setFont(titleFont);
		titleLabel.setForeground(Color.black);
		titlePanel.add(titleLabel);

		//subtitle text
		JPanel subtitlePanel = new JPanel();
		subtitlePanel.setBounds(125, 450, 1000, 80);
		subtitlePanel.setBackground(Color.white);
		losingSubtitleLabel = new JLabel("THE WORD WAS: ");
		losingSubtitleLabel.setFont(subtitleFont);
		losingSubtitleLabel.setForeground(Color.black);
		subtitlePanel.add(losingSubtitleLabel);

		mainPanel.add(titlePanel);
		mainPanel.add(subtitlePanel);

		// new GameButton(int width, int height, String text, int fontSize, int x, int
		// y);
		GameButton again = new GameButton(300, 115, "play again", 75, 480, 575);

		mainPanel.add(again.getPanel());

		again.getButton().addActionListener(e -> {
			showTitleScreen();
		});

		return mainPanel;

	}

	public JPanel createTitleScreen() {

		//getting font
		Font titleFont = getFont("WhiskeyBite-Regular.otf");
		titleFont = titleFont.deriveFont(Font.PLAIN, 100);
		Font subtitleFont = titleFont.deriveFont(Font.PLAIN, 75);

		//main panel
		JPanel panel = new JPanel();
		panel.setSize(1250, 780);
		panel.setBackground(Color.white);
		panel.setLayout(null);

		// creating image
		java.net.URL imgUrl = getClass().getResource("mascot.png");
		ImageIcon icon = new ImageIcon(imgUrl);
		JLabel imageLabel = new JLabel(icon);

		// creating JPanel for imageLabel
		JPanel imagePanel = new JPanel();
		imagePanel.setBackground(Color.white);
		imageLabel.setVisible(true);
		imagePanel.setBounds(520, 150, 209, 193); 
		// position: x = 520, y = 150, image is 209x193 pixels

		imagePanel.add(imageLabel);
		panel.add(imagePanel);

		//title text
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(125, 0, 1000, 200);
		titlePanel.setBackground(Color.white);
		JLabel titleLabel = new JLabel("THE HANGMAN GAME");
		titleLabel.setFont(titleFont);
		titleLabel.setForeground(Color.black);
		titlePanel.add(titleLabel);

		//subtitle text
		JPanel subtitlePanel = new JPanel();
		subtitlePanel.setBounds(125, 350, 1000, 100);
		subtitlePanel.setBackground(Color.white);
		JLabel subtitleLabel = new JLabel("Choose a category to play with: ");
		subtitleLabel.setFont(subtitleFont);
		subtitleLabel.setForeground(Color.black);
		subtitlePanel.add(subtitleLabel);

		panel.add(titlePanel);
		panel.add(subtitlePanel);

		// new GameButton(int width, int height, String text, int fontSize, int x, int
		// y)

		GameButton animals = new GameButton(250, 115, "animals", 75, 355, 475);
		GameButton countries = new GameButton(250, 115, "countries", 75, 645, 475);
		GameButton food = new GameButton(250, 115, "food", 75, 355, 600);
		GameButton clothing = new GameButton(250, 115, "clothing", 75, 645, 600);

		//creating ArrayList of buttons and adding ActionListneers to all of them
		ArrayList<JButton> buttons = new ArrayList<>();
		buttons.add(animals.getButton());
		buttons.add(countries.getButton());
		buttons.add(food.getButton());
		buttons.add(clothing.getButton());

		panel.add(animals.getPanel());
		panel.add(countries.getPanel());
		panel.add(food.getPanel());
		panel.add(clothing.getPanel());

		for (JButton b : buttons) {
			b.addActionListener(e ->{
					fileName = b.getText() + ".txt";
					Hangman hangman = new Hangman(fileName);
					JPanel hangmanPanel = hangman.getPanel();
					mainPanel.add(hangmanPanel, "hangmanPanel");
					cardLayout.show(mainPanel, "hangmanPanel");
				});
		}

		return panel;

	}

	public static Font getFont(String name) {

		final Font SERIF_FONT = new Font("serif", Font.PLAIN, 24);
		Font font;
		if (name == null) {
			return SERIF_FONT;
		}

		try {
			File fontFile = new File(name);
			font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

		} catch (Exception ex) {
			System.out.println(name + " not loaded.  Using serif font.");
			font = SERIF_FONT;
		}
		return font;
	}
}
