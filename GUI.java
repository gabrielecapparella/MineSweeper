import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel{
	
	static final private long serialVersionUID = 0L;
	Grid grid;
	final private JFrame jframe;
	JLabel l, end;
	
	public GUI(Grid g) {
		this.grid = g;
		int w = this.grid.width*20;
		int h = this.grid.height*20+25;
		
		this.setPreferredSize(new Dimension(w, h));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		jframe = new JFrame("MineSweeper");
	    JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.add(this);
		jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setFont(new Font("Serif", Font.PLAIN, 12));
		this.l = new JLabel("Active Mines: "+this.grid.mines);
		this.end = new JLabel("");
		this.add(this.l);
		this.add(this.end);
		this.l.setBounds(5,  5, 150, 20);
		this.end.setBounds(w-105,  5, 100, 20);
		
		jframe.pack();
		jframe.setVisible(true);
	    	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		this.l.setText("Active Mines: "+this.grid.mines);
		//this.l.revalidate();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.grid.width*20, this.grid.height*20+25);
		for(int i=0; i<this.grid.width; i++) {
			for(int j=0; j<this.grid.height; j++) {
				this.drawSquare(g, this.grid.squares[i][j], i*20, j*20+25);
			}
		}
	}
	
	private void drawSquare(Graphics g, Square s, int x, int y) {
		switch(s.state) {
			case 0: //covered
				g.setColor(Color.BLACK);
				g.fillRect(x+1, y+1, 18, 18);
				break;
			case 1: //flagged
				g.setColor(Color.BLACK);
				g.fillRect(x+1, y+1, 18, 18);
				g.setColor(Color.WHITE);
				g.fillRect(x+2, y+2, 16, 16);
				g.setColor(Color.RED);
				g.fillRect(x+5, y+5, 10, 10);
				break;
			case 2: //uncovered
				if(s.value==9) {
					g.setColor(Color.RED);
					g.fillRect(x+1, y+1, 18, 18);
				} else if(s.value!=0){
					g.setColor(Color.BLACK);
					g.fillRect(x+1, y+1, 18, 18);
					g.setColor(Color.WHITE);
					g.fillRect(x+2, y+2, 16, 16);
					g.setColor(Color.BLACK);
					g.drawString(String.valueOf(s.value), x+6, y+14);
				}
				break;
			case 3: //flagged_green
				g.setColor(Color.BLACK);
				g.fillRect(x+1, y+1, 18, 18);
				g.setColor(Color.WHITE);
				g.fillRect(x+2, y+2, 16, 16);
				g.setColor(Color.GREEN);
				g.fillRect(x+5, y+5, 10, 10);
				break;
		}
	}
	
	public void end(String str) {
		this.grid.exposeMines();
		this.end.setText(str);
		this.repaint();
	}

}
