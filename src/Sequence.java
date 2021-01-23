
public class Sequence {
    private Grid grid;
    private Stack<Loc> path;//stores the sequence path

    //constructor: create a new Sequence for the 
    //specified grid
    public Sequence(Grid grid) {
        this.grid = grid;
        this.path = new Stack<Loc>();
    }

    //resets the grid and the path
    public void reset(Grid grid) {
        this.grid = grid;
        this.path = new Stack<Loc>();
    }

    //(i, j)  is the starting location
    //val is the value that should end the sequence
    public void getSeq(int i, int j, int val) throws EmptyStackException {
        //To be implemented
        //Loc[] visitedLocations
        Loc currentLocObj = grid.getLoc(i, j);
        //int found = 0;
        if (currentLocObj == null) {
            return;
        }

        int currentVal = currentLocObj.val;
        //System.out.printf("val= %d\n", val);


        path.push(currentLocObj);
        System.out.printf("Pushed: %s\n", currentLocObj.toString());
        currentLocObj.isVisted = true;
        
        if (currentVal == val) {
            return;
        }
        if ((i > 0) && ((currentLocObj.val + 1) == grid.getLoc(i - 1, j).val)
                && (!grid.getLoc(i - 1, j).isVisted)) {
            getSeq(i - 1, j, val);
            if (path.peek().val == val) {
                return;
            }

        }
        if ((j < grid.size() - 1) && ((currentLocObj.val + 1) == grid.getLoc(i, j + 1).val) &&
                (!grid.getLoc(i, j+1).isVisted)) {
            getSeq(i, j + 1, val);
            if (path.peek().val == val) {
                return;
            }
        }

        if ((i < grid.size() - 1) && ((currentLocObj.val + 1) == grid.getLoc(i + 1, j).val) &&
                (!grid.getLoc(i + 1, j).isVisted)) {
            getSeq(i + 1, j, val);
            if (path.peek().val == val) {
                return;
            }

        }
        if ((j > 0) && ((currentLocObj.val + 1) == grid.getLoc(i, j - 1).val) &&
                (!grid.getLoc(i, j-1).isVisted)) {
                getSeq(i, j - 1, val);
            if (path.peek().val == val) {
                return;
            }
        }

        path.pop();
        System.out.printf("Popped: %s\n", currentLocObj.toString());
        currentLocObj.isVisted = false;


    }

    //return a String representation of the sequence
    //starting at the starting location and listing
    //all locations in the sequence in order
    public String toString() {
        //To be implemented
        //Loc[] reversePath = new Loc[path.size()];
        String finalPath = "";
        Stack<Loc> tempStack = path.getCopy();
        while (tempStack.getFirst() != null) {
            try {
                Loc loc = tempStack.pop();
                finalPath += (loc.toString());
               // System.out.println(finalPath);

            } catch (EmptyStackException e) {
                e.printStackTrace();
            }
        }
       // finalPath.reverse();
        //System.out.println(finalPath);
        return finalPath;
    }
}