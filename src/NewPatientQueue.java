import java.util.ArrayList;

public class NewPatientQueue {
    private Patient[] array;
    private int size;
    private PHashtable table;

    /*TO BE COMPLETED IN PART 1*/

    //constructor: set variables
    //capacity = initial capacity of array 
    public NewPatientQueue(int capacity) {
	//TO BE COMPLETED
        this.array = new Patient[capacity];
        this.size = 0;
        this.table = new PHashtable(capacity);
    }

    // returns the index of the left child of the given parent
    public int leftChild(int parentIndex) {
        return (2 * parentIndex + 1);
    }

    // returns the index of the right child of the given parent
    public int rightChild(int parentIndex) {
        return (2 * parentIndex + 2);
    }

    // returns the index of the parent of the given child
    public int parent(int childIndex) {
        if (childIndex == 0) {
            return 0;
        }
        return (childIndex - 1) / 2;
    }

    // swaps the patients at the given indices with each other
    public void swapPatients(int index1, int index2) {
        Patient temp = array[index2];
        array[index2] = array[index1];
        array[index2].setPosInQueue(index2);
        array[index1] = temp;
        array[index1].setPosInQueue(index1);
    }

    // recursively heapifies the heap from bottom-up if heap is out of order
    public void heapifyBottomUp(Patient p, int index) {

        int parentIndex = parent(index);
        if ((index > 0) && (p.compareTo(array[parentIndex]) > 0)) {
            swapPatients(index, parentIndex);
            //p.setPosInQueue(parentIndex);
            heapifyBottomUp(array[parentIndex], parentIndex);
        }
        //System.out.printf("final index: %d\n", index);
       // return index;
    }

    // recursively heapifies the heap from top-down if heap is out of order
    public void heapifyTopDown(Patient p, int index) {
        int leftChildIndex = leftChild(index);
        int rightChildIndex = rightChild(index);
        int max = index;

        if ((leftChildIndex < size) && (array[leftChildIndex].compareTo(array[index]) > 0)) {
            max = leftChildIndex;
        }

        if ((rightChildIndex < size) && (array[rightChildIndex].compareTo(array[max]) > 0)) {
            max = rightChildIndex;
        }

        if (max != index) {
            swapPatients(max, index);
            heapifyTopDown(array[max], max);
        }

        //return index;
    }

    //insert Patient p into queue
    //return the final index at which the patient is stored
    //return -1 if the patient could not be inserted
    public int insert(Patient p) {
        //TO BE COMPLETED
        if (size == array.length) {
            return -1;
        }
        int index = size;
        table.put(p);
        array[index] = p;
        size++;
        p.setPosInQueue(index);
        //int finalIndex =
        heapifyBottomUp(p, index);

        //p.setPosInQueue(finalIndex);
        //System.out.printf("%s, index: %d\n", p.toStringWithPos(), finalIndex);
       // System.out.println(p.toStringWithPos());
        return p.posInQueue();

    }

    //remove and return the patient with the highest urgency level
    //if there are multiple patients with the same urgency level,
    //return the one who arrived first
    public Patient delMax() {
        //TO BE COMPLETED
        if (isEmpty()) {
            return null;
        }
        Patient root = getMax();
        table.remove(root.name());
        root.setPosInQueue(-1);
        array[0] = array[size - 1];
        Patient temp = array[size - 1];
        array[size-1] = null;
        size--;
       // int finalIndex =
        heapifyTopDown(root, 0);
        //temp.setPosInQueue(finalIndex);
        Patient rootCopy = root;
        root = null;
        return rootCopy;
    }

    //return but do not remove the first patient in the queue
    public Patient getMax() {
        //TO BE COMPLETED
        return array[0];
    }

    //return the number of patients currently in the queue
    public int size() {
        //TO BE COMPLETED
        return size;
    }

    //return true if the queue is empty; false else
    public boolean isEmpty() {
        //TO BE COMPLETED
        return size == 0;
    }

    //used for testing underlying data structure
    public Patient[] getArray() {
	return array;
    }

    /*TO BE COMPLETED IN PART 2*/

    //remove and return the Patient with
    //name s from the queue
    //return null if the Patient isn't in the queue
    public Patient remove(String s) {
	//TO BE COMPLETED
        Patient p = table.get(s);
        if (p == null) {
            return null;
        }
        int index = p.posInQueue();
        p.setPosInQueue(-1);
        array[size - 1].setPosInQueue(index);
        array[index] = array[size - 1];
        array[size - 1] = null;
        size--;
        if (index > 0 && array[index] != null && array[parent(index)] != null) {
            if (array[index].compareTo(array[parent(index)]) > 0) {
                heapifyBottomUp(array[index], index);
            }
            else {
                heapifyTopDown(array[index], index);
            }
        }

	    Patient pCopy = p;
	    p = null;
	    table.remove(s);
        return pCopy;
    }
    
    //update the emergency level of the Patient
    //with name s to urgency
    public void update(String s, int urgency) {
	//TO BE COMPLETED
        Patient p = table.get(s);
        if (p == null) {
            return;
        }
        p.setUrgency(urgency);
        if (p.posInQueue() > 0 && p.compareTo(array[parent(p.posInQueue())]) > 0) {
            heapifyBottomUp(p, p.posInQueue());
        }
        else {
            heapifyTopDown(p, p.posInQueue());
        }
    }

    public PHashtable getTable() {
        return table;
    }
}
    