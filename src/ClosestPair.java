public class ClosestPair {
    private Grid grid;
    private Queue<Loc> queue;

    //constructor: create a new ClosestPair object for grid
    public ClosestPair(Grid grid) {
        this.grid = grid;
        this.queue = new Queue<Loc>();
    }

    //search for the closest location with a value 
    //that is equal to the value at start
    //return null if no match is found
    public Loc search(int x, int y) {
        //To be implemented
        if (grid.getLoc(x, y) == null) {
            return null;
        }
        int val = grid.getLoc(x, y).val;  // value we are looking for
        queue.enqueue(grid.getLoc(x, y)); // enqueue starting Loc at (x, y)
        grid.getLoc(x, y).isVisted = true;
        while (!queue.isEmpty()) {
            int row = x;
            int col = y;
            Loc currentLocObj = null;
            try {
                currentLocObj = queue.dequeue();
                row = currentLocObj.row;
                col = currentLocObj.col;
                // currentLocObj.isVisted = false;
            } catch (EmptyQueueException e) {
                e.printStackTrace();
            }
            if ((row > 0) && (grid.getLoc(row - 1, col) != null) &&
                    (!grid.getLoc(row - 1, col).isVisted)) {
                if ((val == grid.getLoc(row - 1, col).val)) {
                    return grid.getLoc(row - 1, col);
                } else {
                    queue.enqueue(grid.getLoc(row - 1, col));
                    grid.getLoc(row - 1, col).isVisted = true;
                }
            }
            if ((col < grid.size() - 1) && (grid.getLoc(row, col + 1) != null) &&
                    (!grid.getLoc(row, col + 1).isVisted)) {
                if ((val == grid.getLoc(row, col + 1).val)) {
                    return grid.getLoc(row, col + 1);
                } else {
                    queue.enqueue(grid.getLoc(row, col + 1));
                    grid.getLoc(row, col + 1).isVisted = true;
                }

            }
            if ((row < grid.size() - 1) && (grid.getLoc(row + 1, col) != null) &&
                    (!grid.getLoc(row + 1, col).isVisted)) {
                if ((val == grid.getLoc(row + 1, col).val)) {
                    return grid.getLoc(row + 1, col);
                } else {
                    queue.enqueue(grid.getLoc(row + 1, col));
                    grid.getLoc(row + 1, col).isVisted = true;
                }
            }

            if ((col > 0) && (grid.getLoc(row, col - 1) != null) &&
                    (!grid.getLoc(row, col - 1).isVisted)) {
                if ((val == grid.getLoc(row, col - 1).val)) {
                    return grid.getLoc(row, col - 1);
                } else {
                    queue.enqueue(grid.getLoc(row, col - 1));
                    grid.getLoc(row, col - 1).isVisted = true;
                }
            }
        }
        return null;
    }
}
	