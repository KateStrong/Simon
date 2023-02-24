/* *****************************************
 * Name: Kate Strong
 * Date: 2/23/2023
 *
 * Project: Simon
 * Package: simonlightmvc.view
 * Class: SimonLightView
 *
 * Description:
 * View of the simon game, which includes the lights, start button,
 * and score tracker.
 * ****************************************
 */
package simonlightmvc.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import simonlightmvc.model.Light;
import simonlightmvc.model.SimonLightModel;

import java.util.ArrayList;

public class SimonLightScreenView {

    /** an instance of the simon light model */
    private SimonLightModel theModel;

    /** the root for the scene graph */
    private AnchorPane root;

    /** HBox for the start and play buttons */
    private HBox startMenu;

    /** FlowPane for placing the buttons */
    private FlowPane buttons_center;

    /** Start button for initializing game */
    private Button startButton;

    /** Play button for playing the pattern.
     * It is hidden and fired from within the code. */
    private Button playButton;

    /** an array to store the four lights */
    private ArrayList<SVGPath> lights;

    /** Label for displaying the length of the longest correctly
     * repeated sequence */
    public Label score;

    public AnchorPane getRoot() { return root; }

    public HBox getStartMenu() { return startMenu; }

    public FlowPane getButtons_center() { return buttons_center; }

    public Button getStartButton() { return startButton; }

    public Button getPlayButton() { return playButton; }

    public ArrayList<SVGPath> getLights() { return lights; }


    /**
     * Constructor for the Simon View
     *
     * @param theModel an instance of the {@link SimonLightModel}
     */
    public SimonLightScreenView(SimonLightModel theModel) {
        this.theModel = theModel;
        initSceneGraph();
    }

    /**
     * The scene graph containing the game layout
     */
    public void initSceneGraph() {
        this.root = new AnchorPane();
        this.startMenu = new HBox();
        startMenu.setAlignment(Pos.CENTER);
        this.buttons_center = new FlowPane();

        // score label set to 0 to start
        this.score = new Label("Score: 0");

        this.startButton = new Button("Start");
        this.playButton = new Button("Play");
        this.playButton.setVisible(false);

        this.lights = new ArrayList<SVGPath>();
        int lightNumber = 0;
        for(Light modelLight : theModel.getLights()) {
            lightNumber++;
            SVGPath light = new SVGPath();
            switch (lightNumber){
                case 1:
                    // Red Button
                    light.getStyleClass().add("red_button");
                    light.setContent("M 330 210 C 510 210 630 360 630 510 L 480 510 C 480 420 420 360 330 360 L 330 210 ");
                    light.prefHeight(200);
                    light.prefWidth(300);
                    break;
                case 2:
                    // Yellow Button
                    light.getStyleClass().add("yellow_button");
                    light.setContent("M 300 450 C 480 450 600 300 600 150 L 450 150 C 450 240 390 300 300 300 L 300 450 ");
                    break;
                case 3:
                    // Blue Button
                    light.getStyleClass().add("blue_button");
                    light.setContent("M 150 500 C 150 350 250 200 450 200 L 450 350 C 350 350 300 400 300 500 L 150 500 ");
                    light.setLayoutX(0);
                    light.setLayoutY(0);
                    break;
                case 4:
                    // Green Button
                    light.getStyleClass().add("green_button");
                    light.setContent("M 180 150 C 180 300 300 450 480 450 L 480 300 C 390 300 330 240 330 150 L 180 150 ");
                    break;
            }

            // set a style class, for additional styles in CSS later
            light.getStyleClass().add("light");

            // set the fill color based on the model
            light.setFill(modelLight.getCurrentColor());

            // add the light to the array
            lights.add(light);
        }

        startButton.getStyleClass().add("start_button");

        // adds elements
        this.root.getChildren().add(startButton);
        this.root.getChildren().add(playButton);
        this.root.getChildren().addAll(lights);
        this.root.getChildren().add(score);
    }

    /**
     * Return a light at a specified index
     *
     * @param i the index for the light
     * @return the light at index i
     */
    public SVGPath getLight(int i) {
        return lights.get(i);
    }
}
