import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Grid {
    private Loc[][] grid;

    //constructor: creates a new Grid of size
    //n X n and populates it with random integers
    //in the range [min, max)
    public Grid(int n, int min, int max) {
	grid = new Loc[n][n];
	Random gen = new Random();
	for(int i = 0; i < n; i++)
	    for(int j = 0; j < n; j++)
		grid[i][j] = new Loc(i, j, gen.nextInt(max-min) + min);
    }

    //constructor: creates a new Grid based on 
    //an input file
    public Grid(String filename) {
	BufferedReader reader;
	try {
	    reader = new BufferedReader(new FileReader(filename));
	    String line = reader.readLine();
	    if(line != null) {
		int n = Integer.parseInt(line);
		grid = new Loc[n][n];
		line = reader.readLine();
		int r = 0;
		while(line != null) {
		    String[] str = line.split(" ");
		    if(str.length < n)
			break;
		    for(int c = 0; c < n; c++)
			grid[r][c] = new Loc(r, c, Integer.parseInt(str[c]));
		    r++;
		    line = reader.readLine();
		}
	    } 
	}catch (Exception e) {
		e.printStackTrace();
	}  
    }

    //creates a String representation of the Grid
    public String toString() {
	String str = "";
	for(int r = 0; r < grid.length; r++) {
	    for(int c = 0; c < grid[0].length; c++) {
		str += grid[r][c].val + " ";
	    }
	    str += "\n";
	}
	return str;
    }

    //returns the size of the Grid (i.e. n for 
    //an n X n Grid--all Grids are square!    
    public int size() {
	return grid.length;
    }

    //returns the location at (i, j) or null if (i, j)
    //is outside the grid
    public Loc getLoc(int i, int j) {
	if(i >= 0 && i < grid.length && j >= 0 && j < grid[0].length)
	    return grid[i][j];
	return null;
    }

    //add a sequence to the grid starting at (x, y) and going for 
    //length len as long as it is achieved before the limit of tries is reached
    private void addSeq(int x, int y, int len) {
	Loc loc = this.getLoc(x, y);
	if(loc == null)
	    return;
	len = Math.min(len, (int)Math.pow(this.size(), 2));
	int val = loc.val;
	boolean[][] marked = new boolean[this.size()][this.size()];
	marked[x][y] = true;
	Random gen = new Random();
	int count = 1;
	int lim = 0;
	while(count < len && lim < 4*len) {
	    int dir = gen.nextInt(4);
	    Loc next = null;
	    switch(dir) {
	    case 0:
		next = this.setNewLoc(x - 1, y, val, marked);
		if(next != null) {
		    x--;
		    val++;
		    loc = next;
		    count++;
		}
	    case 1:
		next = this.setNewLoc(x, y + 1, val, marked);
		if(next != null) {
		    y++;
		    val++;
		    loc = next;
		    count++;
		}
	    case 2: 
		next = this.setNewLoc(x + 1, y, val, marked);
		if(next != null) {
		    x++;
		    val++;
		    loc = next;
		    count++;
		}
	    case 3:
		next = this.setNewLoc(x, y - 1, val, marked);
		if(next != null) {
		    y--;
		    val++;
		    loc = next;
		    count++;
		}
	    }
	    lim++;
	}
    }
    
    //helper function for setting a sequence
    private Loc setNewLoc (int x, int y, int val, boolean[][] marked) {
	Loc loc = this.getLoc(x, y);
	Loc ret = null;
	if(loc != null && !marked[x][y]) {
	    ret = new Loc(x, y, val + 1);
	    grid[x][y] = ret;
	    marked[x][y] = true;
	}
	return ret;
    }
}
    
	