import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;

public class GraphicsTest extends JComponent {

  private int width;
  private int height;
  private String correct;

  public GraphicsTest(int w, int h, String correct) {
    this.width = w;
    this.height = h;
    this.correct = correct;
  }

  public void paintComponent(Graphics g) { 
    Graphics2D g2d = (Graphics2D) g;
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
    
    String input = "";// enter input from textbox
    int incorrect = 0;
    do {
      if (input.equals("SNAIL") || correct.indexOf(input) >= 0) {
        if (incorrect == 9) {
          smile(g);
          incorrect++;
        }
        // add animation code
       
        // add switch to win screen code
        } else {
          incorrect++;
          addLimb(incorrect, g2d, g);
        }
    } while (incorrect != 10);
    // add animation code
       
    // add switch to lose screen code
  }

  public static void addLimb(int numGuess, Graphics2D g2d, Graphics g) {
    
    switch (numGuess){
      case 1:
        head(g2d);
      case 2:
        body(g2d);
      case 3:
        arm1(g2d);
      case 4:
        arm2(g2d);
      case 5: 
        leg1(g2d);
      case 6:
        leg2(g2d);
      case 7:
        eye1(g2d);
      case 8:
        eye2(g2d);
      case 9:
        nose(g2d);
      case 10:
        frown(g);
    }  
    //if (numGuess == 1) {
    //  head(g2d);
    //} else if (numGuess == 2) {
    // body(g2d);
    //} else if (numGuess == 3) {
    //  arm1(g2d);
    //} else if (numGuess == 4) {
    //  arm2(g2d);
    //} else if (numGuess == 5) {
    //  leg1(g2d);
    //} else if (numGuess == 6) {
    //  leg2(g2d);
    //} else if (numGuess == 7) {
    // eye1(g2d);
    //} else if (numGuess == 8) {
    //  eye2(g2d);
    //} else if (numGuess == 9) {
    //  nose(g2d);
    //} else if (numGuess == 10) {
    //  frown(g);
    //}
  }

  public static void body(Graphics2D g2d) {
    Rectangle2D.Double r = new Rectangle2D.Double(103, 150, 15, 100);
    g2d.setColor(Color.BLACK);
    g2d.fill(r);
  }

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

  public static void eye1(Graphics2D g2d) {
    Ellipse2D.Double eye1 = new Ellipse2D.Double(90, 75, 10, 20);
    g2d.fill(eye1);
  }

  public static void eye2(Graphics2D g2d) {
    Ellipse2D.Double eye2 = new Ellipse2D.Double(120, 75, 10, 20);
    g2d.fill(eye2);
  }

  public static void arm1(Graphics2D g2d) {
    AffineTransform arm1 = new AffineTransform();
    arm1.rotate(0.5);

    Rectangle shape = new Rectangle(165, 80, 10, 80);
    Shape rotated = arm1.createTransformedShape(shape);
    g2d.fill(rotated);
  }

  public static void arm2(Graphics2D g2d) {
    AffineTransform arm2 = new AffineTransform();
    arm2.rotate(-0.5);

    Rectangle shape2 = new Rectangle(20, 190, 10, 80);
    Shape rotated2 = arm2.createTransformedShape(shape2);
    g2d.fill(rotated2);
  }

  public static void leg1(Graphics2D g2d) {
    AffineTransform leg1 = new AffineTransform();
    leg1.rotate(0.5);

    Rectangle leggy = new Rectangle(205, 160, 10, 80);
    Shape legz = leg1.createTransformedShape(leggy);
    g2d.fill(legz);
  }

  public static void leg2(Graphics2D g2d) {
    AffineTransform leg2 = new AffineTransform();
    leg2.rotate(-0.5);

    Rectangle leggy2 = new Rectangle(-20, 260, 10, 80);
    Shape legz2 = leg2.createTransformedShape(leggy2);
    g2d.fill(legz2);
  }

  public static void smile(Graphics g) {
    g.drawArc(68, 35, 85, 85, 225, 90);
  }

  public static void frown(Graphics g) {
    g.drawArc(88, 110, 40, 40, 150, -120);
  }

  public static void nose(Graphics2D g2d) {
    Ellipse2D.Double n = new Ellipse2D.Double(105, 95, 10, 10);
    g2d.fill(n);
  }

}