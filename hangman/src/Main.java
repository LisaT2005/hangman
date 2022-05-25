import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.text.AttributeSet.ColorAttribute;
import java.util.ArrayList;
import javax.sound.sampled.*;
//hi
//hi! - Lisa
// please ignore the e and "youre a loser" it is not part of the message
//you're a winner to us!
//hello - Anjali cant spell - jasmine
//                   ^ It's true - Anjali
// damn you guys made so much progress 

class Main {

	private static String fileName;
	private static CardLayout cardLayout = new CardLayout();

	public static void main(String[] args) {

		JFrame mainFrame = new JFrame();
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(cardLayout);

		MainScreen mainScreen = new MainScreen();
		JPanel titlePanel = mainScreen.getPanel();

		WinningScreen winningScreen = new WinningScreen();
		JPanel winningPanel = winningScreen.getPanel();

		LosingScreen losingScreen = new LosingScreen();
		JPanel losingPanel = losingScreen.getPanel();

		mainPanel.add(titlePanel, "titlePanel");
		mainPanel.add(winningPanel, "winningPanel");
		mainPanel.add(losingPanel, "losingPanel");
		cardLayout.show(mainPanel, "titlePanel");

		mainFrame.add(mainPanel);
		mainFrame.setSize(1250, 780);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setVisible(true);

		ArrayList<JButton> buttons = mainScreen.getButtonsList();

		for (JButton b : buttons) {
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fileName = b.getText() + ".txt";
					System.out.println(fileName);
					cardLayout.show(mainPanel, "winningPanel");
				}
			});
		}

		winningScreen.getPlayAgainButton().addActionListener(e -> {
			cardLayout.show(mainPanel, "losingPanel");
		});

		losingScreen.getagainButton().addActionListener(e -> {
			cardLayout.show(mainPanel, "titlePanel");
		});

		// LosingScreen l = new LosingScreen();
		// WinningScreen w = new WinningScreen();

		// int w = 700;
		// int h = 550;
		// JFrame f = new JFrame();
		// GraphicsTest dc = new GraphicsTest(w, h, "placeholder");

		// f.setSize(w, h);
		// f.setTitle("Drawing in Java");
		// f.add(dc);
		// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// f.setVisible(true);
	}
}