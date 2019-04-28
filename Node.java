public class Node {
    //variable to store the phrase number of the node
    private int phraseNum;
    //variable to store the ASCII character of the node
    private char character;
    //pointer to the child of the node
    private Node child;
    //pointer to the sibling of the node
    private Node sibling;

    
    public Node(int p, char c) {
        phraseNum = p;
        character = c;
        child = null;
        sibling = null;
    }

    //getter for the phrase number
    public int Phrase() {
	    return phraseNum;
    }

    //getter for the character
    public char Character() {
	    return character;
    }

    //getter for the child
    public Node Child() {
	    return child;
    }

    //getter for the sibling
    public Node Sibling() {
	    return sibling;
    }

    //setter for the child pointer
    public void AddChild(Node c) {
	    child = c;
    }

    //setter for the sibling pointer
    public void AddSibling(Node s) {
	    sibling = s;
    }
    
}
