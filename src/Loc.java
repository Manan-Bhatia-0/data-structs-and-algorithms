public class Loc {
    public final int row;
    public final int col;
    public final int val;
    public boolean isVisted;

    //constructor
    //x is row, y is column
    public Loc(int x, int y, int val) {
	    this.row = x;
	    this.col = y;
	    this.val = val;
	    isVisted = false;
    }

    //returns Loc in the form (row, col)
    public String toString() {
	return "(" + row + ", " + col + ")";
    }
}