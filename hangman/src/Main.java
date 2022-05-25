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

	public static void main(String[] args) {

		MainScreen mainScreen = new MainScreen();
		ArrayList<JButton> buttons = mainScreen.getButtonsList();

		for (JButton b : buttons) {
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fileName = b.getText() + ".txt";
					System.out.println(fileName);
				}
			});
		}
		
		LosingScreen l = new LosingScreen();
		WinningScreen w = new WinningScreen();

//    int w = 700;
//    int h = 550;
//    JFrame f = new JFrame();
//    GraphicsTest dc = new GraphicsTest(w, h, "placeholder");
//	    
//    f.setSize(w, h);
//    f.setTitle("Drawing in Java");
//    f.add(dc);
//    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    f.setVisible(true);
	}
}