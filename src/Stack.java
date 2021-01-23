public class Stack<Item> {
    private int n;
    private Node first;

    //constructor: creates an empty Stack
    public Stack() {
	//To be implemented
        n = 0;
        first = null;
    }
    // returns true if Loc already in stack
    public boolean visitedLocation(Loc currentLocObj) {
        Node temp = first;
        while (temp != null) {
            if (currentLocObj == temp.item) {
                return true;
            }
            temp = temp.next;
        }

        return false;
    }

    //adds item to the top of the Stack
    public void push(Item item) {
	//To be implemented
        Node temp = new Node();
        temp.item = item;
        temp.next = first;
        first = temp;
    }

    //removes and returns the top item from the Stack
    //throw EmptyStackException if the Stack is empty
    public Item pop() throws EmptyStackException {
	//To be implemented
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Node temp = new Node();
        temp = first;
        first = first.next;
        return temp.item;
    }
    
    //return true if the Stack is empty, false else
    public boolean isEmpty() {
	//To be implemented
        return (first == null);
    }

    //return the size (number of items) of the Stack
    public int size() {
	//To be implemented
        Node temp = first;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        n = count;
        return count;
    }

    public Node getFirst() {
        return this.first;
    }
    //return but do not remove the top item from the Stack
    //throw EmptyStackException if the Stack is empty
    public Item peek() throws EmptyStackException {
	//To be implemented
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return first.item;
    }

    public Stack<Item> getCopy() {
        Node temp = first;
        Stack<Item> stackCopy = new Stack<>();
        while (temp != null) {
            stackCopy.push(temp.item);
            temp = temp.next;
        }
        return stackCopy;
    }
    private class Node {
	Item item;
	Node next;
    }


}