import java.util.Objects;

public class Queue<Item> {
    private Item[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    //constructor: create an empty Queue with initial
    //capacity of 1
    public Queue() {
	//To be implemented
        this.queue = (Item[]) new Object[1];
        front = 0;
        rear = 0;
        size = 0;
        capacity = 1;
    }

    //constructor: create an empty Queue with initial
    //capacity of n
    public Queue(int n) {
	//To be implemented
        this.queue = (Item[]) new Object[n];
        front = 0;
        rear = 0;
        size = 0;
        capacity = n;
    }
    

    //double the array capacity(func = 0) or reduce the array capacity by half if the size(func = 1)
    public Item[] changeCapacity(Item[] queue, int func) {
        Item[] newArr;
        if (func == 0) {
            newArr = (Item[]) new Object[2 * queue.length];
        }

        else {
            newArr = (Item[]) new Object[queue.length / 2];
        }
        int i = front;
        int k = 0;
        while (i != queue.length && k < size) {
            newArr[k] = queue[i];
            i = (i + 1) % queue.length;
            k++;
        }
        front = 0;
        rear = size;
        return newArr;
    }

    //add an item to the back of the Queue
    public void enqueue(Item item) {
	//To be implemented
        if ((size + 1) == queue.length) {
            queue = changeCapacity(queue, 0);
        }

        queue[rear] = item;
        size++;
        rear = (rear + 1) % queue.length;


    }

    //remove and return the front item from the Queue
    //throw EmptyQueueException if the Queue is empty
    //reduce the array capacity by half if the size 
    //of the Queue falls below 1/4 full
    public Item dequeue() throws EmptyQueueException {
	//To be implemented
        if (this.isEmpty()) {
            throw new EmptyQueueException();
        }
        size--;
        Item temp = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;

        if ((size < (queue.length / 4)) && ((queue.length / 2) >= capacity)) {
            queue = changeCapacity(queue, 1);
        }
        return temp;
    }

    //return true if the Queue is empty
    //return false if the Queue is not empty
    public boolean isEmpty() {
	//To be implemented
        return (front == rear);
    }

    //return the size of the Queue (i.e. the 
    //number of elements currently in the Queue)
    public int size() {
	//To be implemented
        return size;

    }

    //return but do not remove the front item in the Queue
    //throw an exception if the Queue is empty
    public Item peek() throws EmptyQueueException {
	//To be implemented
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        return queue[front];
    }
    
    //return the underlying array
    public Item[] getArray() {
	    return queue;
    }
}