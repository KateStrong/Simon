/* *****************************************
 * Name: Kate Strong
 * Date: 2/23/2023
 *
 * Project: Simon
 * Package: simonlightmvc.model
 * Class: SimonLightPattern
 *
 * Description:
 * This class represents a simon light pattern.
 * The pattern is generated randomly.
 * Methods are provided to help check user input against the pattern.
 * ****************************************
 */
package simonlightmvc.model;

import java.util.Random;

public class SimonLightPattern {

    /** An array to store the pattern of lights.
     *  Each light is a unique integer (0 through number of lights -1)
     */
    private int[] sequence;

    /**
     * Construct a new pattern with a random light sequence.
     *
     * @param length the length of the pattern
     * @param numberOfLights the number of lights the pattern will use
     */
    public SimonLightPattern(int length, int numberOfLights) {
        Random rand = new Random();
        this.sequence = new int[length];
        for (int i = 0; i < length; i++ ) {
            int randInt = rand.nextInt(numberOfLights);
            this.sequence[i] = randInt;
        }
    }

    public int getElement(int i) { return this.sequence[i]; }

    /**
     * get a specified portion of the pattern starting at index 0.
     *
     * @param end the ending index (not included).
     * @return an integer array sequence[0 : end - 1].
     */
    public int[] getPortionOfSequence(int end) {
        // return an empty array if the stopping point is longer than the array
        if (end > this.sequence.length) {
            return null;
        }
        int[] ret = new int[end];
        for (int i = 0; i < end; i++) {
            ret[i] = sequence[i];
        }
        return ret;
    }

    /**
     * Check that the provided light is a correct guess.
     *
     * @param sequenceIndex the index of the pattern.
     * @param light the light guessed.
     * @return return true if the light at the specified index of the pattern matches
     *          the guessed light.
     */
    public boolean checkLight(int sequenceIndex, int light) {
        return light == this.sequence[sequenceIndex];
    }
}
