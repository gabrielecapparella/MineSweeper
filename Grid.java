import java.util.Random;

public class Grid {
	
	public int width;
	public int height;
	public int mines;		//number of active mines
	public Square[][] squares;
	
	public Grid(int w, int h, int mines) {
		this.width = w;
		this.height = h;
		this.mines = mines;
		this.squares = new Square[w][h];
		
		this.initializeSquares();
		this.placeMines();
		this.countMines();
	}
	
	private void initializeSquares() {
		for(int i=0; i<this.width; i++) {
			for(int j=0; j<this.height; j++) {
				this.squares[i][j] = new Square();
				//this.squares[i][j].state = 2;
			}
		}
	}
	
	private void placeMines() {
		int x, y;
		Random rand = new Random();	
		
		for(int i=0; i<this.mines; i++) {
			do {
				x = rand.nextInt(this.width);
				y = rand.nextInt(this.height);
			} while(this.squares[x][y].value==9);
			
			this.squares[x][y].value=9;
		}
		
	}
	
	private void countMines() {		
		for(int i=0; i<this.width; i++) {
			for(int j=0; j<this.height; j++) {
				
				if(this.squares[i][j].value!=9) {
					for(int x=-1; x<=1; x++) {
						for(int y=-1; y<=1; y++) {
							if(this.isValid(i+x, j+y) && this.squares[i+x][j+y].value==9) {
								this.squares[i][j].value++;
							}
						}
					}
				}
				
			}
		}
	}
	
	public int squareClicked(int x, int y, int button) {
		x /= 20;
		y = (y-25)/20;
		if(isValid(x, y)) {
			switch(button) {
			case 1: //left
				if(this.squares[x][y].value==0) digAround(x, y);
				if(this.squares[x][y].value==9) return 1;
				this.squares[x][y].state = 2;
				break;
			case 3: //right
				if(this.squares[x][y].state==0) {
					this.squares[x][y].state = 1;
					this.mines--;
					if(this.mines==0) return 2;
				} else if(this.squares[x][y].state==1) {
					this.squares[x][y].state = 0;
					this.mines++;
				}
			}
		}
		return 0;
	}
	
	private void digAround(int x, int y) {
		this.squares[x][y].state = 2;
		if(this.squares[x][y].value==0) {
			for(int i=-1; i<=1; i++) {
				for(int j=-1; j<=1; j++) {
					if(!(i==0 && j==0) && 
							this.isValid(i+x, j+y) && 
							this.squares[i+x][j+y].state==0) {
						digAround(i+x, j+y);
					}
				}
			}
		}
	}
	
	public void exposeMines() {
		Square s;
		for(int i=0; i<this.width; i++) {
			for(int j=0; j<this.height; j++) {
				s = this.squares[i][j];
				if(s.value==9) {
					switch(s.state) {
						case 0:
							s.state = 2;
							break;
						case 1:
							s.state = 3;
							break;
					}
				}
			}
		}
	}
	
	private boolean isValid(int x, int y) {
		return (x>=0 && x<this.width && y>=0 && y<this.height);
	}

}
