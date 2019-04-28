import java.util.*;
import java.io.*;

public class BitUnpacker {

    public static void main(String[] args) {

        try {
            //the current max phrase number
            int maxPhrase = 256;
            //the current input byte
            int input = 0;
            //the number of bits that need to be processed from the current byte
            int bitsToRead = 8;
            //the part of the current phrase that will be packed into the current byte
            int currInputPart = 0;
            //the current phrase number
            int phraseNumber = 0;

            //the current number of bits needed to complete the phrase number
            int phraseBitsNeeded = log2(maxPhrase);

            //read in a byte
            input = (int)System.in.read();

            //while there is still a byte of information to read
            while(input != -1){

                //if there are still bits to be processed for the current phrase 
                if(bitsToRead != 0){
                    //if the current number of bits needed to complete the phrase is greater 
                    //than or equal to the number of bits left to process from the current byte
                    if(phraseBitsNeeded >= bitsToRead){
                        //concatenate the bits still to be processed to the end of the phrase number
                        phraseNumber <<= bitsToRead;
                        phraseNumber |= input;
                        //decrease the number of bits needed to complete the phrase by the number 
                        //of bits that were just processed
                        phraseBitsNeeded -= bitsToRead;
                        //reset bits to read
                        bitsToRead = 0;
                    }

                    //else the current number of bits needed to complete the phrase is less 
                    //than the number of bits left to process from the current byte
                    else{
                        //right shift by the amount that we don't want for the current phrase
                        currInputPart = input >> (bitsToRead - phraseBitsNeeded);
                        //use logical and to mask out the bits that we might have already processed
                        input &= (int)(Math.pow(2, (bitsToRead - phraseBitsNeeded)) -1);
                        //concatenate the bits still to be processed to the end of the phrase number
                        phraseNumber <<= phraseBitsNeeded;
                        phraseNumber |= currInputPart;
                        //decrease bits to read by the number of bits just processed
                        bitsToRead -= phraseBitsNeeded;
                        //reset phrase bits needed to zero
                        phraseBitsNeeded = 0;
                    }

                }

                //if we have finished a phrase
                if(phraseBitsNeeded == 0){
                    //write out the phrase number
                    System.out.println(phraseNumber);

                    //if the current phrase is the reset symbol, reset maxPhrase
                    if(phraseNumber == 256) maxPhrase = 256;
                    //else increase the max phrase number by 1
                    else maxPhrase++;

                    //reset phraseNumber
                    phraseNumber = 0;
                    //recalculate the current number of bits per phrase
                    phraseBitsNeeded = log2(maxPhrase);
                }

                //if we have finished a byte
                if(bitsToRead == 0){
                    //read in another byte
                    input = (int)System.in.read();
                    //reset bits to read
                    bitsToRead = 8;
                }
            }
            //flush anything remaining in system.out
            System.out.flush();

        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //method to claculate log base 2 for the given input
    public static int log2(int x){
        return Integer.SIZE - Integer.numberOfLeadingZeros(x);
    }
}
