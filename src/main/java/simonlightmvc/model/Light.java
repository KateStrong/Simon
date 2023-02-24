/* *****************************************
 * Name: Kate Strong
 * Date: 2/23/2023
 *
 * Project: Simon
 * Package: simonlightmvc.model
 * Class: Light
 *
 * Description:
 * A simple abstraction for a basic simon light that can turn on and off.
 * The light will also have a color which the class will manage
 * to simulate darkening
 * ****************************************
 */
package simonlightmvc.model;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class Light {

    /** Is the light on? */
    private SimpleBooleanProperty isOn;

    /** The current color of the light */
    private SimpleObjectProperty<Color> currentColor;

    /** The light's on color */
    private Color onColor;
    /** The light's off color */
    private Color offColor;

    /**
     * construct a new Light instance that is currently set
     * to the light's off color
     *
     * @param color represents the on color for the light
     */
    public Light(Color color) {
        this.isOn = new SimpleBooleanProperty(false);
        this.onColor = color;
        this.offColor = color.darker();
        this.currentColor = new SimpleObjectProperty<>();
        this.currentColor.set(this.offColor);
    }

    public boolean isIsOn() {
        return isOn.get();
    }

    public SimpleBooleanProperty isOnProperty() {
        return isOn;
    }

    public Color getCurrentColor() {
        return currentColor.get();
    }

    public SimpleObjectProperty<Color> currentColorProperty() {
        return currentColor;
    }

    /**
     * toggle the state of the light to be either on or off
     */
    public void toggle () {
        this.isOn.set(!this.isOn.get());
        if (this.isIsOn()) {
            this.currentColor.set(this.onColor);
        }
        else {
            this.currentColor.set(this.offColor);
        }
    }

    /**
     * Turn the light on for a specified number of milliseconds.
     *
     * @param ms number of milliseconds
     */
    public void turnOnFOrMs(long ms) {
        Runnable r = () -> {
            try {
                // if we're not on, then turn on
                if (!this.isIsOn()) {
                    toggle();
                }
                Thread.sleep(ms);
            }
            catch (InterruptedException e) {
            }
            finally {
                //if we're on, turn off
                if (this.isIsOn()) {
                    toggle();
                }
            }
        };

        // encapsulate our Runnable in a thread and start it
        Thread t = new Thread(r);
        t.start();
    }

    /**
     * Turn the light on
     */
    public void turnOn() {
        this.isOn.set(true);
        this.currentColor.set(this.onColor);
    }

    /**
     * Turn the light off
     */
    public void turnOff() {
        this.isOn.set(false);
        this.currentColor.set(this.offColor);
    }
}