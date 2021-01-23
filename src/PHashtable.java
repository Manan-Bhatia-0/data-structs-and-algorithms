import java.util.ArrayList;
public class PHashtable {
    private ArrayList<Patient>[] table;
    private int size;

    //set the table size to the first
    //prime number p >= capacity
    public PHashtable (int capacity) {
	//TO BE COMPLETED
        this.table = new ArrayList[getNextPrime(capacity)];
        this.size = 0;
    }

    //returns the hash value for the given key(patient's name)
    public int getHashValue(String key) {
       int hash = key.hashCode() % table.length;
       if (hash < 0) {
           hash += table.length;
       }
       return hash;
    }

    //return the Patient with the given name
    //or null if the Patient is not in the table
    public Patient get(String name) {
	//TO BE COMPLETED
        /*if (table.length == 0) {
            return null;
        }

          */
        int index = getHashValue(name);
        if (table[index] == null) {
            return null;
        }
        if (table[index].get(0).name().equals(name)) {
            return table[index].get(0);
        }
        for (int i = 1; i < table[index].size() ; i++) {
            if (table[index].get(i).name().equals(name)) {
                return table[index].get(i);
            }
        }
        return null;
    }

    //put Patient p into the table
    public void put(Patient p) {
	//TO BE COMPLETED
        int index = getHashValue(p.name());
        if (table[index] != null) {
            table[index].add(p);
        }
        else {
            table[index] = new ArrayList<>();
            table[index].add(p);
        }
        size++;
    }

    //remove and return the Patient with the given name
    //from the table
    //return null if Patient doesn't exist
    public Patient remove(String name) {
	//TO BE COMPLETED
        Patient patient = get(name);
        if (patient == null) {
            return null;
        }
        int index = getHashValue(name);
        table[index].remove(patient);
        size--;
        return patient;

    }	    

    //return the number of Patients in the table
    public int size() {
	//TO BE COMPLETED
        return size;
    }

    //returns the underlying structure for testing
    public ArrayList<Patient>[] getArray() {
	return table;
    }
    
    //get the next prime number p >= num
    private int getNextPrime(int num) {
    if (num == 2 || num == 3)
        return num;
    int rem = num % 6;
    switch (rem) {
        case 0:
        case 4:
            num++;
            break;
        case 2:
            num += 3;
            break;
        case 3:
            num += 2;
            break;
    }
    while (!isPrime(num)) {
        if (num % 6 == 5) {
            num += 2;
        } else {
            num += 4;
           }
        }
        return num;
    }


    //determines if a number > 3 is prime
    private boolean isPrime(int num) {
        if(num % 2 == 0){
            return false;
        }
        
	int x = 3;
	for(int i = x; i < num; i+=2){
	    if(num % i == 0){
		    return false;
        }
    }
	return true;
    }
}
      

