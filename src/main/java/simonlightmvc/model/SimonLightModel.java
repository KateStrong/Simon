/* *****************************************
 * Name: Kate Strong
 * Date: 2/23/2023
 *
 * Project: Simon
 * Package: simonlightmvc.model
 * Class: SimonLightModel
 *
 * Description:
 * A class representing all the lights in a simon game.
 * ****************************************
 */
package simonlightmvc.model;

import java.util.ArrayList;

public class SimonLightModel {
    /** Array of {@link Light} objects for the simon lights */
    private ArrayList<Light> lights;

    public SimonLightModel() {
        // utilize an ArrayList to store the lights
        this.lights = new ArrayList<>();

        // initialize their colors and add them to the array
        for (LightColorEnum light : LightColorEnum.values())
            this.lights.add(new Light(light.getColor()));
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    /**
     * return light at specified index
     *
     * @param i light index
     * @return light at index i
     */
    public Light getLight(int i) {
        return this.lights.get(i);
    }
}
