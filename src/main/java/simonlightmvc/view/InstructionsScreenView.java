/* *****************************************
 * Name: Kate Strong
 * Date: 2/23/2023
 *
 * Project: Simon
 * Package: simonlightmvc.view
 * Class: InstructionsScreenView
 *
 * Description:
 * View for the instructions screen.
 * ****************************************
 */
package simonlightmvc.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InstructionsScreenView {
    private VBox root;

    /**
     * Instructions for how to play the simon game.
     */
    private Label instructionsTxt;

    /**
     * Button to return to the home screen.
     */
    private Button returnButton;

    public InstructionsScreenView() {
        initSceneGraph();
    }

    public VBox getRoot() { return root; }

    public Button getReturnButton() {return returnButton; }

    private void initSceneGraph(){
        this.root = new VBox();
        this.instructionsTxt = new Label("Get ready to watch, remember, repeat!" + "\n" +
                "The Simon game is the exciting electronic brain game" + "\n" +
                " in which players must repeat random sequences of lights " + "\n" +
                "by pressing the colored pads in the correct order.");
        this.instructionsTxt.getStyleClass().add("instruction");
        this.returnButton = new Button("Back to Main Menu");
        this.root.getChildren().add(instructionsTxt);
        this.root.getChildren().add(returnButton);
    }
}