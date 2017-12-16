import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {
	
	public static void main(String[] args) {
		final Grid grid = new Grid(50, 25, 40);
		final GUI gui = new GUI(grid);
		gui.addMouseListener(new MouseListener() {
			boolean enabled = true;
	    	public void mousePressed(MouseEvent me) { }
	    	public void mouseReleased(MouseEvent me) { }
	    	public void mouseEntered(MouseEvent me) { }
	    	public void mouseExited(MouseEvent me) { }
	    	public void mouseClicked(MouseEvent me) { 
	    		if(enabled) {
	    			int c = grid.squareClicked(me.getX(), me.getY(), me.getButton()); 
	    			if(c==1) {
	    				gui.end("YOU LOST!");
	    				enabled = false;
	    			} else if(c==2) {
	    				gui.end("YOU WON!");
	    				enabled = false;
	    			}
	    			gui.repaint();
	    		}
	    	}
	    });	
		gui.repaint();
	}
	
	static void printMatrix(int[][] m) {
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[0].length; j++) {
				System.out.print(m[i][j]);
			}
			System.out.println();
		}
	}

}
