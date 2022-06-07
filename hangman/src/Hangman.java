import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import java.io.*;

public class Hangman extends JPanel {
	
	TextField text;
	JLabel guessesLabel;
	JLabel lettersLeftLabel;
	String guess;
	static ArrayList<String> words;
	int wrong = 0;
	String letters = "";
	JPanel mainPanel;
	private int width;
	private int height;
	Graphics2D g2d;

	public Hangman(String fileName) {

		Font font = getFont("WhiskeyBite-Regular.otf");
		font = font.deriveFont(Font.TRUETYPE_FONT, 75);
		
		words = importFile(fileName);
		String correct = words.get((int) (Math.random() * words.size())); 

		String dashes = "";
		for (int i = 0; i < correct.length(); i++) {
			dashes += "_";

		}
		
		GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);

		// object "this" is the graphics JPanel
		this.width = 1250;
		this.height = 780;
		this.setBounds(0, 100, 1250, 780);

		//guesses label _ _ _ _ _
		guessesLabel = new JLabel(dashes);
		guessesLabel.setVerticalAlignment(JLabel.TOP);
		guessesLabel.setFont(font);
		guessesLabel.setBounds(500, 50, 750, 100);

		//main panel
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		mainPanel.setSize(1250, 780);
		mainPanel.setLayout(null);
		mainPanel.setVisible(true);
	
		//text field (user enters text here)
		text = new TextField(20);
		text.setFont(font);
		text.setBounds(500, 600, 700, 100);

		//letters left 
		lettersLeftLabel = new JLabel("Letters used: ");
		lettersLeftLabel.setFont(font);
		lettersLeftLabel.setVerticalAlignment(JLabel.TOP);
		lettersLeftLabel.setBounds(500, 150, 500, 500);

		mainPanel.add(guessesLabel);
		mainPanel.add(text);
		mainPanel.add(lettersLeftLabel);
		mainPanel.add(this);
		
		// actionListener code (main game)
		text.addActionListener(e -> {
			String label = guessesLabel.getText();
			String input = text.getText().trim().toUpperCase();
			
			
			//checks if input contains valid characters only
			if (input.length() == 0) {
				text.setText("");
				return;
			}
			for (int i = 0; i<input.length(); i++) {
				if (!Character.isLetter(input.charAt(i))) {
					text.setText("");
					return;
				}
			}
			
			
			if (input.length() == 1) {
				
				if (letters.indexOf(input) > -1) { //checks if letter has already been guessed
					text.setText("");
					
				} else { //updates guesses label _ _ _ _ _
					if (correct.indexOf(input) == -1) {
						wrong++;
					}
					
					for (int i = 0; i < correct.length(); i++) {
						if (input.equals(correct.substring(i, i + 1))) {
							label = label.substring(0, i) + input + label.substring(i + 1);
						}
					}
					
					letters += input + " ";
				}
			} else { //input length > 1
				if (correct.equals(input)) {
					label = input;
				} else {
					wrong++;
				}
			}
			
			guessesLabel.setText(label);
			
			if (label.equals(correct) && wrong == 9) {
				wrong = 11;
				this.repaint(new Rectangle(375, 450));
				
				//after delay of 1 second displayWinningScreen(ninthAttempt true, snail false)
				text.setEditable(false);
				Timer t = new Timer(1000, f ->{
					Game.showWinningScreen(true, false);
				});
				t.setRepeats(false);
				t.start();
			
			} else if (label.equals(correct)) {
				
				//after delay of 1 second displayWinningScreen(ninthAttempt false, snail false)
				text.setEditable(false);
				Timer t = new Timer(1000, f ->{
					Game.showWinningScreen(false, false);
				});
				t.setRepeats(false);
				t.start();
				
			} else if (input.equals("SNAIL")){
				
				//displayWinningScreen(ninthAttempt false, snail true)
				text.setEditable(false);
				Game.showWinningScreen(false, true);
				
			} else if (wrong == 10) {
			
				//after delay of 1 second display losing screen
				text.setEditable(false);
				Timer t = new Timer(1000, f ->{
					
					Game.showLosingScreen(correct);
				});
				t.setRepeats(false);
				t.start();
				
			}
			
			//updates graphics
			this.repaint(new Rectangle(375, 450));
			
			//adds line break to lettersLeft once the line reaches 21 characters
			if (lettersLeftLabel.getText().length() % 21 == 0) {
				letters  += "<br/>";
			}
			lettersLeftLabel.setText("<html>Letters used: <br/>" + letters + "<html>");
			text.setText("");
			
		});

	}

	public void paintComponent(Graphics g) {
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);

		// background
		Rectangle2D.Double b = new Rectangle2D.Double(0, 0, width, height);
		g2d.setColor(Color.WHITE);
		g2d.fill(b);

		// stand
		Rectangle2D.Double h = new Rectangle2D.Double(251, 13, 20, 350);
		g2d.setColor(Color.BLACK);
		g2d.fill(h);

		Rectangle2D.Double h1 = new Rectangle2D.Double(105, 23, 10, 28);
		g2d.setColor(Color.BLACK);
		g2d.fill(h1);

		AffineTransform line = new AffineTransform();
		line.rotate(1.56);

		Rectangle top = new Rectangle(15, -270, 10, 165);
		Shape topLine = line.createTransformedShape(top);
		g2d.fill(topLine);

		AffineTransform line2 = new AffineTransform();
		line2.rotate(1.56);

		Rectangle bottom = new Rectangle(360, -300, 10, 165);
		Shape bottomline = line2.createTransformedShape(bottom);
		g2d.fill(bottomline);

		if (wrong <= 11) {
			addLimb(wrong, g2d);
		}
	}

	public static void addLimb(int numGuess, Graphics2D g2d) {

		switch (numGuess) {
		case 1:
			head(g2d);
			break;
		case 2:
			body(g2d);
			break;
		case 3:
			arm1(g2d);
			break;
		case 4:
			arm2(g2d);
			break;
		case 5:
			leg1(g2d);
			break;
		case 6:
			leg2(g2d);
			break;
		case 7:
			eye1(g2d);
			break;
		case 8:
			eye2(g2d);
			break;
		case 9:
			nose(g2d);
			break;
		case 10:
			//lose on last attempt
			frown(g2d);
			break;
		case 11:
			//win on last attempt
			smile(g2d);
			break;
		}
	}

	public JPanel getPanel() {
		return mainPanel;
	}

	public static ArrayList<String> importFile(String fileName) {
		ArrayList<String> words = new ArrayList<>();
		try {
			File myObj = new File(fileName);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine().toUpperCase().trim();
				words.add(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return words;
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
	
	//hangman body part methods

	public static void head(Graphics2D g2d) {
		// outline head
		Ellipse2D.Double c = new Ellipse2D.Double(60, 50, 100, 100);
		g2d.fill(c);
		// head hole
		Ellipse2D.Double c1 = new Ellipse2D.Double(70, 60, 80, 80);
		g2d.setColor(Color.WHITE);
		g2d.fill(c1);
		g2d.setColor(Color.BLACK);
	}
	
	public static void body(Graphics2D g2d) {

		head(g2d);
		Rectangle2D.Double r = new Rectangle2D.Double(103, 150, 15, 100);
		g2d.setColor(Color.BLACK);
		g2d.fill(r);
	}
	
	public static void arm1(Graphics2D g2d) {
		body(g2d);
		AffineTransform arm1 = new AffineTransform();
		arm1.rotate(0.5);

		Rectangle shape = new Rectangle(165, 80, 10, 80);
		Shape rotated = arm1.createTransformedShape(shape);
		g2d.fill(rotated);
	}

	public static void arm2(Graphics2D g2d) {
		arm1(g2d);
		AffineTransform arm2 = new AffineTransform();
		arm2.rotate(-0.5);

		Rectangle shape2 = new Rectangle(20, 190, 10, 80);
		Shape rotated2 = arm2.createTransformedShape(shape2);
		g2d.fill(rotated2);
	}

	public static void leg1(Graphics2D g2d) {
		arm2(g2d);
		AffineTransform leg1 = new AffineTransform();
		leg1.rotate(0.5);

		Rectangle leggy = new Rectangle(205, 160, 10, 80);
		Shape legz = leg1.createTransformedShape(leggy);
		g2d.fill(legz);
	}

	public static void leg2(Graphics2D g2d) {
		leg1(g2d);
		AffineTransform leg2 = new AffineTransform();
		leg2.rotate(-0.5);

		Rectangle leggy2 = new Rectangle(-20, 260, 10, 80);
		Shape legz2 = leg2.createTransformedShape(leggy2);
		g2d.fill(legz2);
	}

	public static void eye2(Graphics2D g2d) {
		eye1(g2d);
		Ellipse2D.Double eye2 = new Ellipse2D.Double(120, 75, 15, 25);
		g2d.fill(eye2);
	}
	
	public static void nose(Graphics2D g2d) {
		eye2(g2d);
		Ellipse2D.Double n = new Ellipse2D.Double(105, 95, 10, 10);
		g2d.fill(n);
	}
	
	public static void nose(Graphics2D g2d) {
		eye2(g2d);
		Ellipse2D.Double n = new Ellipse2D.Double(105, 95, 10, 10);
		g2d.fill(n);
	}
	
	public static void frown(Graphics g) {
		nose((Graphics2D) g);
		g.drawArc(88, 110, 40, 40, 150, -120);
	}

	public static void smile(Graphics g) {
		nose((Graphics2D) g);
		g.drawArc(68, 35, 85, 85, 225, 90);
	}

}
