package interviewcake;

import java.util.Random;

/**
 * Created by nachiketlondhe on 3/16/16.
 */
public class Shuffler {

    public static void main(String args[]) {

        int[] deck = new int[52];

        for ( int i = 0 ; i < deck.length; i ++) {
            deck[i] = i + 1;
        }

        Shuffler shuffler = new Shuffler();

        shuffler.shuffle(deck);

        for ( int i = 0 ; i < deck.length; i ++) {
            System.out.print("["+deck[i] + "] ");
        }
    }

    public void shuffle(int[] array) {
        if(array.length <= 1) {
            return;
        }

        for(int indexToShuffleFor = 0; indexToShuffleFor < array.length; indexToShuffleFor ++) {

            int randomPosition = getRandom(indexToShuffleFor, array.length - 1);

            int tempValue = array[indexToShuffleFor];
            array[indexToShuffleFor] = array[randomPosition];
            array[randomPosition] = tempValue;
        }
    }

    /**
     * Given the lower and upper bound return the random number after the lowerBound
     * @param lowerBound
     * @param upperBound
     * @return random number withing bounds
     */
    public int getRandom(int lowerBound, int upperBound) {

        Random random = new Random();

        return random.nextInt((upperBound - lowerBound) + 1) + lowerBound;
    }

}
