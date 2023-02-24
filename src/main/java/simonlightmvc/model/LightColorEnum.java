/* *****************************************
 * Name: Kate Strong
 * Date: 2/23/2023
 *
 * Project: Simon
 * Package: simonlightmvc.model
 * Class: LightColorEnum
 *
 * Description:
 * An enumeration of the different lights used in the app.
 * Each enum will encapsulate a color representing an "on" state.
 * ****************************************
 */
package simonlightmvc.model;
import javafx.scene.paint.Color;

public enum LightColorEnum {
    RED(Color.RED),
    YELLOW(Color.YELLOW),
    BLUE(Color.BLUE),
    GREEN(Color.GREEN);

    private Color color;

    private LightColorEnum(Color color) { this.color = color; }

    public Color getColor() {
        return color;
    }
}
