import java.io.*;

public class Encoder {
    
    public static void main(String[] args) {

		try{

			//if more or less than 1 arguements are given...
			if(args.length != 1)
			{
				//display error message
				System.err.println("Usage:    java Encoder <max number of bits>");
				return;
			}

			//variable to store the maximum number of bits to encode a phrase number
			int maxBits = Integer.parseInt(args[0]);
			//variable to store the maximum number of phrases allowed in the trie until it must be reset
			int maxSize = (int) Math.pow(2, maxBits);

			//create a multiway trie with just the known symbol set 
			Trie multiTrie = new Trie();
			
			//a variable to keep track of the string that has been found in the trie
			String w = "";
			//variable to store the data read from the input stream
			int ch;

			//whilst there is input...
			while((ch = System.in.read()) != -1) {

				//convert the input into a char
				char c = (char) ch;
				//add the new char to the string that is known to be in the trie
				String wc = w + c;

				//if the trie contains the new string...
				if(multiTrie.Contains(wc)) {
					//store the new string in the variable that keeps track of the string known to be in the trie
					w = wc;
				}
				//if the trie does not contain the new string...
				else {		

					//find the node that the mismatched character needs to be added to
					Node prev = multiTrie.FindLast(wc);

					//add the mismatched character off of the that node
					multiTrie.Add(prev, c);

					//print out the phrase number of that node
					System.out.println(prev.Phrase());

					//set the variable that keeps track of the string known to be in the trie to the character that was just added to the trie
					w = "" + c;

					//if the trie is full...
					if(multiTrie.Size() == maxSize) {
						//print out the phrase number for the RESET symbol
						System.out.println("256");
						//reset the trie
						multiTrie.Reset();
					}
				}
			}

			//if there is no more input and the last charcter of the input was not a mismatched character...
			if(!w.equals("")) {

				//find the node that signifies the end of 'w'
				Node prev = multiTrie.FindLast(w);

				//print out the phrase number of the node
				System.out.println(prev.Phrase());
			}

		}
		catch (Exception ex) {
			System.out.println(ex);
		}
    }
}
