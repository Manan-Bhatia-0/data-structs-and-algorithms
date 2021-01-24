import java.io.*;

public class Puzzle
{
    private String[] grid;
    private int size;
    //
	// constructor: fn is the filename where the puzzle is stored
	//
    public Puzzle(String fn)
    {
        File file = new File(fn);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bfr = new BufferedReader(fr);
        String s = null;
        try {
            s = bfr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        size = Integer.parseInt(s);
        grid = new String[size];
        try {
            s = bfr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        while (s != null) {
            if (i == size) {
                break;
            }
            grid[i++] = s;
            try {
                s = bfr.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TO BE IMPLEMENTED
    }

    //
    // search the puzzle for the given word
    // return {a, b, x, y} where (a, b) is
    // the starting location and (x, y) is 
    // the ending location
    // return null if the word can't be found
    // in the puzzle
    //
    public int[] search(String word) 
    {
        //TO BE IMPLEMENTED
        int[] result;
        //System.out.println(word);
        result = leftToRight(word);
        if (result != null) {
            return result;
        }
        result = rightToLeft(word);
        if (result != null) {
            return result;
        }

        result = topToBottom(word);
        if (result != null) {
            return result;
        }

        result = bottomToTop(word);
        if (result != null) {
            return result;
        }

        result = topLeftToBottomRight(word);
        if (result != null) {
            return result;
        }

        result = bottomRightToTopLeft(word);
        if (result != null) {
            return result;
        }
        result = topRightToBottomLeft(word);
        if (result != null) {
            return result;
        }

        return bottomLeftToTopRight(word);
    }

    private int[] leftToRight(String word) {
        int startI = 0;
        int startJ = 0;
        int endI = 0;
        int endJ = 0;
        for (int i = 0; i < size; i++) {
            int k = 0;
            for (int j = 0; j < size; j++) {
                    if (word.charAt(k) == grid[i].charAt(j)) {
                        k++;
                    }
                    else {
                        k = 0;
                        if (word.charAt(k) == grid[i].charAt(j)) {
                            k++;
                        }
                    }
                    if (k == word.length()) {
                        endI = i;
                        endJ = j;
                        startI = endI;
                        startJ = endJ - word.length() + 1;
                        return new int[] {startI, startJ, endI, endJ};
                    }
            }
        }
        return null;
    }

    private int[] rightToLeft(String word) {
        word = reverseString(word);
        int startI = 0;
        int startJ = 0;
        int endI = 0;
        int endJ = 0;
        for (int i = 0; i < size; i++) {
            int k = 0;
            for (int j = 0; j < size; j++) {

                if (word.charAt(k) == grid[i].charAt(j)) {
                    k++;
                }
                else {
                    k = 0;
                    if (word.charAt(k) == grid[i].charAt(j)) {
                        k++;
                    }
                }
                if (k == word.length()) {
                    startI = i;
                    startJ = j;
                    endI = startI;
                    endJ = startJ - word.length() + 1;
                    return new int[] {startI, startJ, endI, endJ};
                }
            }
        }
        return null;
    }

    private int[] topToBottom(String word) {
        int startI = 0;
        int startJ = 0;
        int endI = 0;
        int endJ = 0;
        for (int i = 0; i < size; i++) {
            int k = 0;
            for (int j = 0; j < size; j++) {
                if (word.charAt(k) == grid[j].charAt(i)) {
                    k++;
                }
                else {
                    k = 0;
                    if (word.charAt(k) == grid[j].charAt(i)) {
                        k++;
                    }
                }
                if (k == word.length()) {
                    endI = j;
                    endJ = i;
                    startI = endI - word.length() + 1;
                    startJ = endJ;
                    return new int[] {startI, startJ, endI, endJ};
                }
            }
        }
        return null;
    }

    private int[] bottomToTop(String word) {
        word = reverseString(word);
        int startI = 0;
        int startJ = 0;
        int endI = 0;
        int endJ = 0;
        for (int i = 0; i < size; i++) {
            int k = 0;
            for (int j = 0; j < size; j++) {
                if (word.charAt(k) == grid[j].charAt(i)) {
                    k++;
                }
                else {
                    k = 0;
                    if (word.charAt(k) == grid[j].charAt(i)) {
                        k++;
                    }
                }
                if (k == word.length()) {
                    startI = j;
                    startJ = i;
                    endI = startI - word.length() + 1;
                    endJ = startJ;
                    return new int[] {startI, startJ, endI, endJ};
                }
            }
        }
        return null;
    }
    
    private int[] topLeftToBottomRight(String word) {
        int startI = 0;
        int startJ = 0;
        int endI = 0;
        int endJ = 0;
        for (int i = 0; i < size; i++) {
            int k = 0;
            int r = i;
            for (int j = 0; j < size; j++) {
                r = i;
                int c = j;
                if (word.charAt(k) == grid[i].charAt(j)) {
                    k++;
                    r++;
                    c++;
                    for (int l = 0; l < word.length() && r < size && c < size; l++) {
                        if (word.charAt(k) == grid[r].charAt(c)) {
                            k++;
                            r++;
                            c++;
                        }

                        else {
                            k = 0;
                            if (word.charAt(k) == grid[r].charAt(c)) {
                                k++;
                            }
                        }

                        if (k == word.length()) {
                            endI = r - 1;
                            endJ = c - 1;
                            startI = endI - word.length() + 1;
                            startJ = endJ - word.length() + 1;
                            return new int[] {startI, startJ, endI, endJ};
                        }

                    }

                }

                else {
                    k = 0;
                    if (word.charAt(k) == grid[r].charAt(c)) {
                        k++;
                    }
                }
            }
        }
        return null;
    }

    private int[] bottomRightToTopLeft(String word) {
        word = reverseString(word);
        int startI = 0;
        int startJ = 0;
        int endI = 0;
        int endJ = 0;
        for (int i = 0; i < size; i++) {
            int k = 0;
            int r = i;
            for (int j = 0; j < size; j++) {
                r = i;
                int c = j;
                if (word.charAt(k) == grid[i].charAt(j)) {
                    k++;
                    r++;
                    c++;
                    for (int l = 0; l < word.length() && r < size && c < size; l++) {
                        if (word.charAt(k) == grid[r].charAt(c)) {
                            k++;
                            r++;
                            c++;
                        }

                        else {
                            k = 0;
                            if (word.charAt(k) == grid[r].charAt(c)) {
                                k++;
                            }
                        }

                        if (k == word.length()) {
                            startI = r - 1;
                            startJ = c - 1;
                            endI = startI - word.length() + 1;
                            endJ = startJ - word.length() + 1;
                            return new int[] {startI, startJ, endI, endJ};
                        }

                    }

                }

                else {
                    k = 0;
                    if (word.charAt(k) == grid[r].charAt(c)) {
                        k++;
                    }
                }
            }
        }
        return null;
    }

    private int[] topRightToBottomLeft(String word) {
        int startI = 0;
        int startJ = 0;
        int endI = 0;
        int endJ = 0;
        for (int i = 0; i < size; i++) {
            int k = 0;
            int r = i;
            for (int j = 0; j < size; j++) {
                r = i;
                int c = j;
                if (word.charAt(k) == grid[i].charAt(j)) {
                    k++;
                    r++;
                    c--;
                    for (int l = 0; l < word.length() && r < size && c > 0; l++) {
                        if (word.charAt(k) == grid[r].charAt(c)) {
                            k++;
                            r++;
                            c--;
                        }

                        else {
                            k = 0;
                            if (word.charAt(k) == grid[r].charAt(c)) {
                                k++;
                            }
                        }

                        if (k == word.length()) {
                            endI = r - 1;
                            endJ = c + 1;
                            startI = endI - word.length() + 1;
                            startJ = endJ + word.length() - 1;
                            return new int[] {startI, startJ, endI, endJ};
                        }

                    }

                }

                else {
                    k = 0;
                    if (word.charAt(k) == grid[r].charAt(c)) {
                        k++;
                    }
                }
            }
        }
        return null;
    }

    private int[] bottomLeftToTopRight(String word) {
        word = reverseString(word);
        int startI = 0;
        int startJ = 0;
        int endI = 0;
        int endJ = 0;
        for (int i = 0; i < size; i++) {
            int k = 0;
            int r = i;
            for (int j = 0; j < size; j++) {
                r = i;
                int c = j;
                if (word.charAt(k) == grid[i].charAt(j)) {
                    k++;
                    r++;
                    c--;
                    for (int l = 0; l < word.length() && r < size && c >= 0; l++) {
                        if (word.charAt(k) == grid[r].charAt(c)) {
                            k++;
                            r++;
                            c--;
                        }
                        else {
                            k = 0;
                            if (word.charAt(k) == grid[r].charAt(c)) {
                                k++;
                            }
                        }

                        if (k == word.length()) {
                            startI = r - 1;
                            startJ = c + 1;
                            endI = startI - word.length() + 1;
                            endJ = startJ + word.length() - 1;
                            return new int[] {startI, startJ, endI, endJ};
                        }

                    }

                }

                else {
                    k = 0;
                    if (word.charAt(k) == grid[r].charAt(c)) {
                        k++;
                    }
                }
            }
        }
        return null;
    }

    private String reverseString(String word) {
        char[] reversedArray = new char[word.length()];
        int j = word.length() - 1;
        for (int i = 0; i < word.length() ; i++) {
            reversedArray[i] = word.charAt(j);
            j--;
        }
        String reversedString = "";
        for (int i = 0; i < word.length(); i++) {
            reversedString += reversedArray[i];
        }
        return reversedString;
    }
}