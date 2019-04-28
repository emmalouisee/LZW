import java.util.*;

class Decoder {

    public static void main(String [] args){

        try{

            //initialise the dictionary object
            ArrayList<String> dictionary = new ArrayList<String>();
            //populate the dictionary with the ascii symbol set + a reset symbol (256)
            for(int i = 0; i < 257; i++){
                dictionary.add(Character.toString ((char) i));
            }

            //initialise scanner object
            Scanner sc = new Scanner(System.in);
            //variable to hold the current piece of read-in data
            int data;
            //the index of the previously created dictionary entry
            int prev = -1;
            //the current index number
            int currIndex = 256;

            //while there is another phrase number to read in
            while(sc.hasNextInt()){

                //get the next phrase number
                data = sc.nextInt();

                //if the phrase number indicates RESET
                if(data == 256) {
                    //clear and reinitialize the dictionary
                    dictionary = new ArrayList<String>();
                    for(int i = 0; i < 257; i++){
                    dictionary.add(Character.toString ((char) i));
                    }
                    //reset currIndex and previous
                    currIndex = 256;
                    prev = -1;
                    //read in the next phrase number
                    data = sc.nextInt();
                }

                //if previous is not empty
                if(prev != -1){
                    //update the previous entry with the current value
                    dictionary.set(currIndex, (dictionary.get(currIndex).concat(dictionary.get(data).substring(0, 1))));
                    currIndex++;
                    //create the next dictionary entry with the current phrase as a value
                    dictionary.add(currIndex, dictionary.get(data));
                    //print out the current phrase
                    System.out.print(dictionary.get(data));
                    //set previous phrase to the current phrase
                    prev = data;
                }
                
                //else previous is empty
                else {
                    currIndex++;
                    //create the next dictionary entry with the current phrase as a value
                    dictionary.add(dictionary.get(data));
                    //output the current character
                    System.out.print((char) data);
                    //set previous to equal the current phrase
                    prev = data;
                }
            }
            //close the scanner object
            sc.close();
            
        }
        catch(Exception e){
            System.err.println(e);
        }
    }
}
