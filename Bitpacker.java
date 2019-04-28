import java.util.*;

public class Bitpacker {

    public static void main(String[] args) {

        try {

            //the current max phrase number
            int maxPhrase = 256;
            //the output byte
            byte output = 0;
            //the number of bits currently available in the output byte
            int bitsAvailable = 8;
            //remaining bits of the current phrase that need to be output
            int bitsRemaining = 0;
            //the part of the current phrase that will be packed into the current byte
            int currPhrasePart = 0;
            //the current phrase number
            int phraseNumber = 0;

            //initialise the input stream
            Scanner sc = new Scanner(System.in);

            //while there are still phrase numbers to read
            while(sc.hasNextInt()){

                //read the next phrase number
                phraseNumber = sc.nextInt();
                //calculate min number of bits needed to represent the phrase number, based on what is in our dictionary
                bitsRemaining = log2(maxPhrase);

                //while the number of bits remaining in the phrase is greater than the number of bits available
                while(bitsRemaining > bitsAvailable){
                    //logical shift left the outbyte by the number of available bits
                    output <<= bitsAvailable;
                    //logical shift right the current phrase number by the amount that we want to store in the current byte
                    currPhrasePart = phraseNumber >> (bitsRemaining - bitsAvailable);
                    //use logical and to mask out the bits that we might have already sent
                    currPhrasePart &= (int)(Math.pow(2, bitsAvailable) -1);
                    //pack the curr phrase bits into the output byte 
                    output |= currPhrasePart;

                    //send the current byte
                    System.out.write(output);

                    //decrease the number of phrase bits to send by the number we just sent
                    bitsRemaining -= bitsAvailable;
                    //reset output byte and bitsAvailable for the next phraseNumber
                    output = 0;
                    bitsAvailable = 8;
                }

                //once we have less than a full byte of the current phrase to send
                
                //if there are still some bits to send 
                if(bitsRemaining > 0){
                    //use logical and to mask out the bits that we might have already sent
                    currPhrasePart = (phraseNumber & (int)(Math.pow(2, bitsRemaining) -1));
                    //pack the curr phrase bits into the output byte 
                    output |= currPhrasePart;
                    //decrease the number of bits available in the byte by the number of bits we have just packed into it
                    bitsAvailable -= bitsRemaining;
                }

                //if the byte is now full
                if(bitsAvailable == 0){
                    //write out the byte
                    System.out.write(output);
                    //reset output byte and bitsAvailable for the next phraseNumber
                    output = 0;
                    bitsAvailable = 8;
                }

                //if the current phrase is the reset symbol, reset maxPhrase
                if(phraseNumber == 256) maxPhrase = 256;
                //else increase the max phrase number by 1
                else maxPhrase++;

            }

            //if we have a phrase number where the final byte isn't full, and has not been sent
            if(bitsAvailable < 8){
                //shift the output to the left by the number of available bits
                output <<= bitsAvailable;
                System.out.write(output);
            }

            //flush anything remaining in system.out, and close the scanner
            System.out.flush();
            sc.close();
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
