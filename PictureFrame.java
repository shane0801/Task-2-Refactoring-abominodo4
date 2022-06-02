import java.awt.*;
import java.util.List;

import javax.swing.*;

public class PictureFrame {
  public int[] reroll = null;
  Aardvark master = null;
  private List<Domino> doList;
  private List<Domino> guList;

  class DominoPanel extends JPanel {
    private static final long serialVersionUID = 4190229282411119364L;

    public void drawGrid(Graphics g) {
      for (int are = 0; are < 7; are++) {
        for (int see = 0; see < 8; see++) {
          drawDigitGivenCentre(g, 30 + see * 20, 30 + are * 20, 20,
              master.grid[are][see],Color.BLACK,2);
        }
      }
    }

    public void drawGridLines(Graphics g) {
      g.setColor(Color.LIGHT_GRAY);
      for (int are = 0; are <= 7; are++) {
        g.drawLine(20, 20 + are * 20, 180, 20 + are * 20);
      }
      for (int see = 0; see <= 8; see++) {
        g.drawLine(20 + see * 20, 20, 20 + see * 20, 160);
      }
    }

    public void drawHeadings(Graphics g) {
      for (int are = 0; are < 7; are++) {
        fillDigitGivenCentre(g, 10, 30 + are * 20, 20, are+1);
      }

      for (int see = 0; see < 8; see++) {
        fillDigitGivenCentre(g, 30 + see * 20, 10, 20, see+1);
      }
    }

    public void drawDomino(Graphics g, Domino d) {
      if (d.placed) {
        final int y = Math.min(d.ly, d.hy);
        final int x = Math.min(d.lx, d.hx);
        final int w = Math.abs(d.lx - d.hx) + 1;
        final int h = Math.abs(d.ly - d.hy) + 1;
        final int X_VAL = 20;
        final int Y_VAL = 30;
        g.setColor(Color.WHITE);
        g.fillRect(X_VAL + x * X_VAL, X_VAL + y * X_VAL, w * X_VAL, h * X_VAL);
        g.setColor(Color.RED);
        g.drawRect(X_VAL + x * X_VAL, X_VAL + y * X_VAL, w * X_VAL, h * X_VAL);
        drawDigitGivenCentre(g, Y_VAL + d.hx * X_VAL, Y_VAL + d.hy * X_VAL, X_VAL, d.high,
            Color.BLUE, 2);
        drawDigitGivenCentre(g, Y_VAL + d.lx * X_VAL, Y_VAL + d.ly * X_VAL, X_VAL, d.low,
            Color.BLUE, 2);
      }
    }
    
    void drawDigitGivenCentre(Graphics g, int x, int y, int diameter, int n, Color c, int type) {
      //int radius = diameter / 2;
      //Without Color = 1, With Color = 2
      if(type == 1) {
    	  g.setColor(Color.BLACK);
      }
      else if(type == 2) {
    	  g.setColor(c);
      }
      // g.drawOval(x - radius, y - radius, diameter, diameter);
      FontMetrics fm = g.getFontMetrics();
      String txt = Integer.toString(n);
      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
    }

    void fillDigitGivenCentre(Graphics g, int x, int y, int diameter, int n) {
      final int radius = diameter / 2;
      g.setColor(Color.GREEN);
      g.fillOval(x - radius, y - radius, diameter, diameter);
      g.setColor(Color.BLACK);
      g.drawOval(x - radius, y - radius, diameter, diameter);
      FontMetrics fm = g.getFontMetrics();
      String txt = Integer.toString(n);
      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
    }

    protected void paintComponent(Graphics g) {
      g.setColor(Color.YELLOW);
      g.fillRect(0, 0, getWidth(), getHeight());

      // numbaz(g);
      //
      // if (master!=null && master.orig != null) {
      // drawRoll(g, master.orig);
      // }
      // if (reroll != null) {
      // drawReroll(g, reroll);
      // }
      //
      // drawGrid(g);
      if(master.mode == 1 || master.mode == 0) {
          drawGridLines(g);
          drawHeadings(g);
          drawGrid(g); 
      }
      if (master.mode == 1) {
        drawDominoesGuesses(g,2);
      }
      if (master.mode == 0) {
        drawDominoesGuesses(g,1);
      }
    }

    public Dimension getPreferredSize() {
      return new Dimension(202, 182);
    }
    
    public void drawDominoesGuesses(Graphics g, int type) {
    	// Dominoes = 1, Guesses = 2
    	if (type == 1) {
    		for (Domino d : doList) {
    			dp.drawDomino(g, d);
    		}
    	} else if (type == 2) {
    		for (Domino d : guList) {
    			dp.drawDomino(g, d);
    		}
    	}		
    }
    
  }

  public DominoPanel dp;

  //public void PictureFrame(Aardvark sf) {
  public void pictureFrame(Aardvark sf) {
    master = sf;
    if (dp == null) {
      JFrame f = new JFrame("Abominodo");
      dp = new DominoPanel();
      f.setContentPane(dp);
      f.pack();
      f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      f.setVisible(true);
    }
  }

  //public void reset() {
    // TODO Auto-generated method stub
  //}
  
}
