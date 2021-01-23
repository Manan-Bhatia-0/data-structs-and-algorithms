public class SortGrid
{
    private static int compares = 0;
    private static int[][] grid;

    // PUBLIC METHODS

    public static void mergeSort(int start, int end, int col)
    {
        if (start < end) {

            int mid = (end + start) / 2;
            mergeSort(start, mid, col);
            mergeSort((mid + 1), end, col);

            int newStart = mid + 1;

            while ((start <= mid) && (newStart <= end)) {

                if (lessThan((start / col), (start % col), (newStart / col), (newStart % col))) {
                    start++;
                }

                else {
                    int value = grid[newStart / col][newStart % col];
                    int index = newStart;

                    while (index > start) {
                        grid[index / col][index % col] = grid[(index - 1) / col][(index - 1) % col];
                        index--;
                    }
                    grid[start / col][start % col] = value;

                    start++;
                    mid++;
                    newStart++;
                }
            }
        }
    }

    public static int sort(int[][] thisGrid)
    {
        compares = 0;
        //TO BE IMPLEMENTED

        int row = thisGrid.length;
        int col = thisGrid[0].length;
        int size = row * col;
        int start = 0;
        int end = size - 1;
        //System.out.println(size);
        grid = thisGrid;
        mergeSort(start, end, col);
        return compares;
    }

    //  HELPER METHODS
    // returns true if value at (r1, c1) is less
    // than value at (r2, c2) and false otherwise;
    // counts as 1 compare
    private static boolean lessThan(int r1, int c1, int r2, int c2)
    {
        compares++;

        if(grid[r1][c1] < grid[r2][c2])
            return true;

        return false;
    }

    // returns true if value at (r1, c1) is greater than
    // value at (r2, c2) and false otherwise;
    // counts as 1 compare
    private static boolean greaterThan(int r1, int c1, int r2, int c2)
    {
        compares++;

        if(grid[r1][c1] > grid[r2][c2])
            return true;

        return false;
    }

    // swaps values in the grid
    // at (r1, c1) and (r2, c2);
    // assumes that the parameters
    // are within the bounds
    private static void swap(int r1, int c1, int r2, int c2)
    {
        int temp = grid[r1][c1];
        grid[r1][c1] = grid[r2][c2];
        grid[r2][c2] = temp;
    }
}
