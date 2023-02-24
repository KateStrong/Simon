package simonlightmvc.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimonLightPatternTest {
    private static final int NUM_LIGHTS = 4;
    private static final int PAT_LENGTH = 5;
    SimonLightPattern pattern;

    @BeforeEach
    void setUp() {
        pattern = new SimonLightPattern(PAT_LENGTH, NUM_LIGHTS);
    }

    @Test
    void getPortionOfSequence() {
        for (int i = 0; i < PAT_LENGTH; i++) {
            int[] temp = pattern.getPortionOfSequence(i);
            for (int j = 0; j < temp.length; j++) {
                assertEquals(true, pattern.checkLight(j, temp[j]));
            }
        }
    }

    @Test
    void checkLight() {
        for (int i = 0; i < PAT_LENGTH; i++) {
            assertTrue(pattern.checkLight(i, pattern.getElement(i)));
        }
    }
}