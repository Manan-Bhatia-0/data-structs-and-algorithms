public class BST<Key extends Comparable<Key>, Value> 
{
    private Node root;
    private int N;

    // CONSTRUCTOR 
    public BST() 
    {
	    this.root = null;
	    this.N = 0;
    }

    // PUBLIC METHODS 

    //
    // insert a new (key, val) into tree
    // or replace value of existing key
    //
    public void insert(Key key, Value val)
    {
	    //TO BE IMPLEMENTED
        Node node = new Node(key, val);
        node.left = null;
        node.right = null;
        node.height = 0;
        if (isEmpty()) {
            root = node;
            N++;
            root.height = 0;
            return;
        }
        else {
            Node temp = this.root;
            while (temp != null) {
                if (temp.key.compareTo(node.key) == 0) {
                    temp.val = node.val;
                    return;
                }
                if (node.key.compareTo(temp.key) < 0) {
                    if (temp.left == null) {
                        temp.left = node;
                        temp.isVisited = true;
                        N++;
                        setHeight(root, temp.left);
                        return;
                    }
                    else {
                        temp.isVisited = true;
                        temp = temp.left;
                    }
                }

                if (node.key.compareTo(temp.key) > 0) {
                    if (temp.right == null) {
                        temp.right = node;
                        N++;
                        temp.isVisited = true;
                        setHeight(root, temp.right);
                        return;
                    }
                    else {
                        temp.isVisited = true;
                        temp = temp.right;
                    }
                }

            }

        }
    }
    
    //
    // get the value associated with the given key;
    // return null if key doesn't exist
    //
    public Value get(Key key) 
    {
	    //TO BE IMPLEMENTED
        if (isEmpty() || (key == null)) {
            return null;
        }

        Node temp = this.root;
        while (temp != null) {
            if (temp.key.compareTo(key) == 0) {
                return temp.val;
            }
            if (temp.key.compareTo(key) > 0) {
                if (temp.left == null) {
                    return null;
                }
                else {
                    temp = temp.left;
                }
            }
            if (temp.key.compareTo(key) < 0) {
                if (temp.right == null) {
                    return null;
                }
                else {
                    temp = temp.right;
                }
            }
        }
        return null;
    }

    //
    // return true if the tree
    // is empty and false 
    // otherwise
    //
    public boolean isEmpty() 
    {
	    return root == null;
    }

    //
    // return the number of Nodes
    // in the tree
    //
    public int size() 
    {
	    return N;
    }

    //
    // returns the height of the tree
    //
    public int height() 
    {
        //System.out.println(height(root));
	    return height(root);
    }

    //
    // returns the height of node 
    // with given key in the tree;
    // return -1 if the key does
    // not exist in the tree
    //
    public int height(Key key) 
    {
	    //TO BE IMPLEMENTED
        if (isEmpty()) {
            return -1;
        }
        Node node = getNode(key);
        if (node == null) {
            return -1;
        }
        //System.out.printf("key: %s, node: %s\n", key.toString(), node.toString());
        if (isChildNode(node)) {
            node.height = 0;
            return node.height;
        }
        node.height = getMaxHeight(node);
      //  System.out.printf("height of node: %s = %d\n", node.toString(), node.height);
        return (node.height);
    }

    //
    // return a String version of the tree
    // level by level
    //
    public String toString() 
    {
        String str = "";
        Pair x = null;
        Queue<Pair> queue = new Queue<Pair>(N);
        int level = 0;
        queue.enqueue(new Pair(root, level));
        str += "Level 0: ";

        while(!queue.isEmpty()) 
        {
            x = queue.dequeue();
            Node n = x.node;

            if(x.depth > level) 
            {
                level++;
                str += "\nLevel " + level + ": ";
            }

            if(n != null) 
            {
                str += "|" + n.toString() + "|";
                queue.enqueue(new Pair(n.left, x.depth + 1));
                queue.enqueue(new Pair(n.right, x.depth + 1));
            } 
            else  
                str += "|null|";
        }

        str += "\n";
        return str;
    }
		

    // PRIVATE METHODS 

    //
    // return the height of x
    // or -1 if x is null
    //
    private int height(Node x) 
    {
        if(x == null)
            return -1;

        return x.height;
    }


   // sets the height of all the nodes in the tree in pre-order traversal

    private void setHeight(Node node, Node temp) {
        if (node == null) {
            return;
        }
        if (node.equals(temp)) {
            //node.height = height(node.key);
            //System.out.println(node.toString());
            return;
        }
        if (node.left == null && node.right == null) {
            return;
        }

        node.height = height(node.key);
        node.isVisited = false;
        if (node.left != null && node.left.isVisited) {
           // node.isVisited = false;
            setHeight(node.left, temp);
        }
        if (node.right != null && node.right.isVisited) {
           // node.isVisited = false;
            setHeight(node.right, temp);
        }
    }


    // finds and returns the maximum height out of the
    // left and right subtrees of the given node

    private int getMaxHeight(Node node) {
        int leftHeight = 0;
        int rightHeight = 0;
        if (node.left != null) {
            leftHeight = getMaxHeight(node.left);
        }
        if (node.right != null) {
            rightHeight = getMaxHeight(node.right);
        }
        if (node.right == null && node.left == null) {
            return Math.max(leftHeight, rightHeight);
        }

        else {
            return 1 + Math.max(leftHeight, rightHeight);
        }
    }

    // returns true if the given node is a child node
    private boolean isChildNode(Node node) {
        return ((node.left == null) && (node.right == null));
    }

    // similar to get(Key key) except it returns the node with the given key
    private Node getNode(Key key)
    {
        //TO BE IMPLEMENTED
        if (isEmpty() || (key == null)) {
            return null;
        }

        Node temp = this.root;
        while (temp != null) {
            if (temp.key.compareTo(key) == 0) {
                return temp;
            }
            if (temp.key.compareTo(key) > 0) {
                if (temp.left == null) {
                    return null;
                }
                else {
                    temp = temp.left;
                }
            }
            if (temp.key.compareTo(key) < 0) {
                if (temp.right == null) {
                    return null;
                }
                else {
                    temp = temp.right;
                }
            }
        }
        return null;
    }

    // NODE CLASS 
    public class Node 
    {
        Key key;
        Value val;
        Node left, right;
        int height;
        boolean isVisited = false;

        public Node(Key key, Value val) 
        {
            this.key = key;
            this.val = val;
        }
        
        public String toString() 
        {
            return "(" + key + ", " + val + "): " + height;
        }
    }

    // PAIR CLASS 
    public class Pair 
    {
        Node node;
        int depth;
        
        public Pair(Node node, int depth) 
        {
            this.node = node;
            this.depth = depth;
        }
    }

}
