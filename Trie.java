public class Trie {

    //variable to store hte number of phrase numbers currently in the trie
    private int size;
    //a variable to store the first 'level' of the trie
    private Node[] root;

    //constructor
    public Trie() {
		//set size to allow for the 257 symbol set assumed at the outset
		size = 257;
		//set root to have a node for every symbol
		root = new Node[size];

		//store the ASCII value of each Node
		for(int t = 0; t < size; t++) {
			root[t] = new Node(t, (char)t);
		}
    }

    //method that adds a new node with a given value to a specified node
    public void Add(Node t, char val) {
		//increment the size of the trie
		size++;
		//create a new node with the specified value and the next available phrase number in the trie
		Node n = new Node(size-1, val);

		//if the given node has no child..
		if(t.Child() == null) {
			//add the new node as a child of the given node
			t.AddChild(n);
		}
		//if the given node does have a child...
		else {
			//create a temporary node to keep track of the current node and set it to the child of the given node
			Node tmp = t.Child();

			//while the current node has a sibling
			while(tmp.Sibling() != null) {
			//set current node to the sibling
			tmp = tmp.Sibling();
			}
			//add the new node as a sibling of the current node
			tmp.AddSibling(n);
		}
    }

    // method that checks if a given string is currently in the trie
    public Boolean Contains(String seq) {

		//variable to store which charcter in the string we are currently processing
		int index = 0;
		//split the given string into an array
		char[] s = seq.toCharArray();

		//variable to store the starting node of the given string 
		Node start = root[(int) s[index]];
		//variable to store which node we are currently at
		Node curr = start.Child();

		//increment the index so we can start processing the next character in the given string
		index++;

		//while we are currently at a node and we have not reached the end of the given string..
		while(curr != null && (index != s.length)) {

			//if the character stored at the current node is the same as the charcter we are currently processing..
			if(curr.Character() == s[index]) {
			//set the current node to be its child
			curr = curr.Child();
			//increment the index so we can start processing the next charchter in the string
			index++;
			}
			//if the character at the current node does not match the character we are currently processing..
			else {
			//set the current node to be its sibling
			curr = curr.Sibling();
			}
		}

		//if we have processed the entire string given..
		if(index == s.length) {
			//the string has been found in the trie
			return true;
		}
		//if we have not finished processing the string given...
		else {
			//the string has not been found in the trie
			return false;
		}
    }
    
    //method to find the last node that the given string matches up to
    public Node FindLast(String seq) {
		
		//variable to store which charcter in the string we are currently processing
		int index = 0;
		//split the given string into an array
		char[] s = seq.toCharArray();
		
		//variable to store the starting node of the given string 
		Node start = root[(int) s[index]];
		//variable to store which node we are currently at
		Node curr = start.Child();
		//variable to store the parent of the current node
		Node parentCurr = start;

		//increment the index so we can start processing the next charchter in the string
		index++;

		//while we are currently at a node and we have no reached the end of the given string..
		while((curr != null) && (index != s.length)) {

			//if the charcter stored at the current node macthes the character currently being processed..
			if(curr.Character() == s[index]) {
			//set the parent of the current node to the current node
			parentCurr = curr;
			//set current node to its child
			curr = curr.Child();
			//increment the index so we can start processing the next charchter in the string
			index++;
			}
			//if the charcter stored at the current node does not match the charcter cureently being processed..
			else {
			//set the current not to be its sibling
			curr = curr.Sibling();
			}
		}

		//return the parent node
		return parentCurr;
    }

    //method to reset the trie back to its original state
    public void Reset() {
		//set size to allow for the 257 symbol set assumed at the outset
		size = 257;
		//set root to have a node for every symbol
		root = new Node[size];

		//store the ASCII value of each Node
		for(int t = 0; t < size; t++) {
			root[t] = new Node(t, (char)t);
		}
		
    }

    //getter for the size of the trie
    public int Size() {
		return size;
    }
}
