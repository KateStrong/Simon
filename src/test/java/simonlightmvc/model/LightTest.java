package simonlightmvc.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LightTest {
    private Light myLight;
    @BeforeEach
    void setUp() {
        myLight = new Light(LightColorEnum.RED.getColor());
    }

    @Test
    void toggle() {
        assertEquals(false, myLight.isIsOn());
        myLight.toggle();
        assertEquals(true, myLight.isIsOn());
        myLight.toggle();
        assertEquals(false, myLight.isIsOn());
    }

    @Test
    void turnOn() {
        myLight.turnOn();
        assertEquals(true, myLight.isIsOn());
    }

    @Test
    void turnOff() {
        myLight.turnOn();
        myLight.turnOff();
        assertEquals(false, myLight.isIsOn());
    }
}